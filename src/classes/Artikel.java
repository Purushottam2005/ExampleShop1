package classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author andreas monschau
 * 
 * Entity-Class Artikel mapped to Database-Table "ART"
 *
 */

@Entity
@Table(name="ART")
public class Artikel {
	private int id;
	private String bezeichnung;
	private String info;
	private double vk_netto;
	private double vk_brutto;
	private String img_url;
	private Artikelgruppe artikelgruppe;
	@Id
	@GeneratedValue
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="BEZEICHNUNG")
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	@Column(name="INFO")
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Column(name="VK_NETTO")
	public double getVk_netto() {
		return vk_netto;
	}
	public void setVk_netto(double vk_netto) {
		this.vk_netto = vk_netto;
	}
	@Column(name="VK_BRUTTO")
	public double getVk_brutto() {
		return vk_brutto;
	}
	public void setVk_brutto(double d) {
		this.vk_brutto = d;
	}
	@Column(name="IMG_URL")
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	@ManyToOne(cascade = javax.persistence.CascadeType.ALL)
	public Artikelgruppe getArtikelgruppe() {
		return artikelgruppe;
	}
	public void setArtikelgruppe(Artikelgruppe artikelgruppe) {
		this.artikelgruppe = artikelgruppe;
	}
}
