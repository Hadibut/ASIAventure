package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import java.util.*;

public class Monde {
	private String nomMonde;
	private HashMap<String,Entite> entites;

	/**
	 * Crée un monde à partir d'un nom
	 * @param String nomMonde
	 * @return Monde
	 */
	public Monde(String nomMonde) {

		this.nomMonde = nomMonde;
		this.entites = new HashMap<String,Entite>();
	}
	
	/**
	 * Ajoute une entité de le monde
	 * @param Entite entite
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si l'entité est déjà présente
	 */
	public void ajouter(Entite entite) 
		throws  NomDEntiteDejaUtiliseDansLeMondeException {

		if (this.entites.put(entite.getNom(), entite) != null)
			throw new NomDEntiteDejaUtiliseDansLeMondeException("L'entité " + entite.getNom() + " existe déjà.");
	}

	/**
	 * Récupère une entité d'un monde par son nom
	 * @param  String nomEntite
	 * @return Entite
	 */
	public Entite getEntite(String nomEntite) {

		return this.entites.get(nomEntite);
	}

	/**
	 * Affiche le monde
	 * @return String
	 */
	public String toString() {

		StringBuilder chaine = new StringBuilder();
		chaine.append("\nObjet Monde appelé \"");
		chaine.append(this.nomMonde);

		if (this.entites.size() == 0) {
			
			chaine.append("\" vide.");
		}
		else {
			
			chaine.append("\" contenant : ");

			for (String nomEntite : this.entites.keySet()) {
				
				chaine.append("\n * ");
				chaine.append(nomEntite);
				chaine.append(" ");
			}
		}

		return chaine.toString();
	}
}