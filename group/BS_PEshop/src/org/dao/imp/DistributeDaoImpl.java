package org.dao.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dao.DistributeDAO;
import org.hibernate.LockMode;
import org.model.Distribute;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Distribute entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.model.Distribute
 * @author Real.Yi
 */
public class DistributeDaoImpl extends HibernateDaoSupport implements DistributeDAO {
	
	private static final Log log = LogFactory.getLog(DistributeDaoImpl.class);
	// property constants
	public static final String EXPRESS = "express";
	public static final String COURIER = "courier";
	public static final String LOCATION = "location";
	public static final String DELIVERY = "delivery";

	protected void initDao() {
		// do nothing
	}

	public void save(Distribute transientInstance) {
		log.debug("saving Distribute instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Distribute transientInstance) {
		log.debug("saving Distribute instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Distribute persistentInstance) {
		log.debug("deleting Distribute instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Distribute findById(java.lang.Integer id) {
		log.debug("getting Distribute instance with id: " + id);
		try {
			Distribute instance = (Distribute) getHibernateTemplate().get(
					"org.model.Distribute", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Distribute>findByExample(Distribute instance) {
		log.debug("finding Distribute instance by example");
		try {
			List<Distribute>results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Distribute>findByProperty(String propertyName, Object value) {
		log.debug("finding Distribute instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Distribute as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Distribute>findByExpress(Object express) {
		return findByProperty(EXPRESS, express);
	}

	public List<Distribute>findByCourier(Object courier) {
		return findByProperty(COURIER, courier);
	}

	public List<Distribute>findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List<Distribute>findByDelivery(Object delivery) {
		return findByProperty(DELIVERY, delivery);
	}

	@SuppressWarnings("unchecked")
	public List<Distribute>findAll() {
		log.debug("finding all Distribute instances");
		try {
			String queryString = "from Distribute";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Distribute merge(Distribute detachedInstance) {
		log.debug("merging Distribute instance");
		try {
			Distribute result = (Distribute) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Distribute instance) {
		log.debug("attaching dirty Distribute instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Distribute instance) {
		log.debug("attaching clean Distribute instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}