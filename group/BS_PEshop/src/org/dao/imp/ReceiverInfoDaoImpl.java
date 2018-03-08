package org.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dao.ReceiverInfoDAO;
import org.hibernate.LockMode;
import org.model.ReceiverInfo;
import org.model.UserInfo;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access String (DAO) providing persistence and search support for
 * ReceiverInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.model.ReceiverInfo
 * @author Real.Yi
 */
public class ReceiverInfoDaoImpl extends HibernateDaoSupport implements ReceiverInfoDAO {
	
	private static final Log log = LogFactory.getLog(ReceiverInfoDaoImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String ADDRESS = "address";
	public static final String USERINFO = "userInfo";

	protected void initDao() {
		// do nothing
	}

	public void save(ReceiverInfo transientInstance) {
		log.debug("saving ReceiverInfo instance");
		try {
			Serializable result = getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
			System.out.println((Integer) result);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(ReceiverInfo transientInstance) {
		log.debug("saving ReceiverInfo instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReceiverInfo persistentInstance) {
		log.debug("deleting ReceiverInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReceiverInfo findById(Integer id) {
		log.debug("getting ReceiverInfo instance with id: " + id);
		try {
			ReceiverInfo instance = (ReceiverInfo) getHibernateTemplate().get(
					"org.model.ReceiverInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ReceiverInfo> findByUserInfo(UserInfo instance) {
		return findByProperty(USERINFO, instance);
	}

	@SuppressWarnings("unchecked")
	public List<ReceiverInfo> findByExample(ReceiverInfo instance) {
		log.debug("finding ReceiverInfo instance by example");
		try {
			List<ReceiverInfo> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ReceiverInfo> findByProperty(String propertyName, Object value) {
		log.debug("finding ReceiverInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReceiverInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ReceiverInfo> findByName(String name) {
		return findByProperty(NAME, name);
	}

	public List<ReceiverInfo> findByPhone(String phone) {
		return findByProperty(PHONE, phone);
	}

	public List<ReceiverInfo> findByAddress(String address) {
		return findByProperty(ADDRESS, address);
	}

	@SuppressWarnings("unchecked")
	public List<ReceiverInfo> findAll() {
		log.debug("finding all ReceiverInfo instances");
		try {
			String queryString = "from ReceiverInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReceiverInfo merge(ReceiverInfo detachedInstance) {
		log.debug("merging ReceiverInfo instance");
		try {
			ReceiverInfo result = (ReceiverInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReceiverInfo instance) {
		log.debug("attaching dirty ReceiverInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReceiverInfo instance) {
		log.debug("attaching clean ReceiverInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}