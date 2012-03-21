package interfaces;

import java.util.List;

import classes.Artikelgruppe;

public interface IArtikelgruppenDAO extends IGenericDAO {
	public List<Artikelgruppe> getAlleArtGrp();
}
