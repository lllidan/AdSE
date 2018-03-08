package org.action;


import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer receiver_id;
	private String receiver_id2;
	
	public String execute() throws Exception {
		if(receiver_id!=null)
			System.out.println("rec1="+receiver_id);
		if(receiver_id2!=null)
			System.out.println("rec2=" + receiver_id2);
		
		/*
		for(int i=0;i<this.receiver_id.length;i++){
			ints[i] = Integer.parseInt(receiver_id[i]);
			System.out.println(ints);
		}*/
		return SUCCESS;
	}

	public Integer getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(Integer receiver_id) {
		this.receiver_id = receiver_id;
	}

	public String getReceiver_id2() {
		return receiver_id2;
	}

	public void setReceiver_id2(String receiver_id2) {
		this.receiver_id2 = receiver_id2;
	}
}
