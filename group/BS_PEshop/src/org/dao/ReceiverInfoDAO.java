package org.dao;

import java.util.List;

import org.model.ReceiverInfo;
import org.model.UserInfo;

public interface ReceiverInfoDAO {
	
	public void save(ReceiverInfo transientInstance);
	
	public void update(ReceiverInfo transientInstance);

	public void delete(ReceiverInfo persistentInstance);

	public ReceiverInfo findById(Integer id);
	
	public List<ReceiverInfo> findByUserInfo(UserInfo instance);

	public List<ReceiverInfo> findByExample(ReceiverInfo instance);

	public List<ReceiverInfo> findByProperty(String propertyName, Object value);
	
	public List<ReceiverInfo> findByName(String name);
	
	public List<ReceiverInfo> findByPhone(String phone);

	public List<ReceiverInfo> findByAddress(String address);

	public List<ReceiverInfo> findAll();

	public ReceiverInfo merge(ReceiverInfo detachedInstance);

	public void attachDirty(ReceiverInfo instance);

	public void attachClean(ReceiverInfo instance);
	
}
