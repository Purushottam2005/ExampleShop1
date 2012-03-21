package classes;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author andreas monschau
 * 
 * Non-Entity class Warenkorb. It´s stored in the user-session. After ordering, a called SP creates Bestellung.
 *
 */
public class Warenkorb {
	private Kunde kunde;
	private List<Artikel> artikel = new ArrayList<Artikel>();
	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	public List<Artikel> getArtikel(){
		return this.artikel;
	}

}
