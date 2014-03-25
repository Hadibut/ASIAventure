package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;

public class ClassMain {
	
	public static void main(String[] args) {
		
		System.out.println("\n---- ---- TEST DE MONDE.JAVA ---- ----");
			
		Monde monde = new Monde("MondeTest");
		Monde autreMonde = new Monde("AutreMonde");
		System.out.println("\nOn affiche le monde : " + monde);

		try {

			// On ajoute Test au monde
			System.out.println("\nOn ajoute l'objet Test au monde.");
			Objet objet = new Objet("Test", monde){};
			
			Objet objetAutreMonde = new Objet("Test", autreMonde){};

			// Affichage du monde avec Test
			System.out.println("On affiche l'objet Test ajouté au monde (getEntite): " + monde.getEntite("Test"));
			System.out.println("On affiche le monde avec test : " + monde);

			// On essaye d'ajouter un deuxième objet nommé test dans le monde.
			System.out.println("\nOn essaye d'ajouter un deuxième objet Test au monde.");
			try {

				Objet objetEgal = new Objet("Test", monde){};
				System.out.println("L'objet Test est égal à l'objet Test : " + objet.equals(objetEgal));
			}
			catch (NomDEntiteDejaUtiliseDansLeMondeException e) {

				System.err.println("\n---- ATTENTION EXCEPTION ----\nException NomDEntiteDejaUtiliseDansLeMondeException : " + e.getMessage() + "\n");
			}

			// On crée un autre objet
			System.out.println("On affiche l'objet Autre ajouté au monde (getEntite) : " + monde.getEntite("Autre"));
			System.out.println("On affiche l'objet Absent absent du monde (getEntite) : " + monde.getEntite("Absent"));

			System.out.println("\nOn ajoute un autre objet, un Mur (ElementStructurel) et un Pied de Biche (PiedDeBiche) au monde.");

			Objet autreObjet = new Objet("Autre", monde){};
			ElementStructurel elementStructurel = new ElementStructurel("Mur", monde){};
			PiedDeBiche pieddebiche = new PiedDeBiche("Pied de biche", monde);
			System.out.println("\nOn affiche le monde rempli : " + monde);

			System.out.println("\n---- ---- TEST DES ÉGALITÉS ---- ----");
			System.out.println("Affiche l'objet Test : " + objet);
			System.out.println("Affiche l'objet Test dans l'autre monde : " + objetAutreMonde);
			System.out.println("Affiche l'objet Autre : " + autreObjet);
			System.out.println("L'objet Test est différent de l'objet Test dans l'autre monde : " + objet.equals(objetAutreMonde));
			System.out.println("L'objet Test est différent de l'objet Autre : " + objet.equals(autreObjet));
			System.out.println("L'objet Test est différent de l'élement structurel Mur : " + objet.equals(elementStructurel));

			System.out.println("\n---- ---- TEST DE OBJET.JAVA ---- ----");
			System.out.println("L'objet Test est déplacable ? " + objet.estDeplacable());
			System.out.println("L'objet Pied de biche est déplacable ? " + pieddebiche.estDeplacable());
		
			// Objets Pièces
			System.out.println("\n---- ---- TEST DE PIECE.JAVA ---- ----");
			Piece piece = new Piece("Pièce", monde);
			System.out.println("\nAffiche la piece : " + piece);
			piece.deposer(objet);
			System.out.println("\nAffiche la piece avec Test : " + piece);
			System.out.println("\nTest est présent (via objet) : " + piece.contientObjet(objet));
			System.out.println("\nTest est présent (via nom) : " + piece.contientObjet(objet.getNom()));
			piece.deposer(pieddebiche);
			System.out.println("\nAffiche la piece avec Test et Pied de biche : " + piece);
			System.out.println("\nPied de biche est présent (via objet) : " + piece.contientObjet(pieddebiche));
			System.out.println("\nPied de biche est présent (via nom) : " + piece.contientObjet(pieddebiche.getNom()));

			Objet retour = piece.retirer(pieddebiche);
			System.out.println("\nObjet récupéré : " + retour);
			System.out.println("\nPied de biche n'est plus présent (via objet) : " + piece.contientObjet(pieddebiche));
			System.out.println("\nPied de biche n'est plus présent (via nom) : " + piece.contientObjet(pieddebiche.getNom()));
			System.out.println("\nAffiche la piece avec Test et sans Pied de biche : " + piece);

			System.out.println("\n---- ---- TEST DE VIVANT.JAVA ---- ----");

			Objet objetVivant = new Objet("Objet Vivant", monde){};
			Objet objetVivantEntrant = new Objet("Objet Vivant Entrant", monde){};
			System.out.println("\nAffiche la piece avec Test et sans Vivant : " + piece);
			Vivant vivant = new Vivant ("Vivant", monde, 10, 5, piece, pieddebiche);
			System.out.println("\nAffiche le vivant avec ses points : " + vivant);
			
			System.out.println("\nOn essaye de prendre un objet non déplacable de la pièce");
			try {

				vivant.prendre(objet);
			}
			catch (ObjetNonDeplacableDeLaPieceException e) {

				System.err.println("\n---- ATTENTION EXCEPTION ----\nException ObjetNonDeplacableDeLaPieceException : " + e.getMessage() + "\n");
			}

			
			System.out.println("\nOn essaye de prendre un objet non présent de la pièce");
			try {

				vivant.prendre(pieddebiche);
			}
			catch (ObjetAbsentDeLaPieceException e) {

				System.err.println("\n---- ATTENTION EXCEPTION ----\nException ObjetAbsentDeLaPieceException : " + e.getMessage() + "\n");
			}
			
			System.out.println("\nOn essaye de déposer un objet que l'on ne possède pas");
			try {

				vivant.deposer(objet);
			}
			catch (ObjetNonPossedeParLeVivantException e) {

				System.err.println("\n---- ATTENTION EXCEPTION ----\nException ObjetNonPossedeParLeVivantException : " + e.getMessage() + "\n");
			}
			
			System.out.println("\nOn essaye de faire sortir deux fois un vivant d'une pièce.");
			try {

				piece.sortir(vivant);
				piece.sortir(vivant);
			}
			catch (VivantAbsentDeLaPieceException e) {

				System.err.println("\n---- ATTENTION EXCEPTION ----\nException VivantAbsentDeLaPieceException : " + e.getMessage() + "\n");
			}

			System.out.println("\nAffiche la piece avec Test et avec Vivant : " + piece);
			piece.sortir(vivant);
			System.out.println("\nAffiche la piece avec Test et sans Vivant (après sortir) : " + piece);

			Vivant vivantEntrant = new Vivant("Vivant Entrant", monde, 15, 10, piece, objetVivantEntrant);
			System.out.println("\nAffiche la piece avec Test et avec Vivant Entrant : " + piece);

			piece.entrer(vivant);
			System.out.println("\nAffiche la piece avec Test et avec Vivant et Vivant Entrant : " + piece);

			System.out.println("\nvivantEntrant est présent (via vivant) : " + piece.contientVivant(vivantEntrant));
			System.out.println("\nvivantEntrant est présent (via nom) : " + piece.contientVivant(vivantEntrant.getNom()));
			System.out.println("\nAffiche la piece : " + piece);
			piece.sortir(vivantEntrant.getNom());
			System.out.println("\nvivantEntrant n'est plus présent : " + piece.contientVivant(vivantEntrant));

		}
		catch (NomDEntiteDejaUtiliseDansLeMondeException e) {

			System.err.println(e.getMessage());
		}
		catch (ObjetAbsentDeLaPieceException e) {
			
			System.err.println(e.getMessage());
		}
		catch (VivantAbsentDeLaPieceException e) {

			System.err.println(e.getMessage());
		}
		catch (ObjetNonDeplacableDeLaPieceException e) {

			System.err.println(e.getMessage());
		}
	}
}			 
