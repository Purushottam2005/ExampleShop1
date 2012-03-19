package Controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	@Autowired
	private Kunde kunde;
	@Autowired
	private KundeDAO kundedao;
	@SuppressWarnings("unused")
	private BeanFactory factory;
	
	public LoginController(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		factory = context;		
		kunde = (Kunde) context.getBean("kunde");
		kundedao = (KundeDAO) context.getBean("kundedao");		
	}
	
	@Override
	public boolean checkLogin() {		
		if (kundedao.checkPassword(kunde.getEmail(), kunde.getPassword())){
			kunde = kundedao.getKundeByEmailPasswort(kunde.getEmail(), kunde.getPassword());
			if(kunde!=null){
				FacesContext ctx = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
				session.setAttribute("Kunde", kunde);
			}
		}
		return kunde!=null;
	}

}
