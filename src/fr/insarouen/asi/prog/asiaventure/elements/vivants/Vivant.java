package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.*;
import java.util.*;

public class Vivant extends Entite {

	
	private Piece piece;
	private int pointVie;
	private int pointForce;
	private HashMap<String,Objet> objets;
	/**
	 * Crée un nouveau Vivant
	 * @param String nom
	 * @param Monde monde
	 * @param int pointVie
	 * @param int pointForce
	 * @param Piece piece
	 * @param Objet... objets
	 * @return vivant créé
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une entité du même nom est déjà présente dans ce monde
	 */
	public Vivant(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
		this.objets = new HashMap<String,Objet>();
		for (int i = 0; i < objets.length; i++) {
			
			this.objets.put(objets[i].getNom(), objets[i]);
		}
		this.piece = piece;
		this.pointVie = pointVie;
		this.pointForce = pointForce;

		this.piece.entrer(this);

	}

	/**import java.util.*;

	 * Ajoute un objet dans le vivant
	 * @param Objet objet
	 * @throws ObjetAbsentDeLaPieceException        si l'objet est absent de la pièce où se situe le vivant
	 * @throws ObjetNonDeplacableDeLaPieceException si l'objet n'est pas déplacable
	 */
	public void prendre(Objet objet)
	throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		Objet retour = this.piece.retirer(objet);

		this.objets.put(retour.getNom(), retour);
	}

	/**
	 * Ajoute un objet dans le vivant
	 * @param String nomObj
	 * @throws ObjetAbsentDeLaPieceException        si l'objet est absent de la pièce où se situe le vivant
	 * @throws ObjetNonDeplacableDeLaPieceException si l'objet n'est pas déplacable
	 */
	public void prendre(String nomObj)
	throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		Objet retour = this.piece.retirer(nomObj);

		this.objets.put(retour.getNom(), retour);
	}

	/**
	 * Retire un objet du vivant
	 * @param Objet objet
	 * @throws ObjetNonPossedeParLeVivantException si le vivant ne possède pas l'objet
	 */
	public void deposer(Objet objet)
	throws ObjetNonPossedeParLeVivantException {

		Objet retour = this.objets.remove(objet.getNom());

		if (retour == null)
			throw new ObjetNonPossedeParLeVivantException("L'objet " + objet.getNom() + " n'est pas en possession du vivant.");

		this.piece.deposer(retour);
	}

	/**
	 * Retire un objet du vivant
	 * @param String nomObj
	 * @throws ObjetNonPossedeParLeVivantException si le vivant ne possède pas l'objet
	 */
	public void deposer(String nomObj)
	throws ObjetNonPossedeParLeVivantException {

		Objet retour = this.objets.remove(nomObj);

		if (retour == null)
			throw new ObjetNonPossedeParLeVivantException("L'objet " + nomObj + " n'est pas en possession du vivant.");

		this.piece.deposer(retour);
	}

	/**
	 * Changer la pièce du vivant
	 * @param Porte porte La porte a franchir
	 */
	public void franchir(Porte porte)
	throws PorteFermeException, PorteInexistanteDansLaPieceException {

		if (!this.getPiece().aLaPorte(porte))
			throw new PorteInexistanteDansLaPieceException("La porte " + porte.getNom() + " n'existe pas dans la pièce " + this.getPiece().getNom() + ".");

		if (porte.getEtat() != Etat.OUVERT)
			throw new PorteFermeException("La porte " + porte.getNom() + " est fermée.");

		try {

			this.getPiece().sortir(this);
			Piece pieceAutreCote = porte.getPieceAutreCote(this.getPiece());
			pieceAutreCote.entrer(this);
			this.piece = pieceAutreCote;
		}
		catch(VivantAbsentDeLaPieceException e) {

			throw new RuntimeException("Attention tu es un nul !", e);
		}
	}

	/**
	 * Changer la pièce du vivant
	 * @param Porte porte La porte a franchir
	 */
	public void franchir(String nomPorte)
	throws PorteFermeException, PorteInexistanteDansLaPieceException {


		if (!this.getPiece().aLaPorte(nomPorte))
			throw new PorteInexistanteDansLaPieceException("La porte " + nomPorte + " n'existe pas dans la pièce " + this.getPiece().getNom() + ".");

		Porte porte = this.getPiece().getPorte(nomPorte);

		if (porte.getEtat() != Etat.OUVERT)
			throw new PorteFermeException("La porte " + porte.getNom() + " est fermée.");

		try {

			this.getPiece().sortir(this);
			Piece pieceAutreCote = porte.getPieceAutreCote(this.getPiece());
			pieceAutreCote.entrer(this);
			this.piece = pieceAutreCote;
		}
		catch(VivantAbsentDeLaPieceException e) {

			throw new RuntimeException("Attention tu es un nul !", e);
		}
	}

	/**
	 * Retourne un objet présent dans le vivant via son nom
	 * @param String nomObj le nom de l'objet que l'on cherche
	 * @return Objet l'objet recherché
	 * @throws ObjetNonPossedeParLeVivantException si le vivant ne possède pas cet objet
	 */
	public Objet getObjet(String nomObj)
	throws ObjetNonPossedeParLeVivantException {

		Objet retour = this.objets.get(nomObj);

		if (retour == null)
			throw new ObjetNonPossedeParLeVivantException("L'objet " + nomObj + " n'est pas en possession du vivant.");

		return retour;
	}

	/**
	 * Est-ce que l'objet est présent dans le vivant
	 * @param Objet objet
	 * @return Booleen
	 */
	public Boolean possede(Objet objet) {

		return this.objets.containsKey(objet.getNom());
	}

	/**
	 * Getter piece
	 * @return Piece pièce
	 */
	public Piece getPiece() {
		return this.piece;
	}


	/**
	 * Getter pointVie
	 * @return int Points de vie
	 */
	public int getPointVie() {
		return this.pointVie;
	}

	/**
	 * Getter pointForce
	 * @return int Points de force
	 */
	public int getPointForce() {
		return this.pointForce;
	}

	/**
	 * Est-ce que le vivant est mort ?
	 * @return Booleen vrai si le vivant a 0 ou moins points de vie
	 */
	public Boolean estMort() {
		return (this.getPointVie() <= 0);
	}

/**
	 * Setter pointVie
	 * @param int Points de vie
	 */
	public void setPointVie(int pointVie) {
		
		this.pointVie = pointVie;
	}
	/**
	 * Affiche un vivant
	 * @return String
	 */
	public String toString() {

		StringBuilder chaine = new StringBuilder();
		chaine.append("\n Le vivant appelé \" ");
		chaine.append(this.getNom());
		chaine.append("\" (");
		chaine.append(this.piece.getNom());
		chaine.append(") \n");
		chaine.append(" * PV : ");
		chaine.append(this.pointVie);
		chaine.append("\n");
		chaine.append(" * PF : ");
		chaine.append(this.pointForce);
		chaine.append("\n");

		if (this.objets.size() == 0)
			chaine.append(" * Aucun objet");
		else {
			
			chaine.append(" * Objets : ");

			for (String nomObjet : this.objets.keySet()) {
				
				chaine.append("\n   * ");
				chaine.append(nomObjet);
				chaine.append(" ");
			}
		}

		return chaine.toString();
	}
}
