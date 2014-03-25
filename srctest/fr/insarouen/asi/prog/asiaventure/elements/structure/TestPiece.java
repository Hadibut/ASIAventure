package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;

import org.junit.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TestPiece {
	public Monde monde;
	public Piece piece;
	public Piece pieceB;
	public PiedDeBiche objet;
	public Vivant vivant;
	public Porte porte;

	@Before
	public void avantTest()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		this.monde = new Monde("test");
		this.piece = new Piece("Pièce", this.monde);
		this.pieceB = new Piece("PièceB", this.monde);
		this.objet = new PiedDeBiche("Pied de biche", this.monde);
		this.porte = new Porte("Porte", this.monde, this.piece, this.pieceB);

		this.vivant = new Vivant ("Vivant", this.monde, 10, 5, this.piece);
	}

	@Test(expected=ObjetAbsentDeLaPieceException.class)
	public void testRetirerObjetAbsent()
	throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		this.piece.retirer(this.objet);
	}

	@Test(expected=ObjetNonDeplacableDeLaPieceException.class)
	public void testRetirerObjetNonDeplacable()
	throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		Objet objetNonDeplacable = new Objet("Non déplacable", this.monde) {};
		this.piece.deposer(objetNonDeplacable);
		this.piece.retirer(objetNonDeplacable);
	}

	@Test
	public void testDeposerPuisRetirerParObjet()
	throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		assertThat(this.piece.contientObjet(this.objet), is(false));
		this.piece.deposer(this.objet);
		assertThat(this.piece.contientObjet(this.objet), is(true));
		this.piece.retirer(this.objet);
		assertThat(this.piece.contientObjet(this.objet), is(false));
	}

	@Test
	public void testDeposerPuisRetirerParString()
	throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableDeLaPieceException {

		assertThat(this.piece.contientObjet(this.objet.getNom()), is(false));
		this.piece.deposer(this.objet);
		assertThat(this.piece.contientObjet(this.objet.getNom()), is(true));
		this.piece.retirer(this.objet.getNom());
		assertThat(this.piece.contientObjet(this.objet.getNom()), is(false));
	}

	@Test(expected=VivantAbsentDeLaPieceException.class)
	public void testSortirVivantAbsent()
	throws VivantAbsentDeLaPieceException {

		this.piece.sortir(this.vivant);
		this.piece.sortir(this.vivant);
	}

	@Test
	public void testSortirPuisEntreVivantParObjet()
	throws VivantAbsentDeLaPieceException {

		assertThat(this.piece.contientVivant(this.vivant), is(true));
		this.piece.sortir(this.vivant);
		assertThat(this.piece.contientVivant(this.vivant), is(false));
		this.piece.entrer(this.vivant);
		assertThat(this.piece.contientVivant(this.vivant), is(true));
	}

	@Test
	public void testSortirPuisEntreVivantParString()
	throws VivantAbsentDeLaPieceException {

		assertThat(this.piece.contientVivant(this.vivant.getNom()), is(true));
		this.piece.sortir(this.vivant.getNom());
		assertThat(this.piece.contientVivant(this.vivant.getNom()), is(false));
		this.piece.entrer(this.vivant);
		assertThat(this.piece.contientVivant(this.vivant.getNom()), is(true));
	}
}

