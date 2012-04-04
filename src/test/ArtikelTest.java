package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;


import generics.ArtikelDAO;
import generics.ArtikelgruppenDAO;
import generics.BestellungDAO;
import generics.GenericDAO;
import generics.KundeDAO;

import oracle.net.aso.k;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import classes.Artikel;
import classes.Artikelgruppe;
import classes.Bestellung;
import classes.Kunde;
import classes.Warenkorb;


public class ArtikelTest {
		GenericDAO dao;	
		SessionFactory sessionFactory;
		Session session;
		HibernateTemplate hibernatetemplate;
		
		@Before
		public void setUp() throws Exception {
			dao = new GenericDAO();
			Configuration configuration = new Configuration();
			configuration.setProperty(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
			configuration.setProperty(Environment.URL, "jdbc:oracle:thin:@localhost:1521");
			configuration.setProperty(Environment.USER, "system");
			configuration.setProperty(Environment.PASS, "system");
			configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.OracleDialect");
			configuration.setProperty(Environment.HBM2DDL_AUTO, "create");
			configuration.setProperty(Environment.SHOW_SQL, "true");
			configuration.addAnnotatedClass(Artikel.class);
			configuration.addAnnotatedClass(Artikelgruppe.class);
			configuration.addAnnotatedClass(Kunde.class);
			this.sessionFactory = configuration.buildSessionFactory();
			hibernatetemplate = new HibernateTemplate(this.sessionFactory);
			dao.setHibernateTemplate(hibernatetemplate, sessionFactory);
			this.session = SessionFactoryUtils.getSession(sessionFactory, true);
			TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));	
		}
		
		@Test
		public void test() {
			//Artikelgruppen erzeugen
			ArtikelgruppenDAO agdao = new ArtikelgruppenDAO();
			agdao.setHibernateTemplate(hibernatetemplate, sessionFactory);
			Artikelgruppe ag = new Artikelgruppe();
			ag.setBezeichnung("Spielwaren");
			ag.setInfo("Spielwaren - Alles was Kindern spass macht");
			Artikelgruppe ag2 = new Artikelgruppe();
			ag2.setBezeichnung("DVD & Blu-Ray");
			ag2.setInfo("DVD- und Blu-Ray-Filme im Sonderangebot");
			agdao.save(ag2);
			Artikelgruppe ag3 = new Artikelgruppe();
			ag3.setBezeichnung("Spiele");
			ag3.setInfo("Spiele für PC und Konsole");
			agdao.save(ag3);
			//Artikel erzeugen
			ArtikelDAO adao = new ArtikelDAO();
			adao.setHibernateTemplate(hibernatetemplate, sessionFactory);
			Artikel artikel = new Artikel();
			artikel.setBezeichnung("Transformers Dark of the Moon Deluxe Optimus Prime");
			artikel.setInfo("Größe je Figur: ca. 17 cm Material: Kunststoff Gruppierung: Autobot ACHTUNG! Nicht geeignet für Kinder unter 36 Monaten. Enthält verschluckbare Kleinteile. Erstickungsgefahr!");
			artikel.setVk_brutto(20);
			artikel.setImg_url("/optimus_prime.jpg");
			artikel.setArtikelgruppe(ag);
			adao.save(artikel);
			Artikel artikel2 = new Artikel();
			artikel2.setBezeichnung("Risiko");
			artikel2.setInfo("Kaum ein Brettspiel hat eine so traditionsreiche und bewegte Geschichte wie Risiko. Mitte der 50er Jahre von Albert Lamorisse erfunden, fand sich der unangefochtene Klassiker unter den Strategiespielen.");
			artikel2.setVk_brutto(35);
			artikel2.setImg_url("/risiko.jpg");
			artikel2.setArtikelgruppe(ag);
			adao.save(artikel2);
			Artikel artikel3 = new Artikel();
			artikel3.setBezeichnung("Barbie, Puppe Blair aus die Prinzessinnen-Akademie");
			artikel3.setInfo("Passend zum Barbie Film 'Prinzessinnen Akademie', erscheint Barbie als Blair in einem wahrhaft magischen Kleid, das sich in die drei wunderschönen Outfits des Films verwandeln lässt.");
			artikel3.setVk_brutto(18);
			artikel3.setImg_url("/barbie.jpg");
			artikel3.setArtikelgruppe(ag);
			adao.save(artikel3);
			Artikel artikel4 = new Artikel();
			artikel4.setBezeichnung("Avatar (Extended Collector's Edition) [Blu-ray]");
			artikel4.setInfo("Nach einer schweren Verwundung ist Marine Jake Sully (Sam Worthington, Terminator - Die Erlösung) von der Hüfte abwärts gelähmt. Ihm wird das Angebot unterbreitet, für ein Projekt zum fernen Pandora zu reisen. ");
			artikel4.setVk_brutto(15);
			artikel4.setImg_url("/avatar.jpg");
			artikel4.setArtikelgruppe(ag2);
			adao.save(artikel4);
			Artikel artikel5 = new Artikel();
			artikel5.setBezeichnung("Robocop - Die Serie (limitierte Steelbox!) [6 DVDs]");
			artikel5.setInfo("Kein Kinofilm, sondern ein Zusammenschnitt mehrerer Episoden der US-TV-Serie. Trotzdem können Darsteller, Effekte und Paul Lynchs ('Blind Side') Inszenierung vollkommen überzeugen. ");
			artikel5.setVk_brutto(8);
			artikel5.setImg_url("/robocop.jpg");
			artikel5.setArtikelgruppe(ag2);
			adao.save(artikel5);
			Artikel artikel6 = new Artikel();
			artikel6.setBezeichnung("StarCraft II: Wings of Liberty");
			artikel6.setInfo("StarCraft II führt die epische Saga um die Protoss, Terraner und Zerg fort. Diese drei vollkommen verschiedenen und mächtigen Völker werden in dem fulminanten Echtzeitstrategienachfolger des legendären Originals StarCraft erneut aufeinander treffen. ");
			artikel6.setVk_brutto(32);
			artikel6.setImg_url("/starcraft.jpg");
			artikel6.setArtikelgruppe(ag3);
			adao.save(artikel6);
			Artikel artikel7 = new Artikel();
			artikel7.setBezeichnung("Quake - Mission Disk 1+2");
			artikel7.setInfo("beinhaltet beide Mission Packs von Quake 1. ");
			artikel7.setVk_brutto(5);
			artikel7.setImg_url("/quake.jpg");
			artikel7.setArtikelgruppe(ag3);
			adao.save(artikel7);
			
			ArrayList<Artikel>arts = (ArrayList<Artikel>) dao.findAll(Artikel.class);
			assertTrue(arts.size() > 0);
			KundeDAO kdao = new KundeDAO();
			kdao.setHibernateTemplate(hibernatetemplate, sessionFactory);
			Kunde k = new Kunde();
			k.setEmail("andreas@diemonschaus.de");
			k.setPassword("test");
			k.setAdresse("Professor-Neu-Alle 3 53225 Bonn");
			k.setVorname("Andreas");
			kdao.save(k);
			Warenkorb wk = new Warenkorb();
			wk.setKunde(k);
			wk.getArtikel().add(artikel);
			wk.getArtikel().add(artikel2);
			assertTrue(wk.getArtikel().size()==2);
			//Artikel nach Artikelgruppe
			ArrayList<Artikel>artikelnachgruppe = (ArrayList<Artikel>)adao.getByGrpId(ag.getId());
			assertTrue(artikelnachgruppe.size()>0);
		}
		@Test
		public void TestBorderCases(){
			ArtikelDAO artdao = new ArtikelDAO();
			artdao.setHibernateTemplate(hibernatetemplate, sessionFactory);
			//null cases
			artdao.save(null);
			KundeDAO kdao = new KundeDAO();
			kdao.setHibernateTemplate(hibernatetemplate, sessionFactory);
			assertFalse(kdao.checkPassword(null, null));
			//empty input
			Kunde k = new Kunde();
			k.setAdresse("");
			k.setEmail("");
			k.setPassword("");
			k.setVorname("");
			kdao.save(k);
			//bad input
			Kunde k2 = new Kunde();
			k2.setAdresse("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
			k2.setEmail("none@none.de");
			k2.setPassword("test");
			k2.setVorname("tester");
			kdao.save(k2);
		}


}
