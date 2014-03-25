package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;

import org.junit.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestClef {

	public Monde monde;
	public Serrure serrure;
	public Clef clef;


	@Before
	public void avantTest()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		this.monde = new Monde("test");
		this.serrure = new Serrure("serrure", this.monde);
		this.clef = this.serrure.creerClef();
	}

	@Test
	public void testDeplacable() {

		assertThat(this.clef.estDeplacable(), is(true));
	}
}
