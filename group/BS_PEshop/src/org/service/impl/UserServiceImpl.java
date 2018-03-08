package org.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.dao.ReceiverInfoDAO;
import org.dao.UserInfoDAO;
import org.hibernate.Hibernate;
import org.model.ReceiverInfo;
import org.model.UserInfo;
import org.service.UserService;

public class UserServiceImpl implements UserService {

	private UserInfoDAO userDao;
	private ReceiverInfoDAO receiverDao;
	
	public UserInfoDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserInfoDAO userDao) {
		this.userDao = userDao;
	}
	public ReceiverInfoDAO getReceiverDao() {
		return receiverDao;
	}
	public void setReceiverDao(ReceiverInfoDAO receiverDao) {
		this.receiverDao = receiverDao;
	}
	
	public UserInfo loginUser(String username, String password) {
		UserInfo userLogin;
		userLogin = userDao.findByUsername(username);
		if(userLogin != null)
			if(userLogin.getPassword().equals(password))
				return userLogin;
		return null;
	}
	
	public boolean register(UserInfo user) {
		UserInfo userRegister;
		userRegister = userDao.findByUsername(user.getUsername());
		if(userRegister == null) {
			user.setLimits("g");
			userDao.save(user);
			return true;
		} else
			return false;
	}
	
	public void addAdmin(UserInfo user) {
		user.setLimits("a");
		userDao.save(user);
	}
	
	public void updateInfo(UserInfo user) {
		UserInfo guest = userDao.findByUsername(user.getUsername());
		guest.setName(user.getName());
		guest.setSex(user.getSex());
		System.out.println(user.getBirthday());
		System.out.println(guest.getBirthday());
		guest.setBirthday(user.getBirthday());
		guest.setEmail(user.getEmail());
		guest.setPhone(user.getPhone());
		guest.setAddress(user.getAddress());
		userDao.update(guest);
	}
	
	public void updatePic(Integer id, File pic) {
		UserInfo user = userDao.findById(id);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(pic);
			user.setPic(Hibernate.createBlob(fis));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		userDao.update(user);
	}
	
	public void updatePassword(String username, String password) {
		UserInfo guest = userDao.findByUsername(username);
		guest.setPassword(password);
		userDao.update(guest);
	}

	public UserInfo showUserInfo(Integer id) {
		return userDao.findById(id);
	}
	
	public UserInfo findByID(Integer id) {
		return userDao.findById(id);
	}
	
	public UserInfo findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public List<ReceiverInfo> showReceiverList(UserInfo user) {
		return receiverDao.findByUserInfo(user);
	}
	
	public ReceiverInfo showReceiver(Integer id) {
		return receiverDao.findById(id);
	}
	
	public void saveReceiver(ReceiverInfo receiver) {
		receiverDao.save(receiver);
	}
	
	public void updateReceiver(ReceiverInfo receiver) {
		receiverDao.update(receiver);
	}
	
	public void deleteReceiver(Integer id) {
		ReceiverInfo receiver = receiverDao.findById(id);
		receiverDao.delete(receiver);
	}
	
	
	
	public List<UserInfo> showUserInfoList() {
		return userDao.findByLimits("g");
	}
	public UserInfo findUserById(Integer id) {
		return userDao.findById(id);
	}
	public UserInfo findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}
	public void deleteGuest(Integer id){
		UserInfo guest = userDao.findById(id);
		userDao.delete(guest);
	}
}
