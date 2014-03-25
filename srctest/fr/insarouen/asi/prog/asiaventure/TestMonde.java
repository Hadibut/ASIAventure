package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import org.junit.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestMonde {
	public Monde monde;

	@Before
	public void avantTest() {

		this.monde = new Monde("test");
	} 

	@Test
	public void testAjouter()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		Entite entite = new Entite("testEntite", this.monde){};
		assertThat(entite, equalTo(this.monde.getEntite("testEntite")));
	}

	@Test(expected=NomDEntiteDejaUtiliseDansLeMondeException.class)
	public void testDoubleAjouter()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		Entite entite = new Entite("testEntite", this.monde){};
		Entite entite2 = new Entite("testEntite", this.monde){};
	}
}