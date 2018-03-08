package org.dao;

import java.util.List;

import org.model.Distribute;

public interface DistributeDAO {
	
	public void save(Distribute transientInstance);
	
	public void update(Distribute transientInstance);
	
	public void delete(Distribute persistentInstance);

	public Distribute findById(java.lang.Integer id);
	
	public List<Distribute>findByExample(Distribute instance);
	
	public List<Distribute>findByProperty(String propertyName, Object value);

	public List<Distribute>findByExpress(Object express);

	public List<Distribute>findByCourier(Object courier);

	public List<Distribute>findByLocation(Object location);

	public List<Distribute>findByDelivery(Object delivery);
	
	public List<Distribute>findAll();

	public Distribute merge(Distribute detachedInstance);

	public void attachDirty(Distribute instance);

	public void attachClean(Distribute instance);
}
