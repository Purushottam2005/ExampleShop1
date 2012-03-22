package generics;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import classes.Artikel;
import classes.Bestellung;
import classes.Kunde;
import classes.Warenkorb;
import interfaces.IWarenkorbDAO;

/**
 * 
 * @author andreas monschau
 * 
 * "DAO" for Warenkorb which handles some operations on wk-objects
 *
 */
public class WarenkorbDAO extends GenericDAO implements IWarenkorbDAO {

	@Override
	public Bestellung doBestellung(Warenkorb wk) {
		System.out.println("doBestellung");
		Bestellung bestellung;
		BeanFactory factory;
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		factory = context;
		bestellung = (Bestellung)context.getBean("bestellung");
		bestellung.setAdresse(wk.getKunde().getVorname() + " " + wk.getKunde().getAdresse());
		bestellung.setArtikel(wk.getArtikel());
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.S" );
		bestellung.setBestelldatum(df.format( dt ));
		bestellung.setKunde(wk.getKunde());
		bestellung.setGesamtpreis(calculateWK(wk));
		BestellungDAO bdao;
		bdao = (BestellungDAO)context.getBean("bestellungdao");
		bdao.createBestellung(bestellung);
		return bestellung;
	}

	@Override
	public double calculateWK(Warenkorb wk) {
		double result = 0;
		for(Artikel art:wk.getArtikel()){
			result+=art.getVk_brutto();
		}
		
		return result;
	}
	


}
