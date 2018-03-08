package org.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.dao.DistributeDAO;
import org.dao.GoodsListDAO;
import org.dao.OrderDAO;
import org.model.Distribute;
import org.model.Goods;
import org.model.GoodsList;
import org.model.Order;
import org.model.ReceiverInfo;
import org.model.UserInfo;
import org.service.GoodsService;
import org.service.OrderService;
import org.service.UserService;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDao;
	private GoodsListDAO goodsListDao;
	private DistributeDAO distributeDao;
	private UserService userService;
	private GoodsService goodsService;
	
	public OrderDAO getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
	public GoodsListDAO getGoodsListDao() {
		return goodsListDao;
	}
	public void setGoodsListDao(GoodsListDAO goodsListDao) {
		this.goodsListDao = goodsListDao;
	}
	public DistributeDAO getDistributeDao() {
		return distributeDao;
	}
	public void setDistributeDao(DistributeDAO distributeDao) {
		this.distributeDao = distributeDao;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	
	public Double calculateTotalPrices(Integer[] shopCarIds) {
		double totalPrice = 0, price;
		int amount;
		GoodsList list;
		for(Integer id : shopCarIds) {
			list = this.goodsListDao.findById(id);
			amount = list.getAmount();
			price = list.getPrice();
			totalPrice += amount * price;
		}
		System.out.println("total price = " + totalPrice);
		return totalPrice;
	}
	
	
	//�½�����ͬʱҪ�����漰����GoodsList��order,ͬʱ�����漰������Ʒ���-amount,����+amount
	public Integer addOrder(Integer receiverId, Integer[] shopCarIds, Order order) {
		ReceiverInfo receiver = userService.showReceiver(receiverId);
		order.setName(receiver.getName());
		order.setPhone(receiver.getPhone());
		order.setAddress(receiver.getAddress());
		
System.out.println("order_id = " + order.getId()==null?"null":order.getId());		
System.out.println("order_userid=" + order.getUserInfo().getId());
System.out.println("order_name=" + order.getName());
System.out.println("order_phone=" + order.getPhone());
System.out.println("order_address=" + order.getAddress());
System.out.println("order_totalprice=" + order.getTotalprices());
System.out.println("order_time=" + order.getTime());
System.out.println("order_status=" + order.getStatus());
		
		Integer order_id = orderDao.save(order);
System.out.print("Order saved, id = "+order_id);
		order = orderDao.findById(order_id);
	
		GoodsList list;
		Goods goods;
		for(Integer id : shopCarIds) {
			list = this.goodsListDao.findById(id);
			list.setOrder(order);
			goods = goodsService.findGoodsById(list.getGoods().getId());
			goods.setSale(goods.getSale() + list.getAmount());
			goods.setStock(goods.getStock() - list.getAmount());
System.out.println("sale="+goods.getSale());
System.out.println("stock="+goods.getStock());
			this.goodsService.updateGoods(goods);
			this.goodsListDao.update(list);
		}
		
		return order_id;
	}
	
	public void updateOrder(Order order) {
		orderDao.update(order);
	}
	public void deleteOrder(Integer id) {
		Order order = orderDao.findById(id);
		orderDao.delete(order);
	}
	public Order getOrderById(Integer id) {
		return orderDao.findById(id);
	}
	public List<Order> getUserOrder(UserInfo user) {
		return orderDao.findByProperty("userInfo", user);
	}
	public List<Order> getAllOrder() {
		return orderDao.findAll();
	}
	public void addGoodsList(GoodsList goodsList) {
		List<GoodsList> list = goodsListDao.findByUserAndGoods(goodsList.getUserInfo(), goodsList.getGoods());
		if(list.isEmpty()) {
			goodsListDao.save(goodsList);
		} else {
			GoodsList old = list.get(0);
			goodsList.setId(old.getId());
			goodsList.setAmount(goodsList.getAmount() + old.getAmount());
			this.updateGoodsList(goodsList);
		}
	}
	public void updateGoodsList(GoodsList goodsList) {
		goodsListDao.update(goodsList);
	}
	public void deleteGoodsList(Integer id) {
		GoodsList goodsList = goodsListDao.findById(id);
		goodsListDao.delete(goodsList);
	}
	public GoodsList getGoodsListById(Integer id) {
		return goodsListDao.findById(id);
	}
	//��ȡ���ﳵ�и�����Ʒ
	public List<GoodsList> getGoodsListsByIds(Integer[] ids) {
		List<GoodsList> list = new ArrayList<GoodsList>();
		Goods goods;
		GoodsList goodsList;
		for(Integer id : ids) {
			goodsList = goodsListDao.findById(id);
			goods = this.goodsService.findGoodsById(goodsList.getGoods().getId());
			//����治��,
			if(goods.getStock() < goodsList.getAmount()) {
				return null;
			}
			list.add(goodsList);
		}
System.out.println("size=" + list.size());
		return list;
	}
	//�ж���Ʒ�б��е���Ʒ�Ƿ��п�治��
	public boolean isGoodsEnough(Integer[] ids) {
		GoodsList list;
		Goods goods;
		for(Integer id : ids) {
			list = goodsListDao.findById(id);
			goods = list.getGoods();
			if(goods.getStock() < list.getAmount())
				return false;
		}
		return true;
	}
	//��ȡ�û����ﳵ��ͬʱ�ж���Ʒ����������������Ʒ�ۼ�
	public List<GoodsList> getUserGoodsList(UserInfo user) {
		List<GoodsList> list = goodsListDao.findByUser(user);
		
		Goods goods;
		for(GoodsList gl : list) {
			goods = this.goodsService.findGoodsById(gl.getGoods().getId());
			//������Ʒ�۸�
			if(goods.getPrice() != gl.getPrice()) {
				gl.setPrice(goods.getPrice());
				this.goodsListDao.update(gl);
			}
			//����治�㣬���ö�Ӧ��Ʒ����Ϊ0����ʾ��ʱ���ܹ���
			if(goods.getStock() < gl.getAmount())
				gl.setAmount(0);
		}
		return list;
	}
	public List<GoodsList> getGoodsListByOrderId(Integer orderId) {
		Order order = orderDao.findById(orderId);
		return goodsListDao.findByProperty("order", order);
	}
	
	
	public void addDistribute(Distribute distribute) {
		distributeDao.save(distribute);
	}
	
	public void addConfirmDistribute(Order order) {
		Distribute distribute = new Distribute();
		distribute.setLocation(order.getAddress());
		distribute.setDelivery("��ǩ��");
		distribute.setTime(new Timestamp(System.currentTimeMillis()));
		distribute.setOrder(order);
		distributeDao.save(distribute);
	}
	
	public void updateDistribute(Distribute distribute) {
		distributeDao.update(distribute);
	}
	public void deleteDistribute(Integer id) {
		Distribute distribute = distributeDao.findById(id);
		distributeDao.delete(distribute);
	}
	public Distribute getDistributeById(Integer id) {
		return distributeDao.findById(id);
	}
	public List<Distribute> getOrderDistribute(Integer orderId) {
		Order order = orderDao.findById(orderId);
		return distributeDao.findByProperty("order", order);
	}
	
}
