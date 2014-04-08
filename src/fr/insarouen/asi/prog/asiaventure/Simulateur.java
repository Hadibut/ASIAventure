package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.*;
import java.io.*;
import java.util.*;



public class Simulateur {

	private Monde monde;
	private int dureeDuJeu;
	private int tempsPourPrevenirLaFinDuJeu;
	private EtatDuJeu etatDuJeu;
	private ArrayList<ConditionDeFin> conditionsDeFin;


	public Simulateur(Monde monde, int dureeDuJeu, int tempsPourPrevenirLaFinDuJeu) {

		this.monde = monde;
		this.dureeDuJeu = dureeDuJeu;
		this.tempsPourPrevenirLaFinDuJeu = tempsPourPrevenirLaFinDuJeu;
		this.conditionsDeFin = new ArrayList<ConditionDeFin>();
	}

	public Simulateur(java.io.ObjectInputStream ois) 
	throws IOException, java.lang.ClassNotFoundException {

		this.monde = (Monde) ois.readObject();
		this.conditionsDeFin = (ArrayList<ConditionDeFin>) ois.readObject();
	}

	public Simulateur(java.io.Reader reader) 
	throws IOException, NomDEntiteDejaUtiliseDansLeMondeException {

		this.conditionsDeFin = new ArrayList<ConditionDeFin>();
		StreamTokenizer st = new StreamTokenizer(reader);	

		while (st.nextToken() != st.TT_EOF) {

			switch (st.sval) {
				case "Monde" : 
					this.creerMonde(st);
					break;
				case "Piece" : 
					this.creerPiece(st);
					break;
				case "PorteSerrure" : 
					this.creerPorteSerrure(st);
					break;
				case "Porte" : 
					this.creerPorte(st);
					break;
				case "Clef" :
					this.creerClef(st);
					break;
				case "JoueurHumain" :
					this.creerJoueurHumain(st);
					break;
				case "ConditionDeFinVivantDansPiece" :
					this.creerConditionDeFinVivantDansPiece(st);
					break;

			}

		}        
	}

	private void creerMonde(StreamTokenizer st)
	throws IOException, NomDEntiteDejaUtiliseDansLeMondeException {

		st.nextToken();
		this.monde = new Monde(st.sval);
	}

	private void creerPiece(StreamTokenizer st)
	throws IOException, NomDEntiteDejaUtiliseDansLeMondeException {

		st.nextToken();
		new Piece(st.sval, this.monde);
	}

	private void creerPorteSerrure(StreamTokenizer st)
	throws IOException, NomDEntiteDejaUtiliseDansLeMondeException {

		Serrure serrure = new Serrure(this.monde);		
		
		st.nextToken();
		String nomPorte = st.sval;

		st.nextToken();
		String nomPieceA = st.sval;

		st.nextToken();
		String nomPieceB = st.sval;

		new Porte(nomPorte, this.monde, serrure, (Piece) this.monde.getEntite(nomPieceA), (Piece) this.monde.getEntite(nomPieceB));
	}

	private void creerPorte(StreamTokenizer st)
	throws IOException, NomDEntiteDejaUtiliseDansLeMondeException {
		
		st.nextToken();
		String nomPorte = st.sval;

		st.nextToken();
		String nomPieceA = st.sval;

		st.nextToken();
		String nomPieceB = st.sval;

		new Porte(nomPorte, this.monde, (Piece) this.monde.getEntite(nomPieceA), (Piece) this.monde.getEntite(nomPieceB));
	}

	private void creerClef(StreamTokenizer st)
	throws IOException, NomDEntiteDejaUtiliseDansLeMondeException {
		
		st.nextToken();
		String nomPorte = st.sval;

		st.nextToken();
		String nomPiece = st.sval;

		Clef clef = ((Porte) this.monde.getEntite(nomPorte)).getSerrure().creerClef();

		((Piece) this.monde.getEntite(nomPiece)).deposer(clef);
	}

	private void creerJoueurHumain(StreamTokenizer st)
	throws IOException, NomDEntiteDejaUtiliseDansLeMondeException {
		
		st.nextToken();
		String nomJoueur = st.sval;

		st.nextToken();
		int pointsDeVie = (int)st.nval;

		st.nextToken();
		int pointsDeForce = (int)st.nval;

		st.nextToken();
		String nomPiece = st.sval;
		
		new JoueurHumain(nomJoueur, this.monde, pointsDeVie, pointsDeForce, (Piece) this.monde.getEntite(nomPiece));
	}



	private void creerConditionDeFinVivantDansPiece(StreamTokenizer st)
	throws IOException {
		
		st.nextToken();
		String etatDuJeu = st.sval;

		st.nextToken();
		String nomVivant = st.sval;

		st.nextToken();
		String nomPiece = st.sval;

		this.ajouterConditionDeFin(new ConditionDeFinVivantDansPiece(EtatDuJeu.valueOf(etatDuJeu), (Vivant) this.monde.getEntite(nomVivant), (Piece) this.monde.getEntite(nomPiece)));
	}
	
	public void enregistrer(ObjectOutputStream oos) throws IOException {

		oos.writeObject(this.monde);
		oos.writeObject(this.conditionsDeFin);
	}	

	public void ajouterConditionsDeFin(Collection<ConditionDeFin> conditions) {


	}

	public void ajouterConditionDeFin(ConditionDeFin condition) {

		this.conditionsDeFin.add(condition);
	}

	public EtatDuJeu executerUnTour() throws Throwable{

		for (ConditionDeFin condition : this.conditionsDeFin) {
			
			EtatDuJeu etat = condition.verifierCondition();

			if (!etat.equals(EtatDuJeu.ENCOURS))
				return etat;
		}

		Collection<Executable> executables = this.monde.getExecutables();

		for (Executable executable : executables) {
			if (executable instanceof JoueurHumain) {
				
				JoueurHumain joueur = (JoueurHumain) executable;
				System.out.println(joueur);
				System.out.println(joueur.getPiece());

				System.out.println("\nQue voulez vous faire ?");
				Scanner input = new Scanner(System.in);
				String ordre = input.nextLine();

				joueur.setOrdre(ordre);
			}
		}

		for (Executable executable : executables) {

			try {

				executable.executer();
			}
			catch(ASIAventureException e) {
				System.out.println(e.getMessage());
			}
		}


		return EtatDuJeu.ENCOURS;

	}

	// public EtatDuJeu executerNbTours(int n) throws Throwable {


	// }

	public EtatDuJeu executerJusquALaFin() throws Throwable{

		EtatDuJeu etat = EtatDuJeu.ENCOURS;

		while(etat.equals(EtatDuJeu.ENCOURS)) {

			etat = this.executerUnTour();
		}

		return etat;
	}

	// public void stopperJeu() {

	// }


}
