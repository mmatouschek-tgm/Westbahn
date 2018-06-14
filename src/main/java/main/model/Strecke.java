package main.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Strecke {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ID;

	@NotNull
	@OneToOne
	private Bahnhof start;

	@OneToOne
	private Bahnhof bahnhof;

	@NotNull
	@OneToOne
	private Bahnhof ende;

	private boolean endeIsStart;

	//@OneToMany(mappedBy = "strecke")
	//private Set<Strecke> test;

	public Strecke() {}
	public Strecke(Bahnhof start, Bahnhof ende) {
		this(start, ende, null);
	}
	public Strecke(Bahnhof start, Bahnhof ende, Bahnhof bahnhof) {
		this.start = start;
		this.ende = ende;
		this.bahnhof = bahnhof;
		//this.endeIsStart = this.ende.getName().equals(this.start.getName());
	}
	public String getEndbahnhof(){return null;}

	public String getStrecke(){
		return start.toString()+" "+ende.toString();
	}
}
