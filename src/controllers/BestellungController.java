package controllers;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import generics.WarenkorbDAO;
import classes.Bestellung;
import classes.Kunde;
import classes.Warenkorb;
import controllerinterfaces.IWarenkorbController;
/**
 * 
 * @author andreas monschau
 * 
 * controller for subpage order.jsp
 *
 */
public class BestellungController implements IWarenkorbController {
		private Warenkorb warenkorb;
		private WarenkorbDAO wkdao;
		private BeanFactory factory;	
		private Kunde kunde;
		private Bestellung bestellung;
	
		public BestellungController(){
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			factory = context;			
			warenkorb = (Warenkorb)context.getBean("warenkorb");
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
			warenkorb = (Warenkorb)session.getAttribute("warenkorb");
			bestellung =(Bestellung)context.getBean("bestellung");
			bestellung = (Bestellung)session.getAttribute("bestellung");
			System.out.println(bestellung.getBestelldatum());
		}

		public Warenkorb getWarenkorb() {
			return warenkorb;
		}

		public void setWarenkorb(Warenkorb warenkorb) {
			this.warenkorb = warenkorb;
		}

		public Kunde getKunde() {
			return kunde;
		}

		public void setKunde(Kunde kunde) {
			this.kunde = kunde;
		}

		public Bestellung getBestellung() {
			return bestellung;
		}

		public void setBestellung(Bestellung bestellung) {
			this.bestellung = bestellung;
		}
		/**
		 * lets us return to the main.jsp
		 */
		public void toshop(){
			FacesContext ctx = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
			session.setAttribute("kunde", kunde);
			bestellung = null;
			session.setAttribute("bestellung", bestellung);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/ExampleShop1/faces/main.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}
