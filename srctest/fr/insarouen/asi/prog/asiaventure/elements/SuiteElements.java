package fr.insarouen.asi.prog.asiaventure.elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.asi.prog.asiaventure.elements.TestEntite;
import fr.insarouen.asi.prog.asiaventure.elements.objets.SuiteObjets;
import fr.insarouen.asi.prog.asiaventure.elements.structure.SuiteStructure;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.SuiteVivants;

@RunWith(Suite.class)
@SuiteClasses({

	TestEntite.class,
	SuiteObjets.class,
	SuiteStructure.class,
	SuiteVivants.class
})
public class SuiteElements {}