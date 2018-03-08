package org.action.admin;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.model.Distribute;
import org.model.GoodsList;
import org.model.Order;
import org.service.OrderService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class OrderManAction extends ActionSupport {
	
	private Order order;
	private OrderService orderService;
	private Distribute distribute;
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
	public Distribute getDistribute() {
		return distribute;
	}
	public void setDistribute(Distribute distribute) {
		this.distribute = distribute;
	}
	
	//显示所有订单
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showOrders() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		List<Order> orderList = this.orderService.getAllOrder();
		
		request.put("orderList", orderList);
		return SUCCESS;
	}
	
	//显示指定订单详情
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showOrderInfo() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		Integer orderId = this.order.getId();
		List<GoodsList> shopList = this.orderService.getGoodsListByOrderId(orderId);
		List<Distribute> distributeList = this.orderService.getOrderDistribute(orderId);
		this.order = this.orderService.getOrderById(orderId);
		request.put("orderInfo", this.order);
		request.put("shopList", shopList);
		request.put("distributeList", distributeList);
		return SUCCESS;
	}
	
	//更新指定订单(状态为主)
	public String updateOrderInfo() throws Exception {
System.out.println("order.id=" + this.order.getId());
System.out.println("status="+this.order.getStatus());
		this.orderService.updateOrder(this.order);
		return SUCCESS;
	}
	
	//删除指定订单
	public String deleteOrderInfo() throws Exception {
		this.orderService.deleteOrder(this.order.getId());
		return SUCCESS;
	}
	
	//展示订单物流信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showDistribute() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		Integer orderId = this.order.getId();
		List<Distribute> distributeList = this.orderService.getOrderDistribute(orderId);
		request.put("distributeList", distributeList);
		return SUCCESS;
	}
	
	//新增物流信息
	public String addDistributeInfo() throws Exception {
System.out.println("id= " + this.order.getId());
System.out.println("distribute= " + this.distribute.getExpress());
if(this.order==null)
	System.out.println("order=null");
if(this.distribute==null)
	System.out.println("distribute=null");
System.out.println(distribute.getCourier());
System.out.println(distribute.getExpress());
System.out.println(distribute.getLocation());
System.out.println(distribute.getDelivery());

		this.distribute.setOrder(this.order);
		this.distribute.setTime(new Timestamp(System.currentTimeMillis()));

System.out.println(distribute.getExpress());
System.out.println(distribute.getLocation());
System.out.println(distribute.getDelivery());
System.out.println(distribute.getTime());
System.out.println(distribute.getOrder().getId());		
		
		this.orderService.addDistribute(this.distribute);
		return SUCCESS;
	}
	
//更新物流信息——暂不使用
	public String updateDistributeInfo() throws Exception {
		this.orderService.updateDistribute(this.distribute);
		return SUCCESS;
	}
	
//删除物流信息——暂不使用
	public String deleteDistributeInfo() throws Exception {
		this.orderService.deleteDistribute(this.distribute.getId());
		return SUCCESS;
	}
}
