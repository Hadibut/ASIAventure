package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.*;

import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class JoueurHumain extends Vivant implements Executable {

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

    public void executer() 
	throws Throwable {

        try {

            String[] parametresOrdre = this.getParametresOrdre(this.ordre.split(" "));
	
            try {

                this.getMethodeOrdre(this.ordre.split(" ")).invoke(this, (Object[]) parametresOrdre);

            }
            catch (InvocationTargetException e) {
			
                throw e.getCause();
            }
            catch (Exception e) {

                throw new CommandeImpossiblePourLeVivantException("Impossible de lancer cette commande");
            }            
        }
        catch (ArrayIndexOutOfBoundsException e) {

            throw new CommandeImpossiblePourLeVivantException("Mauvais nombre d'arguments. (1, 2 ou 4 mots requis)");
        }
    }

    private Method getMethodeOrdre(String[] parametres) 
	throws NoSuchMethodException {

        if (parametres.length == 4)
            return this.getClass().getMethod("commande" + parametres[0].substring(0, 1).toUpperCase() + parametres[0].substring(1), String.class, String.class);
        else if (parametres.length == 2)
            return this.getClass().getMethod("commande" + parametres[0].substring(0, 1).toUpperCase() + parametres[0].substring(1), String.class); 
        else
            return this.getClass().getMethod("commande" + parametres[0].substring(0, 1).toUpperCase() + parametres[0].substring(1)); 
    }

    private String[] getParametresOrdre(String[] ordres) {

        String[] resultat;

        if (ordres.length > 2) {
			
            resultat = new String[2];
            resultat[0] = ordres[1];
            resultat[1] = ordres[3];
        }
        else if (ordres.length == 2) {
			
            resultat = new String[1];
            resultat[0] = ordres[1];
        }
        else
            resultat = new String[0];

        return resultat;
    }

    public void setOrdre (String ordre) {

        this.ordre = ordre;

    }

    public void commandePrendre(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

        this.prendre(nomObjet);
    }

    public void commandePoser(String nomObjet) throws ObjetNonPossedeParLeVivantException {

        this.deposer(nomObjet);
    }
	
    public void commandeFranchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException {

        this.franchir(nomPorte);
    }
	
    public void commandeOuvrirPorte(String nomPorte) throws ActivationImpossibleException, PorteInexistanteDansLaPieceException {

        this.getPiece().getPorte(nomPorte).activer();

    } 	

    public void commandeOuvrirPorte(String nomPorte, String nomObjet)throws ActivationImpossibleException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException{

        this.getPiece().getPorte(nomPorte).activerAvec(this.getObjet(nomObjet));

    }

    public void commandeAide() {
    
        System.out.println("\n--- AIDE ---");
        System.out.println("Commandes disponibles :");
        System.out.println("    * ouvrirPorte nomPorte");
        System.out.println("    * ouvrirPorte nomPorte avec nomObjet");
        System.out.println("    * franchir nomPorte");
        System.out.println("    * prendre nomObjet");
        System.out.println("    * poser nomObjet");
    }
}
