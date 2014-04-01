package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.*;
import java.io.*;


public class Simulateur {

	private Monde monde;
	private int dureeDuJeu;
	private int tempsPourPrevenirLaFinDuJeu;


	public Simulateur(Monde monde, int dureeDuJeu, int tempsPourPrevenirLaFinDuJeu) {

		this.monde = monde;
		this.dureeDuJeu = dureeDuJeu;
		this.tempsPourPrevenirLaFinDuJeu = tempsPourPrevenirLaFinDuJeu;
	}

	public Simulateur(java.io.ObjectInputStream ois) 
	throws IOException, java.lang.ClassNotFoundException {

		this.monde = (Monde) ois.readObject();
	}

	public Simulateur(java.io.Reader reader) 
	throws IOException, NomDEntiteDejaUtiliseDansLeMondeException {

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
		String pointsDeVie = st.sval;

		st.nextToken();
		String pointsDeForce = st.sval;

		st.nextToken();
		String nomPiece = st.sval;

		new JoueurHumain(nomJoueur, this.monde, Integer.parseInt(pointsDeVie), Integer.parseInt(pointsDeForce), (Piece) this.monde.getEntite(nomPiece));
	}
	
	public void enregistrer(java.io.ObjectOutputStream oos) throws java.io.IOException {

		oos.writeObject(this.monde);
	}	

	// public void ajouterConditionsDeFin(java.util.Collection<ConditionDeFin> conditions) {


	// }

	// public void ajouterConditionDeFin(ConditionDeFin condition) {


	// }

	// public EtatDuJeu executerUnTour() throws java.lang.Throwable {


	// }

	// public EtatDuJeu executerNbTours(int n) throws java.lang.Throwable {


	// }

	// public EtatDuJeu executerJusquALaFin() throws java.lang.Throwable {


	// }

	// public void stopperJeu() {

	// }


}
