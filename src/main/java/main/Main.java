package main;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import main.model.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class);

	static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
	static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

	static Configuration configuration;
	static SessionFactory sessionFactoryTest;
	static Session session;
	static Transaction transaction;

	private Main() {
		super();
	}

	public static void main(String[] args) {
		log.setLevel(Level.ALL);
		try {
			log.info("Starting \"Mapping Perstistent Classes and Associations\" (task1)");
			configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			sessionFactoryTest = configuration.buildSessionFactory();
			session = sessionFactoryTest.openSession();
			transaction = session.beginTransaction();

			task01();
			log.info("Starting \"Working with JPA-QL and the Hibernate Criteria API\" (task2)");
			task02a();
			task02b();
			task02c();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fillDB(EntityManager em) throws ParseException {

	}

	public static void task01() throws ParseException, InterruptedException {

		Bahnhof hbf = new Bahnhof("Hauptbahnhof", 50, 50, 50, true);
		Bahnhof nbf = new Bahnhof("Nebenbahnhof", 50, 50, 50, false);
		Bahnhof fbf = new Bahnhof("Floridsdorf Bahnhof", 50, 50, 50, true);

		session.persist(hbf);
		session.persist(nbf);
		session.persist(fbf);

		Strecke hbf_nach_fbf = new Strecke(hbf,fbf);
		Strecke nbf_nach_fbf = new Strecke(nbf,fbf);

		session.persist(hbf_nach_fbf);
		session.persist(nbf_nach_fbf);

		Zug s2 = new Zug(hbf, fbf);
		Zug s40 = new Zug(nbf,fbf);

		session.persist(s2);
		session.persist(s40);

		Zahlung kreditkarte = new Kreditkarte();

		Ticket ticket = new Zeitkarte(ZeitkartenTyp.MONATSKARTE, new Date(), hbf_nach_fbf, kreditkarte);

		session.persist(ticket);

		Benutzer testuser = new Benutzer("Marco", "Matouschek", "mmatouschek@student.tgm.ac.at");

		session.persist(testuser);

		Reservierung res1 = new Reservierung(new Date(), 10, 100, StatusInfo.ONTIME, s2, hbf_nach_fbf, testuser, kreditkarte);
		Reservierung res2 = new Reservierung(new Date(), 100, 150, StatusInfo.ONTIME, s40, nbf_nach_fbf, testuser, kreditkarte);

		session.persist(res1);
		session.persist(res2);

		transaction.commit();
	}
	public static void task02a() throws ParseException {
		Query query = session.getNamedQuery("getAllReservationsForEMail");
		query.setParameter("eMail", "mmatouschek@student.tgm.ac.at");
		List<Reservierung> reservierungen = ((org.hibernate.query.Query) query).list();
		System.out.println(reservierungen.size());
		for (Reservierung r : reservierungen)
			System.out.println(r.showReservierung());

	}

	public static void task02b() throws ParseException {
		/*
		Query query = session.getNamedQuery("getAllUsersWithMonthTicket");
		List<Benutzer> l = ((org.hibernate.query.Query) query).list();
		System.out.println(l.size());
		for (Benutzer str : l) {
			System.out.println(str.getUser());
		}
		*/
	}

	public static void task02c() throws ParseException {

	}
}
