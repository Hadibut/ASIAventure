package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;

public abstract class Objet extends Entite {

	/**
	 * Constructeur de Objet
	 * @param String nom de Objet
	 * @param Monde monde 
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une entité du même nom est déjà présente dans ce monde
	 */
	public Objet (String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
	}
	
	/**
	 * Dire si un objet est deplacable
	 * @return Boolean, par defaut false
	 */
	public boolean estDeplacable() {

		return false;
	}
}
