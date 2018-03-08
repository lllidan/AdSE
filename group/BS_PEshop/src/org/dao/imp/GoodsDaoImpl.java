package org.dao.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dao.GoodsDAO;
import org.hibernate.LockMode;
import org.model.Goods;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Goods
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.model.Goods
 * @author Real.Yi
 */
public class GoodsDaoImpl extends HibernateDaoSupport implements GoodsDAO {
	
	private static final Log log = LogFactory.getLog(GoodsDaoImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String ATTRIBUTE = "attribute";
	public static final String PRICE = "price";
	public static final String LIST_PRICE = "listPrice";
	public static final String TYPE = "type";
	public static final String FIELD = "field";
	public static final String STOCK = "stock";
	public static final String SALE = "sale";

	protected void initDao() {
		// do nothing
	}

	public void save(Goods transientInstance) {
		log.debug("saving Goods instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Goods transientInstance) {
		log.debug("saving Goods instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Goods persistentInstance) {
		log.debug("deleting Goods instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Goods findById(java.lang.Integer id) {
		log.debug("getting Goods instance with id: " + id);
		try {
			Goods instance = (Goods) getHibernateTemplate().get(
					"org.model.Goods", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Goods> findByExample(Goods instance) {
		log.debug("finding Goods instance by example");
		try {
			List<Goods> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Goods> findByProperty(String propertyName, Object value) {
		log.debug("finding Goods instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Goods as model where model."
					+ propertyName + " like ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Goods> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Goods> findByAttribute(Object attribute) {
		return findByProperty(ATTRIBUTE, attribute);
	}

	public List<Goods> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List<Goods> findByListPrice(Object listPrice) {
		return findByProperty(LIST_PRICE, listPrice);
	}

	public List<Goods> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Goods> findByField(Object field) {
		return findByProperty(FIELD, field);
	}

	public List<Goods> findByStock(Object stock) {
		return findByProperty(STOCK, stock);
	}

	public List<Goods> findBySale(Object sale) {
		return findByProperty(SALE, sale);
	}

	@SuppressWarnings("unchecked")
	public List<Goods> findAll() {
		log.debug("finding all Goods instances");
		try {
			String queryString = "from Goods";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Goods merge(Goods detachedInstance) {
		log.debug("merging Goods instance");
		try {
			Goods result = (Goods) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Goods instance) {
		log.debug("attaching dirty Goods instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Goods instance) {
		log.debug("attaching clean Goods instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}