package main.model;

import javax.persistence.*;

@Entity
public abstract class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long ID;

	@OneToOne
	protected Strecke strecke;

	protected Zahlung zahlung;

	private TicketOption typ;

	public Ticket(Strecke s, Zahlung z){
		this.strecke = s;
		this.zahlung = z;
	}

	public Ticket(){

	}
}
