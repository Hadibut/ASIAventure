package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.*;
import org.junit.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class TestEntite {
	public Monde monde;
	public Entite entite;


	@Before
	public void avantTest()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		this.monde = new Monde("test");
		this.entite = new Entite("testEntite", this.monde){};
	}

	@Test
	public void testConstructeur()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		assertThat("testEntite", equalTo(this.entite.getNom()));
		assertThat(this.monde, equalTo(this.entite.getMonde()));
	}

	@Test
	public void testEquals()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		Entite entite2 = new Entite("testEntiteDeux", this.monde){};
		Entite entiteAutreMonde = new Entite("testEntite", new Monde("testMondeDeux")){};

		assertThat(this.entite, equalTo(this.entite));
		assertThat(this.entite, not(equalTo(entite2)));
		assertThat(this.entite, not(equalTo(entiteAutreMonde)));
	}
}
