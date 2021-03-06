package classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 * 
 * @author andreas monschau
 * 
 * Entity-Class Kunde mapped to Database-Table "KUN"
 *
 */


@NamedNativeQueries({
	@NamedNativeQuery(
			name = "findKundeByEmailAndPassword",
			query = "Select * from Kunde k where k.email = :email and k.password= :password",
			resultClass = Kunde.class
			)
})

@Entity
@Table(name="Kunde")
public class Kunde {
	
	
	private int id;
	private String email;
	private String password;
	private String adresse;
	private String vorname;
	@Id
	@GeneratedValue
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="EMAIL", length=1024)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="PASSWORD", length=1024)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="ADRESSE", length=1024)
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	@Column(name="VORNAME", length=1024)
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
}
