package org.dao;

import java.util.List;

import org.model.UserInfo;

public interface UserInfoDAO {
    	
    public void save(UserInfo transientInstance);
    
    public void update(UserInfo transientInstance);
    
	public void delete(UserInfo persistentInstance);
    
    public UserInfo findById(Integer id);

	public UserInfo findByUsername(String username);
    
    public List<UserInfo> findByProperty(String propertyName, Object value);

	public List<UserInfo> findByName(String name);
	
	public List<UserInfo> findBySex(String sex);
	
	public List<UserInfo> findByEmail(String email);
	
	public List<UserInfo> findByPhone(String phone);
	
	public List<UserInfo> findByAddress(String address);
	
	public List<UserInfo> findByPassword(String password);
	
	public List<UserInfo> findByLimits(String limits);
	
	public List<UserInfo> findAll();
	
    public UserInfo merge(UserInfo detachedInstance);

    public void attachDirty(UserInfo instance);
    
    public void attachClean(UserInfo instance);

}