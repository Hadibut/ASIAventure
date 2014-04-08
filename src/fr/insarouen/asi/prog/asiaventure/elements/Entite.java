package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.*;
import java.io.Serializable;

public abstract class Entite implements Serializable {

	public final static int MAXIMUMNOMALEATOIRE = 42;
	private Monde monde;
	private String nom;
	
	/**
	 * Crée une nouvelle Entite
	 * @param String nom
	 * @param Monde monde
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une entite du même nom est déjà présente dans ce monde
	 */
	public Entite(String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		this.nom = nom;
		this.monde = monde;
		this.monde.ajouter(this);
	}
	
	/**
	 * Donne le nom de l'entité
	 * @return String nom 
	 */
	public String getNom() {

		return this.nom;
	}

	/**
	 * Donne le monde où se trouve le vivant
	 * @return Monde monde
	 */
	protected Monde getMonde() {

		return this.monde;
	}

	/**
	 * Affiche l'entité
	 * @return String 
	 */
	public String toString() {

		return this.getNom();
	}

	/**
	 * Verifie l'egalité de deux entités
	 * @param Object object
	 * @return boolean 
	 */
	public boolean equals(Object object) {

		if (!(object instanceof Entite))
			return false;

		Entite entite = (Entite) object;
		return (
			this.getNom().equals(entite.getNom()) && 
			this.getMonde().equals(entite.getMonde())
			);
	}
}
