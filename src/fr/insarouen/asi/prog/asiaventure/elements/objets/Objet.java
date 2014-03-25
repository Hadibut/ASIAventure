package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;

public abstract class Objet extends Entite {

	/**
	 * Constructeur de Objet
	 * @param String nom de Objet
	 * @param Monde monde 
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException Si le monde possede deja un objet du même nom
	 */
	public Objet (String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
	}
	/**
	 * Dire si un objet est deplacable
	 * @return Boolean par deffaut false
	 */
	public boolean estDeplacable() {

		return false;
	}
}
