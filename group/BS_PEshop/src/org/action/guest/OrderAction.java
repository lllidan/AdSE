package org.action.guest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.model.Distribute;
import org.model.Goods;
import org.model.GoodsList;
import org.model.Order;
import org.model.ReceiverInfo;
import org.model.UserInfo;
import org.service.OrderService;
import org.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class OrderAction extends ActionSupport {
	
	private Order order;
	private OrderService orderService;
	private GoodsList goodsList;
	private Goods goods;
	private List<GoodsList> shopCar;
	private Integer[] shopCar_id;
	private Integer receiver_id;
	private	UserService userService;
	private Integer order_id;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public GoodsList getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(GoodsList goodsList) {
		this.goodsList = goodsList;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public List<GoodsList> getShopCar() {
		return shopCar;
	}
	public void setShopCar(List<GoodsList> shopCar) {
		this.shopCar = shopCar;
	}
	public Integer[] getShopCar_id() {
		return shopCar_id;
	}
	public void setShopCar_id(Integer[] shopCar_id) {
		this.shopCar_id = shopCar_id;
	}
	public Integer getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(Integer receiver_id) {
		this.receiver_id = receiver_id;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Integer getOrder_id() {
		return this.order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	//��ʾ�û����ﳵ��Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showShopcar() throws Exception {
System.out.println("us_id=" + order_id==null?"null":order_id);
		Map session = (Map) ActionContext.getContext().getSession();
		Map request = (Map) ActionContext.getContext().get("request");
		UserInfo user = (UserInfo) session.get("custom");
		
		List<GoodsList> shopCar = this.orderService.getUserGoodsList(user);
		request.put("shopCar", shopCar);
		request.put("order_id", this.order_id);
		return SUCCESS;
	}
	
	//������Ʒ�����ﳵ
	@SuppressWarnings("rawtypes")
	public String addInShopcar() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		UserInfo user = (UserInfo) session.get("custom");
		
		GoodsList goodsList = new GoodsList();
		goodsList.setUserInfo(user);
		goodsList.setGoods(this.goods);
		goodsList.setAmount(1);
		goodsList.setPrice(this.goods.getPrice());

		this.orderService.addGoodsList(goodsList);
		return SUCCESS;
	}
	
	//���¹��ﳵ��ѡ����Ʒ��Ϣ
	public String updateShopcar() throws Exception {

System.out.println("id= " + this.goodsList.getId());
System.out.println("old_amount=" + this.goodsList.getAmount());

		GoodsList list = this.orderService.getGoodsListById(this.goodsList.getId());
		list.setAmount(this.goodsList.getAmount());
		
System.out.println("new_amount=" + list.getAmount());
		this.orderService.updateGoodsList(list);
		return SUCCESS;
	}
	
	//ɾ�����ﳵ��ѡ����Ʒ
	public String deleteShopcar() throws Exception {
		this.orderService.deleteGoodsList(this.goodsList.getId());
		return SUCCESS;
	}
	
	
	//��ʾ�û������б�
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showUserOrders() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		Map request = (Map) ActionContext.getContext().get("request");
		UserInfo user = (UserInfo) session.get("custom");
		List<Order> orders = this.orderService.getUserOrder(user);
		request.put("orders", orders);
		return SUCCESS;
	}
	
	//�����µı���Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String createUserOrder() throws Exception {

System.out.println(shopCar_id.length);

		//Ҫ����Ϣ�ȴ洢����������ʽ���ɶ���ʱʹ��
		Map session = (Map) ActionContext.getContext().getSession();
		Map request = (Map) ActionContext.getContext().get("request");
		
		UserInfo user = (UserInfo) session.get("custom");
		Order newOrder = new Order();
		newOrder.setUserInfo(user);
		newOrder.setStatus("0");
		newOrder.setTotalprices(this.orderService.calculateTotalPrices(this.shopCar_id));
		newOrder.setTime(new Timestamp(System.currentTimeMillis()));

		List<ReceiverInfo> receivers = this.userService.showReceiverList(user);
		List<GoodsList> list = this.orderService.getGoodsListsByIds(this.shopCar_id);
		
		//��Ʒ��治��
		if(list == null || list.size() < 1) {
			this.order_id = -1;
			return ERROR;
		}
		
		session.put("shopCar_id", this.shopCar_id);
		session.put("newOrder", newOrder);
		request.put("receivers", receivers);
		request.put("shopList", list);

System.out.println("action is ok");
		return SUCCESS;
	}
	
	//��������Ϣ(ʵ�����),�漰��Ʒ���-amount,����+amount
	@SuppressWarnings("rawtypes")
	public String addUserOrder() throws Exception {
		//��ѡ�е�goodslist����orderidֵ
		Map session = (Map) ActionContext.getContext().getSession();
		Order newOrder = (Order) session.get("newOrder");
		Integer[] shopCar_id = (Integer[]) session.get("shopCar_id");
		
System.out.println("shopid=" + shopCar_id.length);
System.out.println("receicer_id=" + this.receiver_id);
System.out.println("action is ok");
		
		if(!this.orderService.isGoodsEnough(shopCar_id)) {
			this.order_id = -1;
			return ERROR;
		}
			
		this.order_id = this.orderService.addOrder(this.receiver_id, shopCar_id, newOrder);
		return SUCCESS;
	}
	
	
	//ȷ���ջ�,ͬʱ����һ�����ջ���������Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String confirmOrder() throws Exception {
		Order updateOrder = this.orderService.getOrderById(order_id);
		if(updateOrder.getStatus().equals("2")) {
			updateOrder.setStatus("3");
			this.orderService.updateOrder(updateOrder);
			this.orderService.addConfirmDistribute(updateOrder);
		}
		
		List<GoodsList> list = this.orderService.getGoodsListByOrderId(order_id);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("list", list);
		request.put("orderId", this.order_id);
		return SUCCESS;
	}
	
	//���¶�����Ϣ
	public String updateUserOrder() throws Exception {
		this.orderService.updateOrder(this.order);
		return SUCCESS;
	}
	
//ɾ��ָ��������Ϣ������δʹ��
	public String deleteUserOrder() throws Exception {
		return SUCCESS;
	}
	
	//��ʾָ��������Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showUserOrder() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		Order orderInfo = this.orderService.getOrderById(this.order_id);
		List<GoodsList> shopList = this.orderService.getGoodsListByOrderId(this.order_id);
		List<Distribute> distributeList = this.orderService.getOrderDistribute(this.order_id);
		
		request.put("orderInfo", orderInfo);
		request.put("shopList", shopList);
		request.put("distributeList", distributeList);
		return SUCCESS;
	}
	
	
}
