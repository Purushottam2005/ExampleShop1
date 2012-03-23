package generics;


import java.util.List;

import classes.Artikel;
import interfaces.IArtikelDAO;

/*
 * Generic DAO for Artikel-Class. Implements the methods from IArtikelDAO
 */
public class ArtikelDAO extends GenericDAO implements IArtikelDAO {
	/**
	 * returns all Artikel in a list
	 */
	@Override
	public List<Artikel> getAll() {
		List<Artikel> artikelliste = findByNamedParam(Artikel.class, "from Artikel art order by art.bezeichnung asc");
		return artikelliste;
	}

	/**
	 * returns Artikel by Artikelgruppen_id. Does not work! 
	 */
	@Override
	public List<Artikel> getByGrpId(int id) {
		if(id==-1){
			return getAll();
		}else{
			String[] paramNames = new String[1];
			paramNames[0] = "artikelgruppe_id";
			Object[] values = new Object[1];
			values[0] = id;
			//return findByNamedParam(Artikel.class, "select a from Artikel a where a.artikelgruppe = :artikelgruppe_id", paramNames, values);
			return getAll();
		}
	}

}
