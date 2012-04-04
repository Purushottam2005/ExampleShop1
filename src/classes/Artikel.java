package classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;


/**
 * 
 * @author andreas monschau
 * 
 * Entity-Class Artikel mapped to Database-Table "ART"
 *
 */


@Entity
@Table(name="ARTIKEL")
public class Artikel{
	private int id;
	private String bezeichnung;
	private String info;
	private float vk_netto;
	private float vk_brutto;
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
	@Column(name="VK_NETTO")
	public float getVk_netto() {
		return vk_netto;
	}
	public void setVk_netto(float vk_netto) {
		this.vk_netto = vk_netto;
	}
	@Column(name="VK_BRUTTO")
	public float getVk_brutto() {
		return vk_brutto;
	}
	public void setVk_brutto(float d) {
		this.vk_brutto = d;
	}
	@Column(name="IMG_URL", length=1024)
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	@ManyToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name = "grp_id", nullable=false)
	public Artikelgruppe getArtikelgruppe() {
		return artikelgruppe;
	}
	public void setArtikelgruppe(Artikelgruppe artikelgruppe) {
		this.artikelgruppe = artikelgruppe;
	}

}
