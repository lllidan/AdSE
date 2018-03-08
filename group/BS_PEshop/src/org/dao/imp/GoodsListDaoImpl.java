package org.dao.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dao.GoodsListDAO;
import org.hibernate.LockMode;
import org.model.GoodsList;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * GoodsList entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.model.GoodsList
 * @author Real.Yi
 */
public class GoodsListDaoImpl extends HibernateDaoSupport implements GoodsListDAO {
	
	private static final Log log = LogFactory.getLog(GoodsListDaoImpl.class);
	// property constants
	public static final String PRICE = "price";
	public static final String AMOUNT = "amount";

	protected void initDao() {
		// do nothing
	}

	public void save(GoodsList transientInstance) {
		log.debug("saving GoodsList instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(GoodsList transientInstance) {
		log.debug("saving GoodsList instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GoodsList persistentInstance) {
		log.debug("deleting GoodsList instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GoodsList findById(java.lang.Integer id) {
		log.debug("getting GoodsList instance with id: " + id);
		try {
			GoodsList instance = (GoodsList) getHibernateTemplate().get(
					"org.model.GoodsList", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GoodsList> findByExample(GoodsList instance) {
		log.debug("finding GoodsList instance by example");
		try {
			List<GoodsList> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GoodsList> findByProperty(String propertyName, Object value) {
		log.debug("finding GoodsList instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from GoodsList as model where model."
				+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<GoodsList> findByUserAndGoods(Object user, Object goods) {
		log.debug("finding GoodsList instance with property: userInfo, value: " 
				+ user + " & goods, value: " + goods + " & order, value: null");
		try {
			String queryString = "from GoodsList as model where model.userInfo=?"
					+ " and model.goods=?" + " and model.order is null";
			System.out.println(queryString);
			List<GoodsList> list = getHibernateTemplate().find(queryString, new Object[]{user,goods});
			if(!list.isEmpty())
				System.out.print("list.id = " + list.get(0).getId());
			return getHibernateTemplate().find(queryString, new Object[]{user,goods});
			
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<GoodsList> findByUser(Object user) {
		log.debug("finding GoodsList instance with property: userInfo, value: " 
				+ user + " & order, value: null");
		try {
			String queryString = "from GoodsList as model where model.userInfo=? "
					+ "and model.order is null";
			System.out.println(queryString);
			return getHibernateTemplate().find(queryString, user);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GoodsList> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List<GoodsList> findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsList> findAll() {
		log.debug("finding all GoodsList instances");
		try {
			String queryString = "from GoodsList";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GoodsList merge(GoodsList detachedInstance) {
		log.debug("merging GoodsList instance");
		try {
			GoodsList result = (GoodsList) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GoodsList instance) {
		log.debug("attaching dirty GoodsList instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GoodsList instance) {
		log.debug("attaching clean GoodsList instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}