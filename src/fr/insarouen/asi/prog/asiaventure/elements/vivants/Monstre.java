package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.*;
import java.util.*;

public class Monstre extends Vivant implements Executable {

/**
	 * Crée un nouveau Vivant
	 * @param String nom
	 * @param Monde monde
	 * @param int pointVie
	 * @param int pointForce
	 * @param Piece piece
	 * @param Objet... objets
	 * @return monstre créé
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une entité du même nom est déjà présente dans ce monde
	 */
	public Monstre(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde, pointVie, pointForce, piece, objets);
	}

	public void executer()
	throws java.lang.Throwable {

		if (!this.estMort()) {

			Porte porte = this.choisirPorte();

			if (porte != null) {
				
				if (porte.getEtat() == Etat.FERME)
					porte.activerPorte();

				this.franchir(porte);
				this.setPointDeVie(this.getPointDeVie - 1);

				HashMap<String,Objet> objetsDuMonstre = this.objets;

				for (Objet objet : this.getPiece().getObjets()) {
					
					this.prendre(objet);
				}

				for (Objet objet : objetsDuMonstre) {
					
					this.deposer(objet);
				}


			}
		}

	}

	private Porte choisirPorte() {

		Collection<Porte> portes = this.piece.getPortes().values();
		Collections.shuffle(portes);

		for (Porte porte : portes) {
			
			if (porte.getEtat() != Etat.VERROUILLE)
				return porte;
		}

		return null;
	}
}