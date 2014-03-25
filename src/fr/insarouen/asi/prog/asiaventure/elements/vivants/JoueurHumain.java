package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.*;
import java.util.*;

public class JoueurHumain extends Vivant {

	private Piece piece;
	private int pointVie;
	private int pointForce;
	private HashMap<String,Objet> objets;
	private String ordre;


/**
	 * Crée un nouveau joueur humain
	 * @param String nom
	 * @param Monde monde
	 * @param int pointVie
	 * @param int pointForce
	 * @param Piece piece
	 * @param Objet... objets
	 * @return JoueurHumain joueur humain créé
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une entité du même nom est déjà présente dans ce monde
	 */
	public JoueurHumain(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde, pointVie, pointForce, piece, objets);
	}	

	public void executer()  {

		// String ordre1;
		// String ordre2;
		// String ordre3;

		// switch () {
		// 	case 1 : ordre1 = "ouvrir";
		// 		commandeOuvrirPorte(ordre2);
		// 		break;
		// 	case 2 : ordre1 = "ouvrirAvec";	
		// 		commandeOuvrirPorte(ordre2,ordre3)
		// 		break;
		// 	case 3 : ordre1 = "franchir";
		// 		commandeFranchir(ordre2);
		// 		break;	
		// 	case 4 : ordre1 = "prendre";
		// 		commandePrendre(ordre2);
		// 		break;
		// 	case 5 : ordre1 = "deposer";
		// 		commandePoser(ordre2);		
		// }
	}	

	public void setOrdre (String ordre) {

		this.ordre = ordre ;

	}

	private void commandePrendre(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		this.prendre(nomObjet);
	}

	private void commandePoser(String nomObjet) throws ObjetNonPossedeParLeVivantException {

		this.deposer(nomObjet);
	}
	
	private void commandeFranchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException {

		this.franchir(nomPorte);
	}
	
	private void commandeOuvrirPorte(String nomPorte) throws ActivationImpossibleException {

		this.piece.getPorte(nomPorte).activer();

	} 	

	private void commandeOuvrirPorte(String nomPorte, String nomObjet)throws ActivationImpossibleException, ObjetNonPossedeParLeVivantException{

		this.piece.getPorte(nomPorte).activerAvec(this.getObjet(nomObjet));

	}  	

}