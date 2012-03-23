package generics;

import java.util.List;

import classes.Artikel;
import classes.Artikelgruppe;
import interfaces.IArtikelgruppenDAO;
/*
 * Generic DAO for Artikelgruppen-Class. Implements the methods from IArtikelgruppenDAO
 */
public class ArtikelgruppenDAO extends GenericDAO implements IArtikelgruppenDAO {
	/**
	 * returns all Artikelgruppen as List
	 */
	@Override
	public List<Artikelgruppe> getAlleArtGrp() {
		return findByNamedParam(Artikelgruppe.class, "from Artikelgruppe art order by art.bezeichnung asc");
	}
}
