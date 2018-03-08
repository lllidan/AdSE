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
	
	//��ʾ���ж���
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showOrders() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		List<Order> orderList = this.orderService.getAllOrder();
		
		request.put("orderList", orderList);
		return SUCCESS;
	}
	
	//��ʾָ����������
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
	
	//����ָ������(״̬Ϊ��)
	public String updateOrderInfo() throws Exception {
System.out.println("order.id=" + this.order.getId());
System.out.println("status="+this.order.getStatus());
		this.orderService.updateOrder(this.order);
		return SUCCESS;
	}
	
	//ɾ��ָ������
	public String deleteOrderInfo() throws Exception {
		this.orderService.deleteOrder(this.order.getId());
		return SUCCESS;
	}
	
	//չʾ����������Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showDistribute() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		Integer orderId = this.order.getId();
		List<Distribute> distributeList = this.orderService.getOrderDistribute(orderId);
		request.put("distributeList", distributeList);
		return SUCCESS;
	}
	
	//����������Ϣ
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
	
//����������Ϣ�����ݲ�ʹ��
	public String updateDistributeInfo() throws Exception {
		this.orderService.updateDistribute(this.distribute);
		return SUCCESS;
	}
	
//ɾ��������Ϣ�����ݲ�ʹ��
	public String deleteDistributeInfo() throws Exception {
		this.orderService.deleteDistribute(this.distribute.getId());
		return SUCCESS;
	}
}
