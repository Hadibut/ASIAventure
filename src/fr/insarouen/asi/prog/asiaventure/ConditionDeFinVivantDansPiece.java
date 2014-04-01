package fr.insarouen.asi.prog.asiaventure;

import java.io.Serializable;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;

public class ConditionDeFinVivantDansPiece extends ConditionDeFin implements Serializable {

	private Vivant vivant;
	private Piece piece;

	public ConditionDeFinVivantDansPiece(EtatDuJeu etatDuJeuConditionVerifiee, Vivant vivant, Piece piece) {

		super(etatDuJeuConditionVerifiee);
		this.vivant = vivant;
		this.piece = piece;
	 }

	public EtatDuJeu verifierCondition() {

		if(this.piece.contientVivant(this.vivant))
			return this.getEtatConditionVerifiee();
		else
			return EtatDuJeu.ENCOURS;
	}
}