package org.model;

import java.sql.Timestamp;

/**
 * Distribute entity.  
 * @author Real.Yi
 */

@SuppressWarnings("serial")
public class Distribute implements java.io.Serializable {

	// Fields

	private Integer id;
	private Order order;
	private String express;
	private String courier;
	private Timestamp time;
	private String location;
	private String delivery;

	// Constructors

	/** default constructor */
	public Distribute() {
	}

	/** minimal constructor */
	public Distribute(Order order, String express, Timestamp time,
			String location, String delivery) {
		this.order = order;
		this.express = express;
		this.time = time;
		this.location = location;
		this.delivery = delivery;
	}

	/** full constructor */
	public Distribute(Order order, String express, String courier,
			Timestamp time, String location, String delivery) {
		this.order = order;
		this.express = express;
		this.courier = courier;
		this.time = time;
		this.location = location;
		this.delivery = delivery;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getExpress() {
		return this.express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getCourier() {
		return this.courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDelivery() {
		return this.delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

}