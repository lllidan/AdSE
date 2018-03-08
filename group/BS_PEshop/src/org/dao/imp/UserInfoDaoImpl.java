package org.dao.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dao.UserInfoDAO;
import org.hibernate.LockMode;
import org.model.UserInfo;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access String (DAO) providing persistence and search support for
 * UserInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see org.model.UserInfo
 * @author Real.Yi
 */
public class UserInfoDaoImpl extends HibernateDaoSupport implements UserInfoDAO {
	private static final Log log = LogFactory.getLog(UserInfoDaoImpl.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String NAME = "name";
	public static final String SEX = "sex";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String ADDRESS = "address";
	public static final String PASSWORD = "password";
	public static final String LIMITS = "limits";

	protected void initDao() {
		// do nothing
	}

	public void save(UserInfo transientInstance) {
		log.debug("saving UserInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(UserInfo transientInstance) {
		log.debug("saving UserInfo instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserInfo persistentInstance) {
		log.debug("deleting UserInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserInfo findById(Integer id) {
		log.debug("getting UserInfo instance with id: " + id);
		try {
			UserInfo instance = (UserInfo) getHibernateTemplate().get(
					"org.model.UserInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public UserInfo findByUsername(String username) {
		List<UserInfo> results = findByProperty(USERNAME, username);
		System.out.println(results.size());
		UserInfo result = results.isEmpty() ? null : results.get(0);
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> findByProperty(String propertyName, Object value) {
		log.debug("finding UserInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List<UserInfo>findByName(String name) {
		return findByProperty(NAME, name);
	}

	public List<UserInfo>findBySex(String sex) {
		return findByProperty(SEX, sex);
	}

	public List<UserInfo>findByEmail(String email) {
		return findByProperty(EMAIL, email);
	}

	public List<UserInfo>findByPhone(String phone) {
		return findByProperty(PHONE, phone);
	}

	public List<UserInfo>findByAddress(String address) {
		return findByProperty(ADDRESS, address);
	}

	public List<UserInfo>findByPassword(String password) {
		return findByProperty(PASSWORD, password);
	}

	public List<UserInfo>findByLimits(String limits) {
		return findByProperty(LIMITS, limits);
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo>findAll() {
		log.debug("finding all UserInfo instances");
		try {
			String queryString = "from UserInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserInfo merge(UserInfo detachedInstance) {
		log.debug("merging UserInfo instance");
		try {
			UserInfo result = (UserInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserInfo instance) {
		log.debug("attaching dirty UserInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserInfo instance) {
		log.debug("attaching clean UserInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}