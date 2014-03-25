package fr.insarouen.asi.prog.asiaventure;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.asi.prog.asiaventure.elements.SuiteElements;
import fr.insarouen.asi.prog.asiaventure.TestMonde;

@RunWith(Suite.class)
@SuiteClasses({

	TestMonde.class,
	SuiteElements.class
})
public class SuiteASIAventure {}