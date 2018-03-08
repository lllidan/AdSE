package org.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dao.OrderDAO;
import org.hibernate.LockMode;
import org.model.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Order
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.model.Order
 * @author Real.Yi
 */
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDAO {
	private static final Log log = LogFactory.getLog(OrderDaoImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String ADDRESS = "address";
	public static final String TOTALPRICES = "totalprices";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public Integer save(Order transientInstance) {
		log.debug("saving Order instance");
		try {
			Serializable result = getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
			return (Integer) result;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Order transientInstance) {
		log.debug("saving Order instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Order persistentInstance) {
		log.debug("deleting Order instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Order findById(java.lang.Integer id) {
		log.debug("getting Order instance with id: " + id);
		try {
			Order instance = (Order) getHibernateTemplate().get(
					"org.model.Order", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Order> findByExample(Order instance) {
		log.debug("finding Order instance by example");
		try {
			List<Order> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Order> findByProperty(String propertyName, Object value) {
		log.debug("finding Order instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Order as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> findByUserAndTime(Object user, Object time) {
		log.debug("finding Order instance with property: userInfo, value: " + user
				+ " & time: " + time);
		try {
			String queryString = "from Order as model where model.userInfo= ?"
					+ " and model.time= ?";
			return getHibernateTemplate().find(queryString, new Object[]{user, time});
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Order> findByTotalprices(Object totalprices) {
		return findByProperty(TOTALPRICES, totalprices);
	}

	public List<Order> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	@SuppressWarnings("unchecked")
	public List<Order> findAll() {
		log.debug("finding all Order instances");
		try {
			String queryString = "from Order";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Order merge(Order detachedInstance) {
		log.debug("merging Order instance");
		try {
			Order result = (Order) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Order instance) {
		log.debug("attaching dirty Order instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Order instance) {
		log.debug("attaching clean Order instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}