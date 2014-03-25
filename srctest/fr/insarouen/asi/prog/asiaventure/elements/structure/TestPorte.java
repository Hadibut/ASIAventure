package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;

import org.junit.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TestPorte {
	public Monde monde;
	public Piece pieceA;
	public Piece pieceB;
	public Porte porte;
	public Serrure serrure;
	public Porte porteAvecSerrure;
	public Clef clefDeLaSerrure;

	@Before
	public void avantTest()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		this.monde = new Monde("test");
		this.pieceA = new Piece("PièceA", this.monde);
		this.pieceB = new Piece("PièceB", this.monde);
		this.porte = new Porte("Porte", this.monde, this.pieceA, this.pieceB);

		this.serrure = new Serrure("Serrure", this.monde);
		this.clefDeLaSerrure = this.serrure.creerClef();
		this.porteAvecSerrure = new Porte("Porte avec serrure", this.monde, serrure, this.pieceA, this.pieceB);
	}

	@Test
	public void testConstructeur() {

		assertThat(this.porte.getEtat(), is(Etat.FERME));
		assertThat(this.porteAvecSerrure.getEtat(), is(Etat.VERROUILLE));

		assertThat(this.porteAvecSerrure.getSerrure(), equalTo(this.serrure));
	}

	@Test
	public void testGetPieceAutreCote() {

		assertThat(this.porte.getPieceAutreCote(this.pieceA), is(this.pieceB));
		assertThat(this.porte.getPieceAutreCote(this.pieceB), is(this.pieceA));
	}

	@Test
	public void testActiverSansObjet()
	throws ActivationImpossibleException {

		this.porte.activer();
		assertThat(this.porte.getEtat(), is(Etat.OUVERT));
		this.porte.activer();
		assertThat(this.porte.getEtat(), is(Etat.FERME));
	}

	@Test(expected=ActivationImpossibleException.class)
	public void testActiverSansObjetException()
	throws ActivationImpossibleException {

		this.porteAvecSerrure.activer();
	}

	@Test
	public void testActivableAvecObjet()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		assertThat(this.porte.activableAvec(this.clefDeLaSerrure), is(true));
		assertThat(this.porteAvecSerrure.activableAvec(this.clefDeLaSerrure), is(true));

		PiedDeBiche piedDeBiche = new PiedDeBiche("Pied de biche", this.monde);
		assertThat(this.porte.activableAvec(piedDeBiche), is(true));

		Objet objet = new Objet("Objet quelconque", this.monde) {};
		assertThat(this.porte.activableAvec(objet), is(false));
	}

	@Test
	public void testActiverAvecObjet()
	throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException {

		PiedDeBiche piedDeBiche = new PiedDeBiche("piedDeBiche", this.monde);

		this.porte.activerAvec(piedDeBiche);
		assertThat(this.porte.getEtat(), is(Etat.CASSE));

		this.porte.activer();
		assertThat(this.porte.getEtat(), is(Etat.CASSE));

		this.porte.activerAvec(piedDeBiche);
		assertThat(this.porte.getEtat(), is(Etat.CASSE));

		this.porteAvecSerrure.activerAvec(this.clefDeLaSerrure);
		assertThat(this.porteAvecSerrure.getEtat(), is(Etat.OUVERT));

		this.porteAvecSerrure.activer();
		assertThat(this.porteAvecSerrure.getEtat(), is(Etat.FERME));

		this.porteAvecSerrure.activer();
		assertThat(this.porteAvecSerrure.getEtat(), is(Etat.OUVERT));

		this.porteAvecSerrure.activerAvec(this.clefDeLaSerrure);
		assertThat(this.porteAvecSerrure.getEtat(), is(Etat.VERROUILLE));

	}
}