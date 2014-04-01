package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;

import java.io.*;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {

		int choix;
		boolean quitter = false;
		Scanner input = new Scanner(System.in);
		String cheminFichier;
		Simulateur jeu = null;

		do {

			System.out.println("\n---Menu---\n");
			System.out.println("\n1/ jouer");
			System.out.println("\n2/ charger un fichier de description");
			System.out.println("\n3/ sauver la partie actuelle");
			System.out.println("\n4/ charger une partie");
			System.out.println("\n5/ quitter");

			choix = input.nextInt();

			switch (choix) {
				case 1 : 
					
					break;
				case 2 : 
					
					System.out.println("\nChemin vers le fichier de description");
					cheminFichier = input.nextLine();

					try {

						Reader reader = new BufferedReader(new FileReader(cheminFichier));
						jeu = new Simulateur(reader);
					}
					catch (FileNotFoundException e) {

						System.out.println("\nChemin vers le fichier de description incorrect !");
					}
					catch (IOException e) {

						System.out.println("\nProblème de lecture !");
					}
					catch (NomDEntiteDejaUtiliseDansLeMondeException e) {

						System.out.println("\nFichier incorrect !");
					}

					break;
				case 3 :

					System.out.println("\nChemin pour sauvegarder a partie actuelle");
					cheminFichier = input.nextLine();

					try {

						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(cheminFichier));
						jeu.enregistrer(output);
					}
					catch (IOException e) {

						System.out.println("\nProblème d'écriture !");
					}

					break;
				case 4 :

					System.out.println("\nChemin vers la partie à charger");
					cheminFichier = input.nextLine();

					try {

						ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(cheminFichier));
						jeu = new Simulateur(inputStream);
					}
					catch (FileNotFoundException e) {

						System.out.println("\nChemin vers le fichier de description incorrect !");
					}
					catch (IOException e) {

						System.out.println("\nProblème de lecture !");
					}
					catch (ClassNotFoundException e) {

						System.out.println("\nFichier incorrect !");
					}
					
					break;
				case 5 :

					quitter = true;
					break;
			}
		} while (!quitter);
	}
}			 
