package generics;

import classes.Warenkorb;
import interfaces.IWarenkorbDAO;

public class WarenkorbDAO extends GenericDAO implements IWarenkorbDAO {

	@Override
	public void doBestellung(Warenkorb wk) {
		//Aufrufen der SP die eine Bestellung erzeugt

	}

}
