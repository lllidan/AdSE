package org.dao;

import java.util.List;

import org.model.GoodsList;

public interface GoodsListDAO {
	
	public void save(GoodsList transientInstance);
	
	public void update(GoodsList transientInstance);

	public void delete(GoodsList persistentInstance);

	public GoodsList findById(Integer id);
	
	public List<GoodsList> findByExample(GoodsList instance);
	
	public List<GoodsList> findByProperty(String propertyName, Object value);
	
	public List<GoodsList> findByUserAndGoods(Object user, Object goods);
	
	public List<GoodsList> findByUser(Object user);

	public List<GoodsList> findByPrice(Object price);
	
	public List<GoodsList> findByAmount(Object amount);
	
	public List<GoodsList> findAll();

	public GoodsList merge(GoodsList detachedInstance);

	public void attachDirty(GoodsList instance);

	public void attachClean(GoodsList instance);
}
