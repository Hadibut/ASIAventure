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

	public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
		this.pieceA = pieceA;
		this.pieceB = pieceB;
		this.etat = Etat.FERME;
		this.pieceA.addPorte(this);
		this.pieceB.addPorte(this);
	}

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

	public boolean activableAvec(Objet obj) {

		return (obj instanceof PiedDeBiche || obj instanceof Clef);
	}
 
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
 
	public void activerAvec(Objet obj)
	throws ActivationImpossibleException {

		if (!this.activableAvec(obj))
			throw new ActivationImpossibleException("L'objet " + obj.getNom() + " ne permet pas d'ouvrir la porte " + this.getNom() + ".");

		if (this.getEtat() != Etat.CASSE) {

			if (obj instanceof PiedDeBiche)
				this.etat = Etat.CASSE;
			
			if (this.getSerrure() == null) {

				this.activer();
			}
			else {

				this.getSerrure().activerAvec(obj);
				
				if (this.getSerrure().getEtat() == Etat.VERROUILLE)
					this.etat = Etat.VERROUILLE;
				
				if (this.getSerrure().getEtat() == Etat.DEVERROUILLE)
					this.etat = Etat.OUVERT;
			}
		}
	}

	public Etat getEtat() {

		return this.etat;
	}

	public Serrure getSerrure() {

		return this.serrure;
	}

	public Piece getPieceAutreCote(Piece piece) {

		if (piece.equals(this.pieceA))
			return this.pieceB;

		if (piece.equals(this.pieceB))
			return this.pieceA;

		return null;
	}

	/**
	 * Donne le nom du vivant
	 * @return String nom 
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



