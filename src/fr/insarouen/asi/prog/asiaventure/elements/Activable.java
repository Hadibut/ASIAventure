package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;

public interface Activable {


	public boolean activableAvec(Objet obj);
	public void activer()
	throws ActivationImpossibleException;
	public Etat getEtat();
}