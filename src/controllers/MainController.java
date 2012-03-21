package controllers;

import generics.ArtikelDAO;
import generics.ArtikelgruppenDAO;
import generics.KundeDAO;
import generics.WarenkorbDAO;

import java.util.Collections;
import java.util.List;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controllerinterfaces.IMainController;
import classes.*;
/**
 * 
 * @author andreas monschau
 * 
 * controlls articleliste, adding to chart and selecting artikelgroups
 *
 */
public class MainController implements IMainController {
	private List<Artikel> artikelliste = null;
	private List<Artikelgruppe> artikelgruppenliste = null;
	private BeanFactory factory;
	private double warenkorbgesamt = 0;
	
	//JavaServerFaces related variable
	private UIForm tableForm;
	
	@Autowired
	private Artikel artikel;
	@Autowired
	private Artikelgruppe artikelgruppe;
	@Autowired
	private ArtikelgruppenDAO artgrpdao;
	@Autowired
	private ArtikelDAO artdao;
	@Autowired
	private Artikelgruppe dummy;
	@Autowired
	private Kunde kunde;
	@Autowired
	private Warenkorb warenkorb;
	@Autowired
	private WarenkorbDAO warenkorbdao;
	
	

	
	public MainController() {
		super();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		factory = context;
		artgrpdao = (ArtikelgruppenDAO) context.getBean("artikelgruppendao");
		artikelgruppenliste = artgrpdao.getAlleArtGrp();
		//Properties are set via setter-injection
		dummy = (Artikelgruppe)context.getBean("artikelgruppedummy");
		artikelgruppenliste.add(dummy);
		artdao = (ArtikelDAO)context.getBean("artikeldao");
		artikelliste = artdao.getAll();
		Collections.sort(artikelliste);
		artikelgruppe =(Artikelgruppe)context.getBean("artikelgruppe");
		kunde = (Kunde)context.getBean("kunde");
		//get Kunde from session
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		kunde = (Kunde)session.getAttribute("Kunde");
		//todo: if kunde.id == 0 then redirect to errorpage
		//...
		warenkorb = (Warenkorb)context.getBean("warenkorb");
		warenkorbdao = (WarenkorbDAO)context.getBean("warenkorbdao");
		if((Warenkorb)session.getAttribute("warenkorb")==null){
			session.setAttribute("warenkorb", warenkorb);	
			System.out.println("nicht drin");
		}else{
			warenkorb = (Warenkorb)session.getAttribute("warenkorb");
			System.out.println("drin");
		}
		
		artikel = (Artikel)context.getBean("artikel");
	}

	

	
	public List<Artikel> getArtikelliste() {
		return artikelliste;
	}

	public void setArtikelliste(List<Artikel> artikelliste) {
		this.artikelliste = artikelliste;
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public List<Artikelgruppe> getArtikelgruppenliste() {
		return artikelgruppenliste;
	}

	public void setArtikelgruppenliste(List<Artikelgruppe> artikelgruppenliste) {
		this.artikelgruppenliste = artikelgruppenliste;
	}

	public Artikelgruppe getArtikelgruppe() {
		return artikelgruppe;
	}

	public void setArtikelgruppe(Artikelgruppe artikelgruppe) {
		this.artikelgruppe = artikelgruppe;
	}

	public ArtikelgruppenDAO getArtgrpdao() {
		return artgrpdao;
	}

	public void setArtgrpdao(ArtikelgruppenDAO artgrpdao) {
		this.artgrpdao = artgrpdao;
	}
	
	public void filter(){
		artikelliste = artdao.getByGrpId(artikelgruppe.getId());
	}
	
	public void kaufen(){
		System.out.println(artikel.getBezeichnung());
		warenkorb.getArtikel().add(artikel);
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.setAttribute("warenkorb", warenkorb);
	}
	
	public void emptywk(){
		warenkorb = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.setAttribute("warenkorb", warenkorb);
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public UIForm getTableForm() {
		return tableForm;
	}

	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}

	public Warenkorb getWarenkorb() {
		return warenkorb;
	}

	public void setWarenkorb(Warenkorb warenkorb) {
		this.warenkorb = warenkorb;
	}
	
	public double getwarenkorbgesamt(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		if((Warenkorb)session.getAttribute("warenkorb")!=null){
			return warenkorbdao.calculateWK((Warenkorb)session.getAttribute("warenkorb"));
		}else{
			return 0.0;
		}
	}
	
}
