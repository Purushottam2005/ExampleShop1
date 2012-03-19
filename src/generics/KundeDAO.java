package generics;

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

	@Override
	public boolean checkPassword(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Kunde getKundeByEmailPasswort(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
