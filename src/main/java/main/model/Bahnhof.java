package main.model;

import javax.persistence.*;

@Entity
public class Bahnhof {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ID;

	private String name;
	
	private int absPreisEntfernung;

	private int absKmEntfernung;

	private int absZeitEntfernung;

	private boolean kopfBahnhof;
	
	public Bahnhof(String name, int absPreisEntfernung, int absKmEntfernung, int absZeitEntfernung, boolean kopfBahnhhof) {
		
		this.name = name;
		this.absPreisEntfernung = absPreisEntfernung;
		this.absKmEntfernung = absKmEntfernung;
		this.absZeitEntfernung = absZeitEntfernung;
		this.kopfBahnhof = kopfBahnhhof;
	}
	public Bahnhof(){

	}

	public String getName(){
		return this.name;
	}
}
