package interfaces;

import classes.Kunde;

public interface IKundeDAO extends IGenericDAO{
	public boolean checkPassword(String email, String password);
	public Kunde getKundeByEmailPasswort(String email, String password);
}
