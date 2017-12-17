package model;

import java.util.ArrayList;

import model.Pion.PionEtat;

public class Joueur {
	 public static enum JoueurEtat{
		 JOUEUR1, 
		 JOUEUR2;
	 }
	
	 private ArrayList<Pion> pions;
	 private JoueurEtat etat;
	 private int tailleDamier;
	 
	 public Joueur(int tailleDamier, int nombrePion, JoueurEtat etat){
		 this.pions = new ArrayList<Pion>();
		 this.etat = etat;
		 this.tailleDamier = tailleDamier;
		 
		// Position en X et Y temporaire pour placer les piéces
		 int x = 0; 
		 int y = this.etat == JoueurEtat.JOUEUR1 ? 0 :  tailleDamier / 2 + 1 ; // On laisse deux case de libre sur le damier
		 
		 //On place les pions sur le damier
		 for(int i = 0; i < nombrePion; i++){
			 pions.add(new Pion(x, y));			 
			 if(x + 2 >= tailleDamier){
				 x = (x%2 == 0)? 1:0;
				 y++;
			 }
			 else
				 x += 2;
		 }
	 }
	 
	 public Joueur(Joueur j){
		 this.pions = new ArrayList<Pion>();
		 this.etat = j.getEtat();
		 this.tailleDamier = j.getTailleDamier();

		 for (Pion pion : j.getPions()) {
			this.pions.add(new Pion(pion));
		}
	 }
	 

	 
	public JoueurEtat getEtat() {
		return etat;
	}
	
	public final ArrayList<Pion> getPions(){
		return pions; 
	}

	public final Pion aPion(int x, int y) {
		Pion p = new Pion(x,y);
		if(pions.contains(p))
			return pions.get(pions.indexOf(p));
		return null;
	}
	
	public final Pion aPion(Pion p) {
		if(pions.contains(p))
			return pions.get(pions.indexOf(p));
		return null;
	}

	public void mortPion(Pion p) {
		pions.remove(p);
	}
	
	public void jouer(Mouvement m){
		Pion p;
		if(pions.contains(m.getPion())){
			p = pions.get(pions.indexOf(m.getPion()));
			p.setPosition(m.getCaseVide().getPosX(), m.getCaseVide().getPosY());
			
			if(this.etat == JoueurEtat.JOUEUR1 && p.getPosY() == this.tailleDamier-1 || this.etat == JoueurEtat.JOUEUR2 && p.getPosY() == 0){
				p.setEtat(PionEtat.DAME);
			}
		}
	}
	
	public int getTailleDamier() {
		return tailleDamier;
	}

	public void setTailleDamier(int tailleDamier) {
		this.tailleDamier = tailleDamier;
	}

	@Override
	public String toString() {
		return etat + " : " + pions.toString();
	}
}
