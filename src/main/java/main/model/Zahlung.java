package main.model;

import javax.persistence.Embeddable;

@Embeddable
public interface Zahlung {

	public void zahlungDurchfuehren();

}
