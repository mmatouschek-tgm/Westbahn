package main.model;

import javax.persistence.*;

@Entity
public class Einzelticket extends Ticket {

	@Enumerated
	private TicketOption ticketOption;

}
