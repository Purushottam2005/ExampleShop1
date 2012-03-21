package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import classes.Artikel;
import interfaces.IArtikelDAO;

public class ArtikelDAO extends GenericDAO implements IArtikelDAO {

	@Override
	public List<Artikel> getAll() {
		List<Artikel> artikelliste = findAll(Artikel.class);
		Collections.sort(artikelliste);
		return artikelliste;
	}

	@Override
	public List<Artikel> getByGrpId(int id) {
		if(id==-1){
			return getAll();
		}else{
			System.out.println(id);
			String[] paramNames = new String[1];
			paramNames[0] = "artikelgruppe_id";
			Object[] values = new Object[1];
			values[0] = id;
			//return findByNamedQueryAndNamedParam(Artikel.class, "findArtikelByGrpId", paramNames, values);7
			return getAll();
		}
	}

}
