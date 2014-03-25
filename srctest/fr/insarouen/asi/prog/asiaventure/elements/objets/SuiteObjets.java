package fr.insarouen.asi.prog.asiaventure.elements.objets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.SuiteSerrurerie;
import fr.insarouen.asi.prog.asiaventure.elements.objets.TestObjet;

@RunWith(Suite.class)
@SuiteClasses({

	SuiteSerrurerie.class,
	TestObjet.class
})
public class SuiteObjets {}