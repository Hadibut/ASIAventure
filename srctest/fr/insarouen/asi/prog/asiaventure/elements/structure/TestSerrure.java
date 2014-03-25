package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;

import org.junit.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TestSerrure {
	public Monde monde;
	public Serrure serrure;
	public Clef clef;
	public Piece pieceA;
	public Piece pieceB;
	public Porte porte;

	@Before
	public void avantTest()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		this.monde = new Monde("test");
		this.serrure = new Serrure(this.monde);
		this.pieceA = new Piece("PièceA", this.monde);
		this.pieceB = new Piece("PièceB", this.monde);
		this.porte = new Porte("Porte", this.monde, this.serrure, this.pieceA, this.pieceB);

		this.clef = this.serrure.creerClef();
	}

	@Test
	public void testConstructeur() {

		assertThat(this.porte.getEtat(), is(Etat.VERROUILLE));
	}

	@Test
	public void testClef()
	throws NomDEntiteDejaUtiliseDansLeMondeException {


		Clef clefNull = this.serrure.creerClef();
		assertNull(clefNull);
	}

	@Test(expected=ActivationImpossibleException.class)
	public void testActiver()
	throws ActivationImpossibleException {

		this.serrure.activer();
	}

	@Test(expected=ActivationImpossibleException.class)
	public void testActiverAvecMauvaiseClef()
	throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException {

		Serrure mauvaiseSerrure = new Serrure(this.monde);
		Porte mauvaisePorte = new Porte("Mauvaise Porte", this.monde, mauvaiseSerrure, this.pieceA, this.pieceB);
		Clef mauvaiseClef = mauvaiseSerrure.creerClef();

		this.serrure.activerAvec(mauvaiseClef);
	}

	@Test
	public void testEtatSerrure()
	throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException{

		PiedDeBiche piedDeBiche = new PiedDeBiche("piedDeBiche", this.monde);

		assertThat(this.serrure.getEtat(), is(Etat.VERROUILLE));

		this.serrure.activerAvec(this.clef);
		assertThat(this.serrure.getEtat(), is(Etat.DEVERROUILLE));

		this.serrure.activerAvec(this.clef);
		assertThat(this.serrure.getEtat(), is(Etat.VERROUILLE));

		this.serrure.activerAvec(piedDeBiche);
		assertThat(this.serrure.getEtat(), is(Etat.CASSE));

		this.serrure.activerAvec(this.clef);
		assertThat(this.serrure.getEtat(), is(Etat.CASSE));

		this.serrure.activerAvec(piedDeBiche);
		assertThat(this.serrure.getEtat(), is(Etat.CASSE));
	}
}

