package generics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import classes.Bestellung;
import classes.Artikel;
import interfaces.IBestellungDAO;

/**
 * 
 * @author andreas monschau
 * 
 * "DAO" for creating written order after firing doBestellung()
 *
 */
public class BestellungDAO extends GenericDAO implements IBestellungDAO {

	public void createBestellung(Bestellung b) {
		try{			
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Bestellung-"+ b.getKunde().getVorname() +".txt")));
			out.println("Bestellung vom " + b.getBestelldatum());
			out.println("Kunde: "+ b.getKunde().getVorname() + " - " + b.getKunde().getEmail());
			out.println();
			out.println("Bestellte Artikel:");
			for(Artikel a : b.getArtikel()){
				out.println(a.getBezeichnung() + " - " + a.getVk_brutto());
			}
			out.println("Gesamtpreis: " + b.getGesamtpreis());
			out.println();
			out.println();
			out.println("Lieferadresse:");
			out.println(b.getKunde().getVorname() + " " + b.getKunde().getAdresse());
			out.flush();
			out.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}

}
