package org.model;

/**
 * GoodsList entity. 
 * @author Real.Yi
 */

@SuppressWarnings("serial")
public class GoodsList implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfo userInfo;
	private Goods goods;
	private Order order;
	private Double price;
	private Integer amount;

	// Constructors

	/** default constructor */
	public GoodsList() {
	}

	/** minimal constructor */
	public GoodsList(UserInfo userInfo, Goods goods, Double price,
			Integer amount) {
		this.userInfo = userInfo;
		this.goods = goods;
		this.price = price;
		this.amount = amount;
	}

	/** full constructor */
	public GoodsList(UserInfo userInfo, Goods goods, Order order, Double price,
			Integer amount) {
		this.userInfo = userInfo;
		this.goods = goods;
		this.order = order;
		this.price = price;
		this.amount = amount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}