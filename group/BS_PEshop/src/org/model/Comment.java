package org.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Comment entity. 
 * @author Real.Yi
 */

@SuppressWarnings("serial")
public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Comment comment;
	private Goods goods;
	private UserInfo userInfo;
	private String theme;
	private String content;
	private Timestamp time;
	private String anonymity;
	private Order order;
	private Set<Order> orders = new HashSet<Order>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(UserInfo userInfo, String theme, String content,
			Timestamp time, String anonymity, Order order) {
		this.userInfo = userInfo;
		this.theme = theme;
		this.content = content;
		this.time = time;
		this.anonymity = anonymity;
		this.order = order;
	}

	/** full constructor */
	public Comment(Comment comment, Goods goods, UserInfo userInfo,
			String theme, String content, Timestamp time, String anonymity, 
			Order order, Set<Order> orders, Set<Comment> comments) {
		this.comment = comment;
		this.goods = goods;
		this.userInfo = userInfo;
		this.theme = theme;
		this.content = content;
		this.time = time;
		this.anonymity = anonymity;
		this.order = order;
		this.orders = orders;
		this.comments = comments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getAnonymity() {
		return this.anonymity;
	}

	public void setAnonymity(String anonymity) {
		this.anonymity = anonymity;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}