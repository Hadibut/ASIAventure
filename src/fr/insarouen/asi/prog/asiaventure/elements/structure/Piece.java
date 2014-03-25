package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import java.util.*;

public class Piece extends ElementStructurel {

	
	private HashMap<String,Porte> portes;
	private HashMap<String,Vivant> vivants;
	private HashMap<String,Objet> objets;
	/**
	 * Crée une nouvelle Pièce
	 * @param String nom
	 * @param Monde monde
	 * @return Piece
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une entité avec le même nom est déjà présente
	 */
	public Piece(String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
		this.objets = new HashMap<String,Objet>();
		this.vivants = new HashMap<String,Vivant>();
		this.portes = new HashMap<String,Porte>();
	}

	/**
	 * Dépose un objet dans la pièce
	 * @param Objet objet
	 */
	public void deposer(Objet objet) {

		this.objets.put(objet.getNom(), objet);
	}

	/**
	 * Retire un objet de la pièce
	 * @param Objet objet
	 * @return Objet l'objet retiré
	 * @throws ObjetAbsentDeLaPieceException        si l'objet n'est pas présent
	 * @throws ObjetNonDeplacableDeLaPieceException si l'objet n'est pas déplacable
	 */
	public Objet retirer(Objet objet)
	throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		if (!objet.estDeplacable()) 
			throw new ObjetNonDeplacableDeLaPieceException("L'objet " + objet.getNom() + " n'est pas déplacable.");

		Objet retour = this.objets.remove(objet.getNom());

		if (retour == null)
			throw new ObjetAbsentDeLaPieceException("L'objet " + objet.getNom() + " n'est pas présent dans la pièce.");

		return retour;
	}

	/**
	 * Retire un objet de la pièce
	 * @param String nomObj le nom de l'objet à retirer
	 * @return Objet l'objet retiré
	 * @throws ObjetAbsentDeLaPieceException        si l'objet n'est pas présent
	 * @throws ObjetNonDeplacableDeLaPieceException si l'objet n'est pas déplacable
	 */
	public Objet retirer(String nomObj)
	throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		Objet retour = this.objets.get(nomObj);

		if (retour == null)
			throw new ObjetAbsentDeLaPieceException("L'objet " + nomObj + " n'est pas présent dans la pièce.");

		if (!retour.estDeplacable())
			throw new ObjetNonDeplacableDeLaPieceException("L'objet " + nomObj + " n'est pas déplacable.");

		return this.objets.remove(nomObj);
	}

	/**
	 * Est-ce que la pièce contient l'objet
	 * @param Objet objet
	 * @return Booleen 
	 */
	public Boolean contientObjet(Objet objet) {

		return this.objets.containsKey(objet.getNom());
	}

	/**
	 * Est-ce que la pièce contient l'objet
	 * @param String nomObj le nom de l'objet dont on test la présence
	 * @return Booleen 
	 */
	public Boolean contientObjet(String nomObj) {

		return this.objets.containsKey(nomObj);
	}

	public void addPorte(Porte porte) {

		this.portes.put(porte.getNom(), porte);
	}

	public Boolean aLaPorte(Porte porte) {

		return this.portes.containsKey(porte.getNom());
	}

	public Boolean aLaPorte(String nomPorte) {

		return this.portes.containsKey(nomPorte);
	}

	public Porte getPorte(String nomPorte) {

		return this.portes.get(nomPorte);
	}

	public HashMap<String,Porte> getPortes() {

		return this.portes;
	}

	public HashMap<String,Objet> getObjets() {

		return this.objets;
	}

	/**
	 * Ajouter un Vivant dans la pièce
	 * @param Vivant vivant à ajouter
	 */
	public void entrer(Vivant vivant) {
		
		this.vivants.put(vivant.getNom(), vivant);

	}  

	/**
	 * Supprimer un vivant de la pièce
	 * @param Vivant vivant
	 * @return Vivant le vivant retiré de la pièce
	 * @throws VivantAbsentDeLaPieceException si le vivant est absent de la pièce
	 */
	public Vivant sortir(Vivant vivant)
	throws VivantAbsentDeLaPieceException {


		Vivant retour = this.vivants.remove(vivant.getNom());

		if (retour == null)
			throw new VivantAbsentDeLaPieceException("Le vivant " + vivant.getNom() + " n'est pas présent dans la pièce.");

		return retour;
	}

	/**
	 * Supprimer un vivant de la pièce
	 * @param String nomVivant nom du vivant à retirer de la pièce
	 * @return Vivant le vivant retiré de la pièce
	 * @throws VivantAbsentDeLaPieceException si le vivant est absent de la pièce
	 */	
	public Vivant sortir(String nomVivant)
	throws VivantAbsentDeLaPieceException {

		Vivant retour = this.vivants.remove(nomVivant);

		if (retour == null)
			throw new VivantAbsentDeLaPieceException("Le vivant " + nomVivant + " n'est pas présent dans la pièce.");

		return retour;
	}

	/**
	 * Est-ce que le vivant est présent ?
	 * @param Vivant vivant
	 * @return Booleen
	 */
	public Boolean contientVivant(Vivant vivant) {

		return this.vivants.containsKey(vivant.getNom());
	}

	/**
	 * Est-ce que le vivant est présent ?
	 * @param String nomVivant nom du vivant dont on test la présence
	 * @return Booleen
	 */
	public Boolean contientVivant(String nomVivant) {
	
		return this.vivants.containsKey(nomVivant);
	}

	/**
	 * Affiche la pièce
	 * @return String
	 */
	public String toString() {

		StringBuilder chaine = new StringBuilder();
		chaine.append("\nObjet Pièce appelé : \"");
		chaine.append(this.getNom());
		chaine.append("\" : \n");

		if (this.objets.size() == 0) {
			
			chaine.append(" * Aucun objet");
		}
		else {

			chaine.append(" * Objets :");

			for (String nomObjet : this.objets.keySet()) {
				
				chaine.append("\n   * ");
				chaine.append(nomObjet);
				chaine.append(" ");
			}
		}

		if (this.vivants.size() == 0) {
			
			chaine.append("\n * Aucun vivant");
		}
		else {

			chaine.append("\n * Vivants :");

			for (String nomVivant : this.vivants.keySet()) {
				
				chaine.append("\n   * ");
				chaine.append(nomVivant);
				chaine.append(" ");
			}
		}

		return chaine.toString();
	}
}

