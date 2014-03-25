package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;

public class Clef extends Objet {

	protected Clef(String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {
		
		super(nom, monde);
	}

	public boolean estDeplacable() {

		return true;
	}
}