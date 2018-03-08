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
	
	//显示用户购物车信息
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
	
	//增加商品进购物车
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
	
	//更新购物车中选中商品信息
	public String updateShopcar() throws Exception {

System.out.println("id= " + this.goodsList.getId());
System.out.println("old_amount=" + this.goodsList.getAmount());

		GoodsList list = this.orderService.getGoodsListById(this.goodsList.getId());
		list.setAmount(this.goodsList.getAmount());
		
System.out.println("new_amount=" + list.getAmount());
		this.orderService.updateGoodsList(list);
		return SUCCESS;
	}
	
	//删除购物车中选中商品
	public String deleteShopcar() throws Exception {
		this.orderService.deleteGoodsList(this.goodsList.getId());
		return SUCCESS;
	}
	
	
	//显示用户订单列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showUserOrders() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		Map request = (Map) ActionContext.getContext().get("request");
		UserInfo user = (UserInfo) session.get("custom");
		List<Order> orders = this.orderService.getUserOrder(user);
		request.put("orders", orders);
		return SUCCESS;
	}
	
	//创建新的表单信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String createUserOrder() throws Exception {

System.out.println(shopCar_id.length);

		//要将信息先存储起来，供正式生成订单时使用
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
		
		//商品库存不足
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
	
	//新增表单信息(实际入库),涉及商品库存-amount,销量+amount
	@SuppressWarnings("rawtypes")
	public String addUserOrder() throws Exception {
		//将选中的goodslist更新orderid值
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
	
	
	//确认收货,同时增加一条已收货的物流信息
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
	
	//更新订单信息
	public String updateUserOrder() throws Exception {
		this.orderService.updateOrder(this.order);
		return SUCCESS;
	}
	
//删除指定订单信息——暂未使用
	public String deleteUserOrder() throws Exception {
		return SUCCESS;
	}
	
	//显示指定订单信息
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
