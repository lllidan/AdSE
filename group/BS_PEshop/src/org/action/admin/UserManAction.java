package org.action.admin;

import java.util.List;
import java.util.Map;

import org.model.UserInfo;
import org.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserManAction extends ActionSupport {

	private UserInfo user;
	private UserService userService;
	
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
	
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
	//�г�ȫ���ͻ������Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showUsers() throws Exception {
		List<UserInfo> userList = this.userService.showUserInfoList();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("userList", userList);
		return SUCCESS;
	}
	
	//��ʾָ���ͻ���Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showUser() throws Exception {
		UserInfo guest = this.userService.findByID(this.user.getId());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("user", guest);
		return SUCCESS;
	}
	
	//���ӹ���Ա�û�
	public String addAdminUser() throws Exception {
		this.userService.addAdmin(this.user);
		return SUCCESS;
	}
	
	//ɾ��ָ���û�
	public String deleteUser() throws Exception {
		this.userService.deleteGuest(this.user.getId());
		return SUCCESS;
	}
}
