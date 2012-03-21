package interfaces;

import classes.Warenkorb;

public interface IWarenkorbDAO extends IGenericDAO{
	public void doBestellung(Warenkorb wk);
	public double calculateWK(Warenkorb wk);
}
