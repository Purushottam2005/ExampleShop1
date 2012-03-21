package controllers;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import generics.GenericDAO;
import generics.KundeDAO;
import classes.Kunde;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controllerinterfaces.ILoginController;
/**
 * 
 * @author andreas monschau
 * 
 * used to controll interaction between login-jsf and kunde-class. Login-Information is checked
 * and instance of current logged-in user is stored into http-session
 *
 */
public class LoginController implements ILoginController {

	private Kunde kunde = new Kunde();

	private GenericDAO genericdao;

	private BeanFactory factory;

	private KundeDAO kundedao;
	

	
	public LoginController(){
		
	}
	
	public void dosomething(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		factory = context;		
		//kunde = (Kunde) context.getBean("kunde");
		kundedao = (KundeDAO) context.getBean("kundedao");
	}
	
	@Override
	public String checkLogin() {
		dosomething();
		System.out.println("checklogin");
		if (kundedao.checkPassword(kunde.getEmail(), kunde.getPassword())){
			kunde = kundedao.getKundeByEmailPasswort(kunde.getEmail(), kunde.getPassword());
			if(kunde.getId()!= 0){
				FacesContext ctx = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
				session.setAttribute("Kunde", kunde);
				return "true";
			}
			return "false";
		}
		return "false";
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public GenericDAO getGenericdao() {
		return genericdao;
	}

	public void setGenericdao(GenericDAO genericdao) {
		this.genericdao = genericdao;
	}

	public KundeDAO getKundedao() {
		return kundedao;
	}

	public void setKundedao(KundeDAO kundedao) {
		this.kundedao = kundedao;
	}





}
