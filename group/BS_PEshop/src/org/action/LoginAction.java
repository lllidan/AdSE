package org.action;

import java.util.Map;


//import org.dao.UserInfoDAO;
import org.model.UserInfo;
import org.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	
	private UserInfo user;
	private UserService loginService;
	
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public UserService getLoginService() {
		return loginService;
	}
	public void setLoginService(UserService loginService) {
		this.loginService = loginService;
	}
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		UserInfo dlUser = loginService.loginUser(this.user.getUsername(), this.user.getPassword());
		if(dlUser != null) {
			@SuppressWarnings("rawtypes")
			Map session = (Map) ActionContext.getContext().getSession();
			session.put("custom", dlUser);
System.out.println("success");
			return SUCCESS;
		} else {
System.out.println("error");
			return ERROR;
		}
	}
	
	public String register() throws Exception {
System.out.println(user.getUsername());
System.out.println(user.getPassword());
System.out.println(user.getSex());
System.out.println(user.getBirthday());
		if(loginService.register(user))
			return SUCCESS;
		else
			return ERROR;
	}
}

