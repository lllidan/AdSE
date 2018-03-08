package org.action.guest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.model.Comment;
import org.model.Goods;
import org.model.Order;
import org.model.UserInfo;
import org.service.GoodsService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GoodsAction extends ActionSupport {

	private Goods goods;
	private GoodsService goodsService;
	private Comment comment;
	private List<Goods> goodsList;
	private Integer order_id;
	private String query_type;
	private String query_content;
	
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getQuery_type() {
		return query_type;
	}
	public void setQuery_type(String query_type) {
		this.query_type = query_type;
	}
	public String getQuery_content() {
		return query_content;
	}
	public void setQuery_content(String query_content) {
		this.query_content = query_content;
	}
	
	
	//�г�ȫ����Ʒ��Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String listGoods() throws Exception {
		List<Goods> goodsList = this.goodsService.findAllGoods();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("goodsList", goodsList);
		return SUCCESS;
	}
	
	//��ʾ��Ʒ��ϸ��Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String listGoodsInfo() throws Exception {
		Goods goodsInfo = this.goodsService.findGoodsById(this.goods.getId());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("goodsInfo", goodsInfo);
		
		List<Comment> comments = this.goodsService.getGoodsComments(this.goods);
		request.put("comments", comments);
		return SUCCESS;
	}
	
	//��ʾ��Ʒ����
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String showComments() throws Exception {
		List<Comment> comments = this.goodsService.getGoodsComments(this.goods);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("comments", comments);
		return SUCCESS;
	}
	
	//������Ʒ����
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String addGoodsComment() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		Map request = (Map) ActionContext.getContext().get("request");
		UserInfo guest = (UserInfo) session.get("custom");
		
		this.goods = this.goodsService.findGoodsById(this.goods.getId());
		this.comment.setUserInfo(guest);
		this.comment.setTheme("��Ʒ����");
		this.comment.setGoods(this.goods);
		this.comment.setTime(new Timestamp(System.currentTimeMillis()));
		Order order = new Order();
		order.setId(this.order_id);
		this.comment.setOrder(order);

System.out.println("������ƷID:" + this.goods.getId());
System.out.println("�����û���:" + guest.getUsername());
System.out.println("�����������:" + this.comment.getAnonymity());
System.out.println("����ʱ��:" + this.comment.getTime());
System.out.println("��������:" + this.comment.getContent());
		this.goodsService.addGoodsComment(this.comment);

		
		request.put("commentInfo", this.comment);
		return SUCCESS;
	}
	
	//���빺�ﳵ
	public String addShopcar() throws Exception {
		this.goods = this.goodsService.findGoodsById(this.goods.getId());
		return SUCCESS;
	}
	
	//��ѯ��Ʒ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryGoods() throws Exception {
System.out.println("type="+this.query_type);
System.out.println("content="+this.query_content);
		this.query_content = "%" + this.query_content + "%";
		List<Goods> goodsList = this.goodsService.findGoodsByProperty(query_type, query_content);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("goodsList", goodsList);
		return SUCCESS;
	}
}
