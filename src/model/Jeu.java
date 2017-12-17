package model;

import java.util.ArrayList;

import model.Joueur.JoueurEtat;
import model.Pion.PionEtat;

public class Jeu {
	public static enum JeuEtat {
		EN_COURS, 
		JOUEUR1_WIN, 
		JOUEUR2_WIN;
	}

	private final static int TAILLE_DAMIER_DEFAUT = 10;
	private final static int NOMBRE_PION_DEFAUT = 20;

	private Joueur joueur1;
	private Joueur joueur2;
	private JoueurEtat joueurCourrant;
	private int tailleDamier;
	private JeuEtat jeuEtat;

	public Jeu(int tailleDamier, int nombrePion) {
		this.joueur1 = new Joueur(tailleDamier, nombrePion, JoueurEtat.JOUEUR1);
		this.joueur2 = new Joueur(tailleDamier, nombrePion, JoueurEtat.JOUEUR2);
		this.joueurCourrant = JoueurEtat.JOUEUR1;
		this.tailleDamier = tailleDamier;
		this.jeuEtat = JeuEtat.EN_COURS;
	}

	public Jeu() {
		this(TAILLE_DAMIER_DEFAUT, NOMBRE_PION_DEFAUT);
	}

	public Jeu(Jeu j) {
		this.joueur1 = new Joueur(j.getJoueur1());
		this.joueur2 = new Joueur(j.getJoueur2());
		this.joueurCourrant = j.joueurCourrant;
		this.tailleDamier = j.tailleDamier;
		this.jeuEtat = JeuEtat.EN_COURS;
	}

	private void setMortPion(Joueur j, Pion p) {
		j.mortPion(p);
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public JoueurEtat getJoueurCourrant() {
		return joueurCourrant;
	}

	public Joueur getJoueurCourrantEntity() {
		return (joueurCourrant == JoueurEtat.JOUEUR1) ? joueur1 : joueur2;
	}

	private void changeJoueurCourrant() {
		joueurCourrant = (joueurCourrant == JoueurEtat.JOUEUR1) ? JoueurEtat.JOUEUR2 : JoueurEtat.JOUEUR1;
	}

	public boolean isFinPartie() {
		return this.jeuEtat == JeuEtat.EN_COURS ? false : true;
	}

	public JeuEtat getJeuEtat() {
		return jeuEtat;
	}

	public void setJeuEtat(JeuEtat jeuEtat) {
		this.jeuEtat = jeuEtat;
	}

	public boolean jouer(Mouvement m) {
		Joueur jnc = joueurCourrant == JoueurEtat.JOUEUR1 ? this.joueur2 : this.joueur1; // Joueur non courrant

		if (estPossible(m)) {
			int nombreCase = Math.abs(m.getPion().getPosX() - m.getCaseVide().getPosX());
			int pasX = (m.getCaseVide().getPosX() - m.getPion().getPosX() > 0) ? 1 : -1;
			int pasY = (m.getCaseVide().getPosY() - m.getPion().getPosY() > 0) ? 1 : -1;
			for (int i = 1; i < nombreCase; i++) {
				if (jnc.aPion(m.getPion().getPosX() + pasX * i, m.getPion().getPosY() + pasY * i) != null) {
					setMortPion(jnc, jnc.aPion(m.getPion().getPosX() + pasX * i, m.getPion().getPosY() + pasY * i));
				}
			}

			this.getJoueurCourrantEntity().jouer(m);

			if (getMoves(jnc).size() == 0) {
				this.jeuEtat = this.getJoueurCourrant() == JoueurEtat.JOUEUR1 ? JeuEtat.JOUEUR1_WIN
						: JeuEtat.JOUEUR2_WIN;
			}
			if (getMoves(getJoueurCourrantEntity()).size() == 0) {
				this.jeuEtat = this.getJoueurCourrant() == JoueurEtat.JOUEUR1 ? JeuEtat.JOUEUR2_WIN
						: JeuEtat.JOUEUR2_WIN;
			}
			this.changeJoueurCourrant();
			return true;
		} else {
			if (getMoves(getJoueurCourrantEntity()).size() == 0) {
				this.jeuEtat = this.getJoueurCourrant() == JoueurEtat.JOUEUR1 ? JeuEtat.JOUEUR2_WIN
						: JeuEtat.JOUEUR2_WIN;
			}
			if (getMoves(jnc).size() == 0) {
				this.jeuEtat = this.getJoueurCourrant() == JoueurEtat.JOUEUR1 ? JeuEtat.JOUEUR1_WIN
						: JeuEtat.JOUEUR2_WIN;
			}

			return false;
		}
	}

	public boolean estPossible(Mouvement m) {
		Joueur jnc = joueurCourrant == JoueurEtat.JOUEUR1 ? this.joueur2 : this.joueur1; // Joueur non courrant

		if (m.getCaseVide().getPosX() < 0 || // si on est hors taille de damier
				m.getCaseVide().getPosY() < 0 || m.getCaseVide().getPosX() >= this.tailleDamier || // si on est hors
																									// taille de damier
				m.getCaseVide().getPosY() >= this.tailleDamier || // si on est hors taille de damier
				this.joueur1.aPion(m.getCaseVide().getPosX(), m.getCaseVide().getPosY()) != null || // si la case
																									// sélectionné n'est
																									// pas vide
				this.joueur2.aPion(m.getCaseVide().getPosX(), m.getCaseVide().getPosY()) != null) // si la case
																									// sélectionné n'est
																									// pas vide
			return false;

		int nombreCase = Math.abs(m.getPion().getPosX() - m.getCaseVide().getPosX());
		if ((nombreCase - Math.abs(m.getPion().getPosY() - m.getCaseVide().getPosY()) != 0))
			return false;

		if (nombreCase != 1) {
			boolean erreur = false;
			int pasX = (m.getCaseVide().getPosX() - m.getPion().getPosX() > 0) ? 1 : -1;
			int pasY = (m.getCaseVide().getPosY() - m.getPion().getPosY() > 0) ? 1 : -1;

			if (m.getPion().getEtat() == PionEtat.PION) {
				for (int i = 1; i < nombreCase; i++)
					if (jnc.aPion(m.getPion().getPosX() + pasX * i, m.getPion().getPosY() + pasY * i) == null)
						erreur = true;
				if (erreur)
					return false;
			}
		} else if (m.getPion().getEtat() == PionEtat.PION) {
			if ((this.joueurCourrant == JoueurEtat.JOUEUR1 && (m.getPion().getPosY() - m.getCaseVide().getPosY()) > 0)
					|| // si sens du mouvement est bon
					(this.joueurCourrant == JoueurEtat.JOUEUR2
							&& (m.getPion().getPosY() - m.getCaseVide().getPosY()) < 0)) { // si sens du mouvement est
																							// bon
				return false;
			}
		}

		return true;
	}

	public ArrayList<Mouvement> getMoves(Joueur j) {
		ArrayList<Mouvement> returnAPions = new ArrayList<Mouvement>();
		ArrayList<Pion> tmpAPions;

		for (Pion pionDuJoueur : j.getPions()) {
			tmpAPions = new ArrayList<Pion>();
			for (int i = 0; i < tailleDamier; i++) {
				tmpAPions.add(new Pion(pionDuJoueur.getPosX() + i, pionDuJoueur.getPosY() + i)); // HAUT GAUCHE
				tmpAPions.add(new Pion(pionDuJoueur.getPosX() - i, pionDuJoueur.getPosY() + i)); // HAUT DROITE*
				tmpAPions.add(new Pion(pionDuJoueur.getPosX() + i, pionDuJoueur.getPosY() - i)); // BAS GAUCHE
				tmpAPions.add(new Pion(pionDuJoueur.getPosX() - i, pionDuJoueur.getPosY() - i)); // BAS DROITE*
			}
			for (Pion pion : tmpAPions) {
				if (estPossible(new Mouvement(pionDuJoueur, pion))) {
					returnAPions.add(new Mouvement(pionDuJoueur, pion));
				}
			}
		}
		return returnAPions;
	}

	public static JeuEtat simulation(Jeu jeux) {
		Jeu j = new Jeu(jeux);
		ArrayList<Mouvement> m;
		while (!j.isFinPartie()) {
			m = j.getMoves(j.getJoueurCourrantEntity());
			if (m.size() == 0) {
				j.jeuEtat = j.getJoueurCourrant() == JoueurEtat.JOUEUR1 ? JeuEtat.JOUEUR2_WIN : JeuEtat.JOUEUR1_WIN;
				return j.jeuEtat;
			}
			j.jouer(m.get((int) (Math.random() * (m.size()))));
		}
		return j.jeuEtat;
	}
}
