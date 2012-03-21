package generics;

import java.util.List;

import classes.Artikelgruppe;
import interfaces.IArtikelgruppenDAO;

public class ArtikelgruppenDAO extends GenericDAO implements IArtikelgruppenDAO {

	@Override
	public List<Artikelgruppe> getAlleArtGrp() {
		// TODO Auto-generated method stub
		return findAll(Artikelgruppe.class);
	}



}
