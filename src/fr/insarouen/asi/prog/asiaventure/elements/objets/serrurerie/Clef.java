package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;

public class Clef extends Objet {

	/**
	 * Crée une clef
	 * @param String nom
	 * @param Monde monde
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une clé du même nom est déjà présente dans ce monde
	 */
	protected Clef(String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {
		
		super(nom, monde);
	}

	/**
	 * Une clef est déplacable
	 * @return Boolean
	 */
	public boolean estDeplacable() {

		return true;
	}
}