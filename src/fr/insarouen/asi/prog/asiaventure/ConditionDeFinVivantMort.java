package fr.insarouen.asi.prog.asiaventure;

import java.io.Serializable;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantMort extends ConditionDeFin implements Serializable {

	private Vivant vivant;

	public ConditionDeFinVivantMort(EtatDuJeu etatDuJeuConditionVerifiee, Vivant vivant) {

		super(etatDuJeuConditionVerifiee);
		this.vivant = vivant;
	 }

	public EtatDuJeu verifierCondition() {

		if(this.vivant.estMort())
			return this.getEtatConditionVerifiee();
		else
			return EtatDuJeu.ENCOURS;
	}
}