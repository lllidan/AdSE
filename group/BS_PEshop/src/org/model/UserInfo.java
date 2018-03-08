package org.model;

import java.sql.Date;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

/**
 * UserInfo entity. 
 * @author Real.Yi
 */

@SuppressWarnings("serial")
public class UserInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String name;
	private String sex;
	private Date birthday;
	private String email;
	private String phone;
	private String address;
	private String password;
	private String limits;
	private Blob pic;
	private Set<GoodsList> goodsLists = new HashSet<GoodsList>(0);
	private Set<Order> orders = new HashSet<Order>(0);
	private Set<ReceiverInfo> receiverInfos = new HashSet<ReceiverInfo>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String username, String sex, Date birthday,
			String password, String limits, Blob pic) {
		this.username = username;
		this.sex = sex;
		this.birthday = birthday;
		this.password = password;
		this.limits = limits;
		this.pic = pic;
	}

	/** full constructor */
	public UserInfo(String username, String name, String sex, Date birthday,
			String email, String phone, String address, String password,
			String limits, Blob pic, Set<GoodsList> goodsLists, Set<Order> orders, 
			Set<ReceiverInfo> receiverInfos, Set<Comment> comments) {
		this.username = username;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.limits = limits;
		this.pic = pic;
		this.goodsLists = goodsLists;
		this.orders = orders;
		this.receiverInfos = receiverInfos;
		this.comments = comments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLimits() {
		return this.limits;
	}

	public void setLimits(String limits) {
		this.limits = limits;
	}

	public Blob getPic() {
		return pic;
	}

	public void setPic(Blob pic) {
		this.pic = pic;
	}

	public Set<GoodsList> getGoodsLists() {
		return this.goodsLists;
	}

	public void setGoodsLists(Set<GoodsList> goodsLists) {
		this.goodsLists = goodsLists;
	}

	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<ReceiverInfo> getReceiverInfos() {
		return this.receiverInfos;
	}

	public void setReceiverInfos(Set<ReceiverInfo> receiverInfos) {
		this.receiverInfos = receiverInfos;
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}