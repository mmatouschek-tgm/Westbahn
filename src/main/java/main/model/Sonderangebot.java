package main.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sonderangebot {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ID;

	private int kontingent = 999;

	private Date startZeit;

	private int dauer = 12;

	private float preisNachlass = 0.5f;

	@OneToOne
	private Ticket tickets;

}
