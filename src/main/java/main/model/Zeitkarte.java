package main.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Zeitkarte extends Ticket {

	private Date gueltigAb;

	private ZeitkartenTyp typ;

	public Zeitkarte(ZeitkartenTyp typ, Date gueltigAb, Strecke s, Zahlung z){
		super(s,z);
		this.typ = typ;
		this.gueltigAb = gueltigAb;
	}
	public Zeitkarte(){

	}
	public ZeitkartenTyp getTyp() {
		return typ;
	}
}
