package org.dao;

import java.util.List;

import org.model.Goods;

public interface GoodsDAO {

	public void save(Goods transientInstance);
	
	public void update(Goods transientInstance);

	public void delete(Goods persistentInstance);

	public Goods findById(java.lang.Integer id);

	public List<Goods> findByExample(Goods instance);

	public List<Goods> findByProperty(String propertyName, Object value);

	public List<Goods> findByName(Object name);

	public List<Goods> findByAttribute(Object attribute);

	public List<Goods> findByPrice(Object price);

	public List<Goods> findByListPrice(Object listPrice);

	public List<Goods> findByType(Object type);

	public List<Goods> findByField(Object field);

	public List<Goods> findByStock(Object stock);

	public List<Goods> findBySale(Object sale);
	
	public List<Goods> findAll();

	public Goods merge(Goods detachedInstance);

	public void attachDirty(Goods instance);

	public void attachClean(Goods instance);
}
