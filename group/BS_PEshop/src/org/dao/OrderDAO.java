package org.dao;

import java.util.List;

import org.model.Order;

public interface OrderDAO {
	
	public Integer save(Order transientInstance);

	public void update(Order transientInstance);
	
	public void delete(Order persistentInstance);

	public Order findById(Integer id);

	public List<Order> findByExample(Order instance);

	public List<Order> findByProperty(String propertyName, Object value);
	
	public List<Order> findByUserAndTime(Object user, Object time);

	public List<Order> findByTotalprices(Object totalprices);

	public List<Order> findByStatus(Object status);

	public List<Order> findAll();

	public Order merge(Order detachedInstance);

	public void attachDirty(Order instance);

	public void attachClean(Order instance);
}
