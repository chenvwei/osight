/*
 * Created on 2012-11-19
 */
package com.osight.framework.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.spi.StatementPreparer;
import org.hibernate.internal.SessionImpl;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.framework.exception.DAOException;
import com.osight.framework.pojos.PersistentObject;

/**
 * @author chenw
 * @version $Id$
 */
public class HibernateUtil {
	private static final String		PARAMNAME		= "ARG";
	private static final String		REGX			= "(?!:" + PARAMNAME + ")[?:](\\w){0,}";
	private static final Pattern	PATTERN			= Pattern.compile(".*" + REGX + ".*");
	SessionFactory					sessionFactory	= null;
	Logger							log				= LoggerFactory.getLogger(getClass());

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param factory
	 */
	public void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
	}

	public Session getSession() {
		return HibernateSession.getSession(getSessionFactory(), false);
	}

	public void save(PersistentObject obj) {
		try {
			if (obj.getId() == 0) {
				getSession().save(obj);
			} else {
				if (!getSession().contains(obj)) {
					try {
						PersistentObject vo = (PersistentObject) getSession().load(obj.getClass(), obj.getId());
						vo.setData(obj);
						obj = vo;
					} catch (ObjectNotFoundException e) {
						getSession().save(obj);
					}
				}
			}
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	public void update(PersistentObject obj) {
		try {
			if (!getSession().contains(obj)) {
				PersistentObject vo = (PersistentObject) getSession().load(obj.getClass(), obj.getId());
				vo.setData(obj);
				obj = vo;
			}
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	public void saveOrUpdate(Object obj) {
		try {
			getSession().saveOrUpdate(obj);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	public void saveOrUpdate(PersistentObject obj) {
		try {
			if (obj.getId() == 0) {
				getSession().save(obj);
			} else {
				boolean update = false;
				if (!getSession().contains(obj)) {
					try {
						// Object has been written to database, but instance
						// passed in
						// is not a persistent instance, so must be loaded into
						// session.
						PersistentObject vo = (PersistentObject) getSession().get(obj.getClass(), obj.getId());
						if (null != vo) {
							update = true;
							vo.setData(obj);
							obj = vo;
						}
					} catch (ObjectNotFoundException e) {
						// ID 可能是自己赋值的
					}
				} else {
					update = true;
				}

				if (update) {
					getSession().update(obj);
				} else
					getSession().save(obj);
			}
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * 
	 * @param id
	 * @param class1
	 * @return
	 */
	public Object getObject(Serializable id, Class<?> class1) {
		try {
			return getSession().get(class1, id);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(String sql, Object[] args, Type[] types) {
		try {
			int i = 0;
			while (PATTERN.matcher(sql).matches()) {
				sql = sql.replaceFirst(REGX, ":" + PARAMNAME + i++);
			}
			Query q = getSession().createQuery(sql);
			if (null != args && args.length > 0) {
				for (i = 0; i < args.length; i++) {
					q.setParameter(PARAMNAME + i, args[i], types[i]);
				}
			}
			return q.list();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * 一般用在分页里面
	 * 
	 * @param hql
	 * @param args
	 * @param types
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Object[] args, Type[] types, int firstResult, int maxResult) {
		try {
			int i = 0;
			while (PATTERN.matcher(hql).matches()) {
				hql = hql.replaceFirst(REGX, ":" + PARAMNAME + i++);
			}
			Query q = getSession().createQuery(hql);
			if (null != args && args.length > 0) {
				for (i = 0; i < args.length; i++) {
					q.setParameter(PARAMNAME + i, args[i], types[i]);
				}
			}
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResult);
			return q.list();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * 获取hql对应的记录总数
	 * 
	 * @param hql
	 * @param args
	 * @param types
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public long getCount(String hql, Object[] args, Type[] types) {
		try {
			int index = hql.indexOf("from");
			if (index != -1) {
				hql = "select count(*) " + hql.substring(index);
			} else {
				throw new DAOException("无效的hql语句:" + hql);
			}
			int i = 0;
			while (PATTERN.matcher(hql).matches()) {
				hql = hql.replaceFirst(REGX, ":" + PARAMNAME + i++);
			}
			Query q = getSession().createQuery(hql);
			if (null != args && args.length > 0) {
				for (i = 0; i < args.length; i++) {
					q.setParameter(PARAMNAME + i, args[i], types[i]);
				}
			}
			List<Number> list = q.list();
			long count = list.size() > 0 ? ((Number) list.get(0)).longValue() : 0;
			return count;
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * 使用SQL语句
	 * 
	 * @param sql
	 * @param args
	 * @param types
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findUseSql(String sql, Object[] args, Type[] types, int firstResult, int maxResult) {
		try {
			SQLQuery q = getSession().createSQLQuery(sql);
			if (null != args && args.length > 0)
				q.setParameters(args, types);
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResult);
			return q.list();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * 使用SQL语句
	 * 
	 * @param sql
	 * @param args
	 * @param types
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public long getCountUseSql(String sql, Object[] args, Type[] types) {
		try {
			int index = sql.indexOf("from");
			if (index != -1) {
				sql = "select count(*) " + sql.substring(index);
			} else {
				throw new DAOException("无效的hql语句:" + sql);
			}

			SQLQuery q = getSession().createSQLQuery(sql);
			if (null != args && args.length > 0)
				q.setParameters(args, types);
			List<Number> list = q.list();
			long count = list.size() > 0 ? ((Number) list.get(0)).longValue() : 0;
			return count;
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(String sql) {
		try {
			Query q = getSession().createQuery(sql);
			return q.list();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	public int delete(String sql, Object[] args, Type[] types) {
		try {
			int i = 0;
			while (PATTERN.matcher(sql).matches()) {
				sql = sql.replaceFirst(REGX, ":" + PARAMNAME + i++);
			}
			Query q = getSession().createQuery(sql);
			if (null != args && args.length > 0) {
				for (i = 0; i < args.length; i++) {
					q.setParameter(PARAMNAME + i, args[i], types[i]);
				}
			}
			return q.executeUpdate();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	public void delete(Object obj) {
		try {
			if (!getSession().contains(obj)) {
				try {
					PersistentObject vo = (PersistentObject) getSession().get(obj.getClass(),
							((PersistentObject) obj).getId());
					if (null != vo) {
						getSession().delete(vo);
					}
				} catch (ObjectNotFoundException e) {
					log.warn("对象{}不存在", obj);
				}
			} else
				getSession().delete(obj);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	public StatementPreparer getStatementPreparer() {
		return ((SessionImpl) getSession()).getTransactionCoordinator().getJdbcCoordinator().getStatementPreparer();
	}
}
