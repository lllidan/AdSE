package org.action.guest;


import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.ReceiverInfo;
import org.model.UserInfo;
import org.service.UserService;
import org.tools.Convert;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {
	
	private UserInfo user;
	private UserService userService;
	private Date birthday;
	private String new_password;
	private ReceiverInfo receiver;
	private File pic_file;
	
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getnew_password() {
		return new_password;
	}
	public void setnew_password(String new_password) {
		this.new_password = new_password;
	}
	
	public ReceiverInfo getReceiver() {
		return receiver;
	}
	public void setReceiver(ReceiverInfo receiver) {
		this.receiver = receiver;
	}
	
	public File getPic_file() {
		return pic_file;
	}
	public void setPic_file(File pic_file) {
		this.pic_file = pic_file;
	}
	
	
	//显示用户信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String execute() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		UserInfo guest = (UserInfo) session.get("custom");
		
		guest = userService.showUserInfo(guest.getId());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("guest", guest);
		if(guest.getPic() == null)
			request.put("img", "none");
		else
			request.put("img", "have");
		return SUCCESS;
	}
	
	//显示修改信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateUserInfo() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		UserInfo guest = (UserInfo) session.get("custom");
		
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("guest", guest);
		if(guest.getPic() == null)
			request.put("img", "none");
		else
			request.put("img", "have");
		return SUCCESS;
	}
	
	//执行修改操作
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateUser() throws Exception {
		//数据类型java.util.Date与MySql中Date会抛出
		//Data truncation: Incorrect date value: '' for column 'birthday' at row 1
		//暂时无法解决，只能将map时改为java.sql.Date类型与之对应，同时，程序需要将页面获取的java.util.Date进行转换
		this.user.setBirthday(new java.sql.Date(this.birthday.getTime()));
		
		this.userService.updateInfo(this.user);
		if(this.pic_file != null) {
			this.userService.updatePic(this.user.getId(), pic_file);
		}
		
		UserInfo guest = this.userService.findByID(this.user.getId());
		Map session = (Map) ActionContext.getContext().getSession();
		session.put("custom", guest);
		return SUCCESS;
	}
	
	//显示头像
	public String getPic() throws Exception {
		UserInfo guest = this.userService.findByID(this.user.getId());
		
		byte[] pic = null;
		if(guest.getPic() != null && guest.getPic().length() > 0) {
			pic =  Convert.blobToBytes(guest.getPic());
		}
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("image/jpeg");
		//得到输出流
		ServletOutputStream os = response.getOutputStream();
		
		if(pic != null && pic.length > 0){
			for(int i = 0;i < pic.length; i++){
				os.write(pic[i]);
			}
		}
		return NONE;
	}

	//执行修改密码操作
	@SuppressWarnings("rawtypes")
	public String updatePassword() throws Exception {
		System.out.println("new:" + this.new_password + "/" + "old:" + this.user.getPassword());
		
		Map session = (Map) ActionContext.getContext().getSession();
		UserInfo guest = (UserInfo) session.get("custom");
		
		if(guest.getPassword().equals(this.user.getPassword())
				&& !guest.getPassword().equals(this.new_password)) {
			this.userService.updatePassword(guest.getUsername(), this.new_password);
			return SUCCESS;
		} else
			return ERROR;
	}
	
	//显示用户的收件人信息列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String showReceiverList() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		UserInfo guest = (UserInfo) session.get("custom");
		
		List<ReceiverInfo> receivers = this.userService.showReceiverList(guest);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("receivers", receivers);
		return SUCCESS;
	}

	//显示用户的收件人信息
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String showReceiver() throws Exception {
		ReceiverInfo receiver = this.userService.showReceiver(this.receiver.getId());

		Map request = (Map) ActionContext.getContext().get("request");
		request.put("receiver", receiver);
		request.put("test", receiver == null ? "新增" : "修改");
		request.put("show", receiver == null ? "add" : "update");
		return SUCCESS;
	}
	
	//新增用户收件人信息
	@SuppressWarnings("rawtypes")
	public String saveReceiver() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		UserInfo guest = (UserInfo) session.get("custom");
		this.receiver.setUserInfo(guest);
		this.userService.saveReceiver(this.receiver);
		return SUCCESS;
	}
	
	//修改用户收件人信息
	@SuppressWarnings("rawtypes")
	public String updateReceiver() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		UserInfo guest = (UserInfo) session.get("custom");
		
		this.receiver.setUserInfo(guest);
		this.userService.updateReceiver(this.receiver);
		return SUCCESS;
	}
	
	//删除用户收件人信息
	public String deleteReceiver() throws Exception {
		this.userService.deleteReceiver(this.receiver.getId());
		return SUCCESS;
	}
}
