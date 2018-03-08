package org.service;

import java.util.List;

import org.model.Distribute;
import org.model.GoodsList;
import org.model.Order;
import org.model.UserInfo;

public interface OrderService {
	
	public Double calculateTotalPrices(Integer[] shopCarIds);
	
	public Integer addOrder(Integer receiverId, Integer[] shopCarIds, Order order);
	
	public void updateOrder(Order order);
	
	public void deleteOrder(Integer id);
	
	public Order getOrderById(Integer id);
	
	public List<Order> getUserOrder(UserInfo user);
	
	public List<Order> getAllOrder();
	
	
	
	public void addGoodsList(GoodsList goodsList);
	
	public void updateGoodsList(GoodsList goodsList);
	
	public void deleteGoodsList(Integer id);
	
	public GoodsList getGoodsListById(Integer id);
	
	public List<GoodsList> getGoodsListsByIds(Integer[] ids);
	
	public boolean isGoodsEnough(Integer[] ids);
	
	public List<GoodsList> getUserGoodsList(UserInfo user);
	
	public List<GoodsList> getGoodsListByOrderId(Integer orderId);
	
	
	
	public void addDistribute(Distribute distribute);
	
	public void addConfirmDistribute(Order order);
	
	public void updateDistribute(Distribute distribute);
	
	public void deleteDistribute(Integer id);
	
	public Distribute getDistributeById(Integer id);
	
	public List<Distribute> getOrderDistribute(Integer orderId);
}
