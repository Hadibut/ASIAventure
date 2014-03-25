package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;

import org.junit.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestVivant {
	public Monde monde;
	public Piece piece;
	public PiedDeBiche objet;
	public Vivant vivant;


	@Before
	public void avantTest()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		this.monde = new Monde("test");
		this.piece = new Piece("Pièce", this.monde);
		this.objet = new PiedDeBiche("Pied de biche", this.monde);

		this.vivant = new Vivant ("Vivant", this.monde, 10, 5, this.piece, this.objet);
	}

	@Test
	public void testConstructeur() {

		assertThat(this.vivant.getPointVie(), equalTo(10));
		assertThat(this.vivant.getPointForce(), equalTo(5));
		assertThat(this.vivant.possede(this.objet), is(true));
	}

	@Test(expected=ObjetAbsentDeLaPieceException.class)
	public void testPrendreObjetNonExistant()
	throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		this.vivant.prendre("Pied de biche");
	}

	@Test(expected=ObjetNonPossedeParLeVivantException.class)
	public void testDeposerObjetNonPossede()
	throws ObjetNonPossedeParLeVivantException {

		this.vivant.deposer("truc");
	}

	@Test
	public void testDeposerObjetPuisPrendreParString()
	throws ObjetNonPossedeParLeVivantException,ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException  {

		this.vivant.deposer("Pied de biche");
		assertThat(this.vivant.possede(this.objet), is(false));
		this.vivant.prendre("Pied de biche");
		assertThat(this.vivant.possede(this.objet), is(true));
	}

	@Test
	public void testDeposerObjetPuisPrendreParObjet()
	throws ObjetNonPossedeParLeVivantException, ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException  {

		this.vivant.deposer(this.objet);
		assertThat(this.vivant.possede(this.objet), is(false));
		this.vivant.prendre(this.objet);
		assertThat(this.vivant.possede(this.objet), is(true));
	}

	@Test
	public void testGetObjet()
	throws ObjetNonPossedeParLeVivantException {

		assertThat(this.objet, equalTo(this.vivant.getObjet("Pied de biche")));
	}

	@Test(expected=ObjetNonPossedeParLeVivantException.class)
	public void testGetObjetNonPossede()
	throws ObjetNonPossedeParLeVivantException {

		Objet objet = this.vivant.getObjet("truc");
	}

	@Test
	public void testVivantMort()
	throws ObjetNonPossedeParLeVivantException, NomDEntiteDejaUtiliseDansLeMondeException {

		assertThat(this.vivant.estMort(), is(false));
		Vivant vivantMort = new Vivant ("Vivant Mort", this.monde, 0, 5, this.piece, this.objet);
		assertThat(vivantMort.estMort(), is(true));

	}

	@Test(expected=PorteFermeException.class)
	public void testVivantFranchirActivation()
	throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException, PorteFermeException, PorteInexistanteDansLaPieceException {

		Piece piece2 = new Piece("Pièce2", this.monde);
		Porte porte = new Porte("porte", this.monde, this.piece, piece2);
		vivant.franchir(porte);
	}

	@Test
	public void testVivantFranchir()
	throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException, PorteFermeException, PorteInexistanteDansLaPieceException {

		Piece piece2 = new Piece("Pièce2", this.monde);
		Porte porte = new Porte("porte", this.monde, this.piece, piece2);
		assertThat(piece.contientVivant(vivant), is(true));
		assertThat(piece2.contientVivant(vivant), is(false));

		porte.activer();
		vivant.franchir(porte);
		assertThat(piece.contientVivant(vivant), is(false));
		assertThat(piece2.contientVivant(vivant), is(true));
	
	}

	@Test
	public void testVivantFranchirAvecString()
	throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException, PorteFermeException, PorteInexistanteDansLaPieceException {

		Piece piece2 = new Piece("Pièce2", this.monde);
		Porte porte = new Porte("porte", this.monde, this.piece, piece2);
		assertThat(piece.contientVivant(vivant), is(true));
		assertThat(piece2.contientVivant(vivant), is(false));
		
		porte.activer();
		vivant.franchir(porte.getNom());
		assertThat(piece.contientVivant(vivant), is(false));
		assertThat(piece2.contientVivant(vivant), is(true));
	
	}
}
