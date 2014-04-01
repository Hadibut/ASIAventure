package fr.insarouen.asi.prog.asiaventure;

import java.io.Serializable;

public abstract class ConditionDeFin implements Serializable {

	private EtatDuJeu etatDuJeuConditionVerifiee;

	public ConditionDeFin(EtatDuJeu etatDuJeuConditionVerifiee) {

		this.etatDuJeuConditionVerifiee = etatDuJeuConditionVerifiee;
	 }

	public EtatDuJeu getEtatConditionVerifiee(){

		return this.etatDuJeuConditionVerifiee;
	}

	public abstract EtatDuJeu verifierCondition();
}