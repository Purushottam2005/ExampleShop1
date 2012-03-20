package interfaces;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

public interface IGenericDAO {
	public void setSessionFactory(SessionFactory sessionFactory);
	public <T>List <T>findAll(Class<T> entityClass) throws DataAccessException;
	public <T> T findById(Class<T> entityClass, Long id) throws DataAccessException;
	public <T> void save(T entity) throws DataAccessException;
	public <T> void update(T entity) throws DataAccessException;
	public <T> void remove(T entity) throws DataAccessException;
	public <T> List<T> findByNamedQuery(Class <T> entitiyClass, String queryName)throws DataAccessException;
	public <T> List<T> findByNamedParam(Class <T> entitiyClass, String queryString, String paramName, Object value)throws DataAccessException;
	public <T> List<T> findByNamedQueryAndNamedParam(Class <T> entityClass, String queryName, String[] paramNames, Object[] values) throws DataAccessException;
	public <T> List<T> findByNamedParam(Class <T> entityClass, String queryString, String[] paramNames, Object[] values)throws DataAccessException;
	public <T> T findByNamesParam(Class <T> entityClass, String queryString, String[] paramNames, Object[] values)throws DataAccessException;
}
