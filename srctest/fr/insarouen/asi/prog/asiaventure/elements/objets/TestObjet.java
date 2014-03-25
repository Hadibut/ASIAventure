package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;

import org.junit.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestObjet {

	public Monde monde;
	public PiedDeBiche objetDeplacable;
	public Objet objetNonDeplacable;


	@Before
	public void avantTest()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		this.monde = new Monde("test");
		this.objetDeplacable = new PiedDeBiche("Pied de biche", this.monde);
		this.objetNonDeplacable = new Objet("Objet non deplacable", this.monde) {};
	}

	@Test
	public void testDeplacable() {

		assertThat(this.objetDeplacable.estDeplacable(), is(true));
		assertThat(this.objetNonDeplacable.estDeplacable(), is(false));
	}


}
