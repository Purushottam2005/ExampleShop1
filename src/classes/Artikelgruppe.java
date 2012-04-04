package classes;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
/**
 * 
 * @author andreas monschau
 * 
 * class in mano-to-one-relation to class artikel
 *
 */
@Entity
@Table(name="ART_GRP")
public class Artikelgruppe {
	private int id;
	private String bezeichnung;
	private String info;
	private Set<Artikel> artikel;
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column(name="BEZEICHNUNG", length=1024)	
	@OrderColumn(name="pos")
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	@Column(name="INFO", length=1024)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@OneToMany(mappedBy="artikelgruppe")
	public Set<Artikel> getArtikel() {
		return artikel;
	}
	public void setArtikel(Set<Artikel> artikel) {
		this.artikel = artikel;
	}
}
