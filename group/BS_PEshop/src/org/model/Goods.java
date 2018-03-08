package org.model;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

/**
 * Goods entity. 
 * @author Real.Yi
 */

@SuppressWarnings("serial")
public class Goods implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String attribute;
	private Double price;
	private Double listPrice;
	private String type;
	private String field;
	private Integer stock;
	private Integer sale;
	private Blob pic;
	private Set<GoodsList> goodsLists = new HashSet<GoodsList>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(String name, String attribute, Double price, Double listPrice,
			String type, String field, Integer stock, Blob pic) {
		this.name = name;
		this.attribute = attribute;
		this.price = price;
		this.listPrice = listPrice;
		this.type = type;
		this.field = field;
		this.stock = stock;
		this.pic = pic;
	}

	/** full constructor */
	public Goods(String name, String attribute, Double price, Double listPrice,
			String type, String field, Integer stock, Integer sale, Blob pic,
			Set<GoodsList> goodsLists, Set<Comment> comments) {
		this.name = name;
		this.attribute = attribute;
		this.price = price;
		this.listPrice = listPrice;
		this.type = type;
		this.field = field;
		this.stock = stock;
		this.sale = sale;
		this.pic = pic;
		this.goodsLists = goodsLists;
		this.comments = comments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getListPrice() {
		return this.listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSale() {
		return this.sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
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

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}