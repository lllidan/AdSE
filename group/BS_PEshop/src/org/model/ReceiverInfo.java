package org.model;


/**
 * ReceiverInfo entity. 
 * @author Real.Yi
 */

@SuppressWarnings("serial")
public class ReceiverInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfo userInfo;
	private String name;
	private String phone;
	private String address;

	// Constructors

	/** default constructor */
	public ReceiverInfo() {
	}

	/** full constructor */
	public ReceiverInfo(UserInfo userInfo, String name, String phone,
			String address) {
		this.userInfo = userInfo;
		this.name = name;
		this.phone = phone;
		this.address = address;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

}