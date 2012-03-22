package interfaces;

import classes.Bestellung;

public interface IBestellungDAO extends IGenericDAO{
	public void createBestellung(Bestellung b);
}
