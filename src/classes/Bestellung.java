package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author andreas monschau
 * 
 * dummy-class used for creating a paperbased bestellung (order)
 *
 */


public class Bestellung {
	private int id;
	private String bestelldatum;
	private List<Artikel> artikel = new ArrayList<Artikel>();
	private double gesamtpreis;
	private String adresse;
	private Kunde kunde;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBestelldatum() {
		return bestelldatum;
	}
	public void setBestelldatum(String string) {
		this.bestelldatum = string;
	}

	public List<Artikel> getArtikel() {
		return artikel;
	}
	public void setArtikel(List<Artikel> artikel) {
		this.artikel = artikel;
	}

	public double getGesamtpreis() {
		return gesamtpreis;
	}
	public void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}

	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
}
