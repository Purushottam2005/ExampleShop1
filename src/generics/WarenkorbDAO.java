package generics;

import classes.Artikel;
import classes.Warenkorb;
import interfaces.IWarenkorbDAO;

public class WarenkorbDAO extends GenericDAO implements IWarenkorbDAO {

	@Override
	public void doBestellung(Warenkorb wk) {
		//Aufrufen der SP die eine Bestellung erzeugt

	}

	@Override
	public double calculateWK(Warenkorb wk) {
		double result = 0;
		for(Artikel art:wk.getArtikel()){
			result+=art.getVk_brutto();
		}
		
		return result;
	}
	


}
