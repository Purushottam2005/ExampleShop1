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
}
