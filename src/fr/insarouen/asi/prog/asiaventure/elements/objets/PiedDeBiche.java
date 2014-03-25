package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

public class PiedDeBiche extends Objet {
	/**
	 * Crée un pied de biche
	 * @param String nom
	 * @param Monde monde
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si un pied de biche du même nom est déjà présente dans ce monde
	 */
	public PiedDeBiche(String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
	}
/**
	 * Un pied de biche  est deplacable
	 * @return Boolean par deffaut true
	 */
	public boolean estDeplacable() {

		return true;
	}
}
