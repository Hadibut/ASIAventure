package fr.insarouen.asi.prog.asiaventure;

import java.io.Serializable;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin implements Serializable {

	private Vivant vivant;
	private Objet[] objets;

	public ConditionDeFinVivantPossedeObjets(EtatDuJeu etatDuJeuConditionVerifiee, Vivant vivant, Objet... objets) {

		super(etatDuJeuConditionVerifiee);
		this.vivant = vivant;
		this.objets = objets;
	
	 }

	public EtatDuJeu verifierCondition() {

		for(Objet objet : objets) {

			if (!this.vivant.possede(objet))
				return EtatDuJeu.ENCOURS;
		}

		return this.getEtatConditionVerifiee();
	}
}