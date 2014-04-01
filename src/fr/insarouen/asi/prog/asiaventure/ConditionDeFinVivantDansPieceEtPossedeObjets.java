package fr.insarouen.asi.prog.asiaventure;

import java.io.Serializable;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFin implements Serializable {

	private ConditionDeFinConjonctionDeConditionDeFin conditionDeFinConjonctionDeConditionDeFin;

	public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etatDuJeuConditionVerifiee, Vivant vivant, Piece piece, Objet... objets) {

		super(etatDuJeuConditionVerifiee);
		ConditionDeFinVivantDansPiece conditionDeFinVivantDansPiece = new ConditionDeFinVivantDansPiece(etatDuJeuConditionVerifiee, vivant, piece);
		ConditionDeFinVivantPossedeObjets conditionDeFinVivantPossedeObjets = new ConditionDeFinVivantPossedeObjets(etatDuJeuConditionVerifiee, vivant, objets);

		this.conditionDeFinConjonctionDeConditionDeFin = new ConditionDeFinConjonctionDeConditionDeFin(etatDuJeuConditionVerifiee, conditionDeFinVivantDansPiece, conditionDeFinVivantPossedeObjets);
	 }

	public EtatDuJeu verifierCondition() {

		return this.conditionDeFinConjonctionDeConditionDeFin.verifierCondition();
	}
}