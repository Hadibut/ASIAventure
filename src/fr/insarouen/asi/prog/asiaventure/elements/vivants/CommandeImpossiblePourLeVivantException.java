package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import java.io.Serializable;

public class CommandeImpossiblePourLeVivantException extends VivantException implements Serializable {

	public CommandeImpossiblePourLeVivantException() {
		super();
	}

	public CommandeImpossiblePourLeVivantException(String msg) {
		super(msg);
	}
}