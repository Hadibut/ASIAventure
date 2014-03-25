package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;

import org.junit.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestSerrure {

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

		assertThat(this.serrure.estDeplacable(), is(false));
	}

	@Test
	public void testCreerClef()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		assertThat(this.clef, instanceOf(Clef.class));

		Clef clefNull = this.serrure.creerClef();
		assertThat(this.clef, is(nullValue()));
	}

	@Test(expected=ActivationImpossibleException.class)
	public void testActiverSansObjet()
	throws ActivationImpossibleException {

		this.serrure.activer();
	}

	@Test
	public void testActivableAvecObjet()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		assertThat(this.serrure.activableAvec(this.clef), is(true));

		PiedDeBiche piedDeBiche = new PiedDeBiche("Pied de biche", this.monde);
		assertThat(this.serrure.activableAvec(piedDeBiche), is(true));

		Serrure autreSerrure = new Serrure("Deuxième serrure", this.monde);
		Clef autreClef = autreSerrure.creerClef();
		assertThat(this.serrure.activableAvec(autreClef), is(false));
	}

	@Test
	public void testEtatSerrure()
	throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException{

		PiedDeBiche piedDeBiche = new PiedDeBiche("Pied de biche", this.monde);

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
