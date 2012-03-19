package generics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import interfaces.IGenericDAO;
/**
 * 
 * @author andreas monschau
 * 
 * Generic class for basic crud-operations. specified in derived classes
 *
 */
public class GenericDAO implements IGenericDAO {
	
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate =  new HibernateTemplate(sessionFactory);

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
			getHibernateTemplate().save(entity);
			getHibernateTemplate().flush();
		}

	}

	@Override
	public <T> void update(T entity) throws DataAccessException {
		if(!(entity==null)){
			getHibernateTemplate().update(entity);	
			getHibernateTemplate().flush();
		}

	}

	@Override
	public <T> void remove(T entity) throws DataAccessException {
		getHibernateTemplate().delete(entity);
		getHibernateTemplate().flush();
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
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}

}
