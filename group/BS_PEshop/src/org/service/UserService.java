package org.service;

import java.io.File;
import java.util.List;

import org.model.UserInfo;
import org.model.ReceiverInfo;

public interface UserService {
	
	public UserInfo loginUser(String username, String password);
	
	public boolean register(UserInfo user);
	
	public void updateInfo(UserInfo user);
	
	public void updatePic(Integer id, File pic);
	
	public void updatePassword(String username, String password);
	
	public UserInfo showUserInfo(Integer id);
	
	public List<ReceiverInfo> showReceiverList(UserInfo user);
	
	public ReceiverInfo showReceiver(Integer id);
	
	public void saveReceiver(ReceiverInfo receiver);
	
	public void updateReceiver(ReceiverInfo receiver);
	
	public void deleteReceiver(Integer id);
	
	public UserInfo findByID(Integer id);
	
	public UserInfo findByUsername(String username);
	
	

	
	public void addAdmin(UserInfo user);
	
	public List<UserInfo> showUserInfoList();
	
	public UserInfo findUserById(Integer id);
	
	public UserInfo findUserByUsername(String username);
	
	public void deleteGuest(Integer id);
}
