package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;

import org.junit.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TestPorte {
	public Monde monde;
	public Piece pieceA;
	public Piece pieceB;
	public Porte porte;

	@Before
	public void avantTest()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		this.monde = new Monde("test");
		this.pieceA = new Piece("PièceA", this.monde);
		this.pieceB = new Piece("PièceB", this.monde);
		this.porte = new Porte("Porte", this.monde, this.pieceA, this.pieceB);
	}

	@Test
	public void testConstructeur()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		assertThat(this.porte.getEtat(), is(Etat.FERME));

		Serrure serrure = new Serrure("Serrure", this.monde);
		Porte porteAvecSerrure = new Porte("Porte avec serrure", this.monde, serrure, this.pieceA, this.pieceB);
		assertThat(porteAvecSerrure.getEtat(), is(Etat.VERROUILLE));
	}

	@Test
	public void testGetPieceAutreCote() {

		assertThat(this.porte.getPieceAutreCote(this.pieceA), is(this.pieceB));
		assertThat(this.porte.getPieceAutreCote(this.pieceB), is(this.pieceA));
	}

	@Test
	public void testEtatPorte()
	throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException{

		PiedDeBiche piedDeBiche = new PiedDeBiche("piedDeBiche", this.monde);

		this.porte.activer();
		assertThat(this.porte.getEtat(), is(Etat.OUVERT));
		this.porte.activer();
		assertThat(this.porte.getEtat(), is(Etat.FERME));

		this.porte.activerAvec(piedDeBiche);
		assertThat(this.porte.getEtat(), is(Etat.CASSE));

		this.porte.activer();
		assertThat(this.porte.getEtat(), is(Etat.CASSE));

		this.porte.activerAvec(piedDeBiche);
		assertThat(this.porte.getEtat(), is(Etat.CASSE));
	}
}