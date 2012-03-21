package interfaces;
import java.util.List;

import classes.Artikel;


public interface IArtikelDAO extends IGenericDAO {
	public List<Artikel> getAll();
	public List<Artikel> getByGrpId(int id);
}
