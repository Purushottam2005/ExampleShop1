package test;

import static org.junit.Assert.*;

import java.util.ArrayList;


import generics.ArtikelDAO;
import generics.ArtikelgruppenDAO;
import generics.GenericDAO;
import generics.KundeDAO;

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
			dao.setHibernateTemplate(hibernatetemplate);
			this.session = SessionFactoryUtils.getSession(sessionFactory, true);
			TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));	
		}
		
		@Test
		public void test() {
			ArtikelgruppenDAO agdao = new ArtikelgruppenDAO();
			agdao.setHibernateTemplate(hibernatetemplate);
			Artikelgruppe ag = new Artikelgruppe();
			ag.setBezeichnung("Artikelgruppe 1");
			ag.setInfo("Informationen zu Artikelgruppe 1");
			agdao.save(ag);
			ArtikelDAO adao = new ArtikelDAO();
			adao.setHibernateTemplate(hibernatetemplate);
			Artikel artikel = new Artikel();
			artikel.setBezeichnung("Testartikel");
			artikel.setInfo("TestartikelInfo");
			artikel.setVk_brutto(19.00);
			artikel.setArtikelgruppe(ag);
			adao.save(artikel);
			ArrayList<Artikel>arts = (ArrayList<Artikel>) dao.findAll(Artikel.class);
			assertTrue(arts.size() > 0);
			Artikel artikel2 = new Artikel();
			artikel2.setBezeichnung("Testartikel2");
			artikel2.setInfo("TestartikelInfo2");
			artikel2.setVk_brutto(19.00);
			artikel2.setArtikelgruppe(ag);
			adao.save(artikel2);
			KundeDAO kdao = new KundeDAO();
			kdao.setHibernateTemplate(hibernatetemplate);
			Kunde k = new Kunde();
			k.setEmail("none@none.de");
			k.setPassword("test");
			k.setAdresse("Meine Strasse 3 53225 Meine Stadt)");
			kdao.save(k);
			Warenkorb wk = new Warenkorb();
			wk.setKunde(k);
			wk.getArtikel().add(artikel);
			wk.getArtikel().add(artikel2);
			assertTrue(wk.getArtikel().size()==2);
			
		}


}
