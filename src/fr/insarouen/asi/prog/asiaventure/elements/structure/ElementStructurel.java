package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;

public abstract class ElementStructurel extends Entite {
	/**
	 * Crée une nouvelle Entite
	 * @param String nom
	 * @param Monde monde
	 * @return ElementStructurel créé
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si un ElementStructurel du même nom est déjà présent dans ce monde
	 */
	public ElementStructurel(String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
	}
}
