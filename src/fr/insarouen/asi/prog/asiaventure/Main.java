package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    
    public static void main(String[] args) throws Throwable {

        int choix;
        boolean quitter = false;
        Scanner input = new Scanner(System.in);
        String cheminFichier;
        Simulateur jeu = null;
        
        do {
            
            System.out.println("\n--- MENU ---");
            System.out.println("1/ jouer");
            System.out.println("2/ charger un fichier de description");
            System.out.println("3/ sauver la partie actuelle");
            System.out.println("4/ charger une partie");
            System.out.println("5/ quitter");
            
            try {
                
                choix = input.nextInt();
                
                switch (choix) {
                case 1 : 
                    String continuer = new String("o");
                    EtatDuJeu etatDuJeu = EtatDuJeu.ENCOURS;
                    
                    while (etatDuJeu.equals(EtatDuJeu.ENCOURS) && continuer.equals("o")) {
                        
                        etatDuJeu = jeu.executerUnTour();
                        
                        if (etatDuJeu.equals(EtatDuJeu.ENCOURS)) {
                            
                            System.out.println("\nContinuer à jouer ? (o / n)");
                            continuer = input.next();
                        }
                    }
                    
                    if (etatDuJeu.equals(EtatDuJeu.ECHEC)) {
                        
                        System.out.println("\n--- DEFAITE ---");
                        System.out.println("Vous avez perdu.");
                    }
                    else if (etatDuJeu.equals(EtatDuJeu.SUCCES)) {
                        
                        System.out.println("\n--- VICTOIRE ---");
                        System.out.println("Vous avez gagné.");
                    }
                    else {
                        
                        System.out.println("\n--- PAUSE ---");
                        System.out.println("Jeu mis en pause.");
                    }
                    break;
                case 2 : 
                    
                    System.out.println("\nVeuillez renseigner le chemin vers le fichier de description...");
                    cheminFichier = input.next();
                    
                    try {
                        
                        Reader reader = new BufferedReader(new FileReader(cheminFichier));
                        jeu = new Simulateur(reader);
                    }
                    catch (FileNotFoundException e) {
                        
                        System.out.println("\n--- ERREUR ---");
                        System.out.println("Chemin vers le fichier de description incorrect.");
                    }
                    catch (IOException e) {
                        
                        System.out.println("\n--- ERREUR ---");
                        System.out.println("Problème de lecture.");
                    }
                    catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
                        
                        System.out.println("\n--- ERREUR ---");
                        System.out.println("Fichier incorrect.");
                    }
                    break;
                case 3 :
                    
                    System.out.println("\nVeuillez renseigner le chemin pour sauvegarder la partie actuelle...");
                    cheminFichier = input.next();
                    
                    try {
                        
                        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(cheminFichier));
                        jeu.enregistrer(output);
                    }
                    catch (IOException e) {
                        
                        System.out.println("\n--- ERREUR ---");
                        System.out.println("Problème d'écriture.");
                    }
                    break;
                case 4 :
                    
                    System.out.println("\nVeuillez renseigner le chemin vers la partie à charger...");
                    cheminFichier = input.next();
                    
                    try {
                        
                        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(cheminFichier));
                        jeu = new Simulateur(inputStream);
                    }
                    catch (FileNotFoundException e) {
                        
                        System.out.println("\n--- ERREUR ---");
                        System.out.println("\nChemin vers le fichier de description incorrect.");
                    }
                    catch (IOException e) {
                        
                        System.out.println("\n--- ERREUR ---");
                        System.out.println("Problème de lecture.");
                    }
                    catch (ClassNotFoundException e) {
                        
                        System.out.println("\n--- ERREUR ---");
                        System.out.println("Fichier incorrect.");
                    }
                    break;
                case 5 :
                    quitter = true;
                    break;
                }
            }
            catch (InputMismatchException e) {
                
                System.out.println("\n--- ERREUR ---");
                            System.out.println("Un nombre est requis.");
                            input.next();
            }
            
        } while (!quitter);
    }
}			 
