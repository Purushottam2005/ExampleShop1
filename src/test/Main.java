package test;

import classes.Kunde;
import generics.KundeDAO;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KundeDAO dao = new KundeDAO();
		Kunde k = new Kunde();
		k.setAdresse("blaadresse");
		k.setEmail("none@none.de");
		k.setPassword("test");
		dao.save(k);

	}

}
