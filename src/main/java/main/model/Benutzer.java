package main.model;

import javax.persistence.*;



@NamedQueries({
		@NamedQuery(
				name = "getAllReservationsForEMail",
				query = "from Reservierung r inner join fetch r.benutzer as b "
						+ "WHERE b.eMail = :eMail"
		)
		/*
		@NamedQuery(
				name = "getAllUsersWithMonthTicket",
				query = "from Benutzer b inner join Ticket t where t.typ='MONATSKARTE'"
		)
		@NamedQuery(
				name = "getAllTicketsWithoutReservation",
				query = "FROM Reservierung r LEFT JOIN FETCH Benutzer b ON b.ID = r.ID "
						+ "RIGHT OUTER JOIN Ticket t ON t.ID = b.id "
						+ "LEFT OUTER JOIN Strecke s ON s.ID = t.id "
						+ "WHERE s.ende = :ende AND s.start = :start HAVING r.ID IS NULL"
		)
		*/
})

@Entity
public class Benutzer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ID;

	private String vorName;

	private String nachName;

	private String eMail;

	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	@OneToOne
	private Ticket tickets;

	@OneToOne
	private Reservierung[] reservierungen;


	public Benutzer(String vname, String nname, String mail){
		this.vorName = vname;
		this.nachName = nname;
		this.eMail = mail;
	}
	public Benutzer(){

	}
	public String getName(){
		return this.vorName+" "+this.nachName;
	}

	public String getUser() {
		if (this.tickets.getClass().equals(main.model.Zeitkarte.class))
			return this.vorName + " " + this.nachName + " (" + this.eMail + ") hat eine "
					+ ((Zeitkarte) this.tickets).getTyp();
		else return this.getName() + " (" + this.eMail + ") hat ein Einzelticket";
	}
}
