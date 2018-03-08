package org.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Order entity. 
 * @author Real.Yi
 */

@SuppressWarnings("serial")
public class Order implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfo userInfo;
	private Comment comment;
	private String name;
	private String phone;
	private String address;
	private Double totalprices;
	private Timestamp time;
	private String status;
	private Set<GoodsList> goodsLists = new HashSet<GoodsList>(0);
	private Set<Distribute> distributes = new HashSet<Distribute>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	
	// Constructors

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Integer id, UserInfo userInfo, Comment comment, String name,
			String phone, String address, Double totalprices, Timestamp time,
			String status) {
		this.id = id;
		this.userInfo = userInfo;
		this.comment = comment;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.totalprices = totalprices;
		this.time = time;
		this.status = status;
	}

	/** full constructor */
	public Order(Integer id, UserInfo userInfo, Comment comment, String name,
			String phone, String address, Double totalprices, Timestamp time,
			String status, Set<GoodsList> goodsLists,
			Set<Distribute> distributes, Set<Comment> comments) {
		this.id = id;
		this.userInfo = userInfo;
		this.comment = comment;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.totalprices = totalprices;
		this.time = time;
		this.status = status;
		this.goodsLists = goodsLists;
		this.distributes = distributes;
		this.comments = comments;
	}

	

	// Property accessors


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getTotalprices() {
		return totalprices;
	}

	public void setTotalprices(Double totalprices) {
		this.totalprices = totalprices;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<GoodsList> getGoodsLists() {
		return goodsLists;
	}

	public void setGoodsLists(Set<GoodsList> goodsLists) {
		this.goodsLists = goodsLists;
	}

	public Set<Distribute> getDistributes() {
		return distributes;
	}

	public void setDistributes(Set<Distribute> distributes) {
		this.distributes = distributes;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	

}