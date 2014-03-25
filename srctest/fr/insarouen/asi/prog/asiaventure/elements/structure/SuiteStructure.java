package fr.insarouen.asi.prog.asiaventure.elements.structure;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.asi.prog.asiaventure.elements.structure.TestPiece;

@RunWith(Suite.class)
@SuiteClasses({

	TestPiece.class,
	TestPorte.class
})
public class SuiteStructure {}