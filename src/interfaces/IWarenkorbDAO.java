package interfaces;

import classes.Bestellung;
import classes.Kunde;
import classes.Warenkorb;

public interface IWarenkorbDAO extends IGenericDAO{
	public double calculateWK(Warenkorb wk);
	Bestellung doBestellung(Warenkorb wk);
}
