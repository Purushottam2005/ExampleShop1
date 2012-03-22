package controllers;

import generics.ArtikelDAO;
import generics.ArtikelgruppenDAO;

import generics.WarenkorbDAO;


import java.io.IOException;
import java.util.Collections;
import java.util.List;



import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.BeanFactory;
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
	
	
	

	private Artikel artikel;

	private Artikelgruppe artikelgruppe;

	private ArtikelgruppenDAO artgrpdao;

	private ArtikelDAO artdao;

	private Artikelgruppe dummy;

	private Kunde kunde;

	private Warenkorb warenkorb;

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
		//Collections.sort(artikelliste);
		artikelgruppe =(Artikelgruppe)context.getBean("artikelgruppe");
		kunde = (Kunde)context.getBean("kunde");
		//get Kunde from session
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		kunde = (Kunde)session.getAttribute("kunde");
		if(kunde==null){

			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/ExampleShop1/faces/loginerror.jsp");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		warenkorb = (Warenkorb)context.getBean("warenkorb");
		warenkorbdao = (WarenkorbDAO)context.getBean("warenkorbdao");
		if((Warenkorb)session.getAttribute("warenkorb")==null){
			session.setAttribute("warenkorb", warenkorb);
		}else{
			warenkorb = (Warenkorb)session.getAttribute("warenkorb");
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
		warenkorb.getArtikel().add(artikel);
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		warenkorb.setKunde(kunde);
		session.setAttribute("warenkorb", warenkorb);

	}
	
	public void emptywk(){
		warenkorb = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.setAttribute("warenkorb", warenkorb);
	}
	
	public void logout(){
		kunde = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.setAttribute("kunde", kunde);	
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/ExampleShop1/faces/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
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
	
	public void buy(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		warenkorb = (Warenkorb)session.getAttribute("warenkorb");
		kunde = (Kunde)session.getAttribute("kunde");
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		factory = context;
		Bestellung b = (Bestellung)context.getBean("bestellung");
		b = warenkorbdao.doBestellung(warenkorb);
		warenkorb = null;
		session.setAttribute("warenkorb", warenkorb);
		session.setAttribute("bestellung", b);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/ExampleShop1/faces/order.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	

	
}
