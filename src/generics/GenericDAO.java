package generics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import classes.Kunde;

import interfaces.IGenericDAO;
/**
 * 
 * @author andreas monschau
 * 
 * Generic class for basic crud-operations. specified in derived classes
 *
 */
public class GenericDAO implements IGenericDAO {
	
	public GenericDAO() {
	}

	private HibernateTemplate hibernateTemplate;
	private Session session;

	
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate =  new HibernateTemplate(sessionFactory);
		this.session = SessionFactoryUtils.getSession(sessionFactory, true);
	}

	@Override
	public <T> List<T> findAll(Class<T> entityClass) throws DataAccessException {
		 getHibernateTemplate().setCacheQueries(true);
		 List<T> results = getHibernateTemplate().loadAll(entityClass);
		 Set<T> set = new HashSet<T>(results);
		 results = new ArrayList<T>(set);
		 return results;	
	}

	@Override
	public <T> T findById(Class<T> entityClass, Long id)
			throws DataAccessException {
		 getHibernateTemplate().setCacheQueries(true);
		 T object = (T) getHibernateTemplate().get(entityClass, id);
		 if(object == null){
	  	   
		   throw new ObjectRetrievalFailureException(entityClass, id);
		 }else {
		   return object;
		 }
	}

	@Override
	public <T> void save(T entity) throws DataAccessException {
		if(!(entity==null)){			
			Transaction tx = session.getTransaction();
			tx.begin();
			getHibernateTemplate().saveOrUpdate(entity);
			tx.commit();
		
		}

	}

	@Override
	public <T> void update(T entity) throws DataAccessException {
		if(!(entity==null)){
			Transaction tx = session.getTransaction();
			tx.begin();
			getHibernateTemplate().update(entity);	
			tx.commit();
		}

	}

	@Override
	public <T> void remove(T entity) throws DataAccessException {
		Transaction tx = session.getTransaction();
		tx.begin();
		getHibernateTemplate().delete(entity);
		tx.commit();
	}

	@Override
	public <T> List<T> findByNamedQuery(Class<T> entitiyClass, String queryName)
			throws DataAccessException {
		List<T> results = (List<T>)getHibernateTemplate().findByNamedQuery(queryName);
		return results;
	}
	private HibernateTemplate getHibernateTemplate() {
		// TODO Auto-generated method stub
		return hibernateTemplate;
	}
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate, SessionFactory sessionFactory){
		this.hibernateTemplate = hibernateTemplate;
		this.session =SessionFactoryUtils.getSession(sessionFactory, true);
	}

	@Override
	public <T> List<T> findByNamedParam(Class<T> entitiyClass,
			String queryString, String paramName, Object value)
			throws DataAccessException {
		List<T> results = (List<T>)getHibernateTemplate().findByNamedParam(queryString, paramName, value);
		return results;
	}

	@Override
	public <T> List<T> findByNamedQueryAndNamedParam(Class<T> entityClass,
			String queryName, String[] paramNames, Object[] values)
			throws DataAccessException {
		try{
			List<T> results = (List<T>)getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
			return results;			
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}

	}



}
