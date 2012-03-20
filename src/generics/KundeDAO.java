package generics;

import javax.swing.JOptionPane;

import classes.Kunde;
import interfaces.IKundeDAO;
/**
 * 
 * @author andreas monschau
 * 
 * Extends GenericDAO. primarilly used for validation-operations on kunde-object
 *
 */
public class KundeDAO extends GenericDAO implements IKundeDAO {

	public KundeDAO() {

	}

	@Override
	public boolean checkPassword(String email, String password) {
		System.out.println("checkpassword");
		String[] paramNames = new String[2];
		paramNames[0] = "email";
		paramNames[1] = "password";
		Object[] values = new Object[2];
		values[0] = email;
		values[1] = password;
		return (findByNamedQueryAndNamedParam(Kunde.class, "findKundeByEmailAndPassword", paramNames, values).size()==1);		
	}

	@Override
	public Kunde getKundeByEmailPasswort(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
