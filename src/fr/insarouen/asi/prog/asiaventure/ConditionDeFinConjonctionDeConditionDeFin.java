package fr.insarouen.asi.prog.asiaventure;

import java.io.Serializable;

public class ConditionDeFinConjonctionDeConditionDeFin extends ConditionDeFin implements Serializable {

	private ConditionDeFin[] conditionsDeFin;

	public ConditionDeFinConjonctionDeConditionDeFin(EtatDuJeu etatDuJeuConditionVerifiee, ConditionDeFin... conditionsDeFin) {

		super(etatDuJeuConditionVerifiee);
		this.conditionsDeFin = conditionsDeFin;
	 }

	public EtatDuJeu verifierCondition() {

		for(ConditionDeFin conditionDeFin : this.conditionsDeFin) {

			if (!conditionDeFin.verifierCondition().equals(EtatDuJeu.ENCOURS))
				return EtatDuJeu.ENCOURS;
		}

		return this.getEtatConditionVerifiee();
	}
}