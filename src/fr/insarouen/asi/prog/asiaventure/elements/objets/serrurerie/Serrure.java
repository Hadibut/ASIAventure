package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.*;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;

public class Serrure extends Objet implements Activable {
	private Etat etat;
	private Clef clef;

	/**
	 * Crée une serrure
	 * @param Monde monde
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une entité du même nom est déjà présente dans ce monde
	 */
	public Serrure(Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {
		
		this(Serrure.genererNomSerrure(monde), monde);
	}

	/**
	 * Crée une serrure
	 * @param String nom
	 * @param Monde monde
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une entité du même nom est déjà présente dans ce monde
	 */
	public Serrure(String nom, Monde monde)
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		super(nom, monde);
		this.etat = Etat.VERROUILLE;
	}

	/**
	 * Génère un nom unique dans le monde pour une serrure
	 * @param Monde monde
	 * @return String nom de la serrure
	 */
	private static String genererNomSerrure(Monde monde) {

		int numeroSerrure = (int)(Math.random() * (Serrure.MAXIMUMNOMALEATOIRE));
		String nomSerrure = "Serrure" + numeroSerrure;

		while (monde.getEntite(nomSerrure) != null) {

			numeroSerrure = (int)(Math.random() * (Serrure.MAXIMUMNOMALEATOIRE));
			nomSerrure = new String("Serrure" + numeroSerrure);
		}

		return nomSerrure;
	}

	/**
	 * Crée la clef de la serrure si cette dernière n'a pas encore été récupérée
	 * @return Clef la clé de la serrure ou null
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException si une clé du même nom est déjà présente dans ce monde
	 */
	public Clef creerClef()
	throws NomDEntiteDejaUtiliseDansLeMondeException {

		if (this.clef != null)
			return null;

		int numeroClef = (int)(Math.random() * (this.MAXIMUMNOMALEATOIRE));
		String nomClef = new String("Clef" + numeroClef);

		while (this.getMonde().getEntite(nomClef) != null) {

			numeroClef = (int)(Math.random() * (this.MAXIMUMNOMALEATOIRE));
			nomClef = new String("Clef" + numeroClef);
		}

		this.clef = new Clef(nomClef, this.getMonde());
		return this.clef;
	}

	/**
	 * Une serrure n'est pas déplacable
	 * @return Boolean
	 */
	public boolean estDeplacable() {

		return false;
	}

	/**
	 * Active une serrure sans objet
	 * @throws ActivationImpossibleException Une serrure ne peut pas être activé sans objet. Levée à chaque fois.
	 */
	public void activer()
	throws ActivationImpossibleException {

		throw new ActivationImpossibleException("Une serrure ne peut pas être activé sans objet.");
	}

	/**
	 * Est-ce que l'objet permet d'activer cette serrure ?
	 * @param Objet objet
	 * @return boolean true si l'objet est un PiedDeBiche ou si c'est la bonne clé
	 */
	public boolean activableAvec(Objet objet) {

		return (objet instanceof PiedDeBiche || objet.equals(this.clef));
	}

	/**
	 * Active la serrure avec l'objet
	 * @param Objet objet
	 * @throws ActivationImpossibleException si l'objet ne permet pas d'activer cette serrure
	 */
	public void activerAvec(Objet objet)
	throws ActivationImpossibleException {

		if (!this.activableAvec(objet))
			throw new ActivationImpossibleException("L'objet " + objet.getNom() + " ne permet pas d'ouvrir la serrure " + this.getNom() + ".");

		if (this.etat != Etat.CASSE) {

			if (objet instanceof PiedDeBiche)
				this.etat = Etat.CASSE;

			if (objet.equals(this.clef)) {

				if (this.etat == Etat.VERROUILLE)
					this.etat = Etat.DEVERROUILLE;
				else
					this.etat = Etat.VERROUILLE;
			}
		}
	}

	/**
	 * Récupère l'état de la serrure
	 * @return Etat État de la serrure : CASSE, VERROUILLE ou DEVERROUILLE
	 */
	public Etat getEtat() {

		return this.etat;
	}
}