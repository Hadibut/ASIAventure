package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.*;
import java.util.*;

public class Porte extends ElementStructurel implements Activable {

 	private Piece pieceA;
	private Piece pieceB;
	private Serrure serrure;
	private Etat etat;

	/**
	 * Crée une nouvelle porte
	 * @param String nom
	 * @param Monde monde
	 * @param Piece pieceA
	 * @param Piece pieceB
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une porte du même nom est déjà présente dans ce monde
	 */
	public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
		this.pieceA = pieceA;
		this.pieceB = pieceB;
		this.etat = Etat.FERME;
		this.pieceA.addPorte(this);
		this.pieceB.addPorte(this);
	}

	/**
	 * Crée une nouvelle porte
	 * @param String nom
	 * @param Monde monde
	 * @param Serrure serrure
	 * @param Piece pieceA
	 * @param Piece pieceB
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une porte du même nom est déjà présente dans ce monde
	 */
	public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
		this.pieceA = pieceA;
		this.pieceB = pieceB;
		this.serrure = serrure;

		if (serrure.getEtat() == Etat.DEVERROUILLE)
			this.etat = Etat.FERME;
		else
			this.etat = serrure.getEtat();
	}

	/**
	 * Est-ce que la porte est activable avec cet objet ?
	 * @param Objet objet
	 * @return boolean true si la porte est activable
	 */
	public boolean activableAvec(Objet objet) {

		return (objet instanceof PiedDeBiche || objet instanceof Clef);
	}
 
 	/**
 	 * Active la porte sans objet
 	 * @throws ActivationImpossibleException si la porte ne peut pas être activer
 	 */
	public void activer()
	throws ActivationImpossibleException {
		
		if (this.getEtat() == Etat.VERROUILLE)
			throw new ActivationImpossibleException("La porte " + this.getNom() + " est verrouillée et ne peux pas être ouverte sans objet.");

		if (this.getEtat() != Etat.CASSE) {
			
			if (this.getEtat() == Etat.FERME)
				this.etat = Etat.OUVERT;
			else
				this.etat = Etat.FERME;
		}
	}
 
 	/**
 	 * Activer la porte avec un objet
 	 * @param Objet objet
 	 * @throws ActivationImpossibleException si la porte n'est pas activable avec cet objet
 	 */
	public void activerAvec(Objet objet)
	throws ActivationImpossibleException {

		if (!this.activableAvec(objet))
			throw new ActivationImpossibleException("L'objet " + objet.getNom() + " ne permet pas d'ouvrir la porte " + this.getNom() + ".");

		if (this.getEtat() != Etat.CASSE) {

			if (objet instanceof PiedDeBiche)
				this.etat = Etat.CASSE;
			
			if (this.getSerrure() == null) {

				this.activer();
			}
			else {

				this.getSerrure().activerAvec(objet);
				
				if (this.getSerrure().getEtat() == Etat.VERROUILLE)
					this.etat = Etat.VERROUILLE;
				
				if (this.getSerrure().getEtat() == Etat.DEVERROUILLE)
					this.etat = Etat.OUVERT;
			}
		}
	}

	/**
	 * Retourne l'état de la porte
	 * @return Etat : OUVERT, FERME, VEROUILLE ou CASSE
	 */
	public Etat getEtat() {

		return this.etat;
	}

	/**
	 * Retourne la serrure de la porte
	 * @return Serrure serrure
	 */
	public Serrure getSerrure() {

		return this.serrure;
	}

	/**
	 * Retourne la pièce de l'autre côté de la porte
	 * @param Piece pièce où l'on est
	 * @return Piece pièce de l'autre côté de la porte
	 */
	public Piece getPieceAutreCote(Piece piece) {

		if (piece.equals(this.pieceA))
			return this.pieceB;

		if (piece.equals(this.pieceB))
			return this.pieceA;

		return null;
	}

	/**
	 * Décris la porte
	 * @return String 
	 */
	public String toString() {

		StringBuilder chaine = new StringBuilder();
		chaine.append("\nPorte appelée : \"");
		chaine.append(this.getNom());
		chaine.append("entre ");
		chaine.append(pieceA.getNom());
		chaine.append(" et ");
		chaine.append(pieceB.getNom());
		chaine.append(".");

		return chaine.toString();
	}
}



