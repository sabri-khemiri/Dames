package view.IHMGraphique;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Jeu;
import model.Pion;

public class Plateau extends JPanel {
	private  int tailledamier;
	private ArrayList<Case> cases;
	private Pion p = new Pion();
	private int pi = 0;

	
	public Plateau(int tailledamier, Jeu jeu){
		this.cases = new ArrayList<>();

		this.tailledamier = tailledamier;
		setLayout(new GridLayout(tailledamier, tailledamier));

		this.setBackground(Color.BLUE);
		for(int i = 0; i < tailledamier; i++){
			boolean couleur = i%2 == 0?false: true;

			for(int j = 0; j < tailledamier; j++){
				Case c = new Case(couleur, j, i, this);
				c.addMouseListener(c);
				this.add(c);
				this.cases.add(c);
				couleur =!couleur;

				repaint();
			}

		}
		setPion(jeu.getJoueur1().getPions(), jeu.getJoueur2().getPions());

	}
	
	public void setPion(final ArrayList<Pion> p1, final ArrayList<Pion> p2){		
		for (Case c : cases) {c.removeAll();}
		for (Pion p : p1) {
			for (Case c : cases) {
				if(c.getPosX() == p.getPosX() && c.getPosY() == p.getPosY()){
					view.IHMGraphique.Pion tmp = new view.IHMGraphique.Pion(false);
					c.add(tmp); 
					c.revalidate();
					c.repaint();
				}
			}
		}
		for (Pion p : p2) {
			for (Case c : cases) {
				if(c.getPosX() == p.getPosX() && c.getPosY() == p.getPosY()){
					view.IHMGraphique.Pion tmp = new view.IHMGraphique.Pion(true);
					c.add(tmp); 
					c.revalidate();
					c.repaint();
				}
			}
		}

		this.revalidate();
		this.repaint();
	}
	
	public void setCase(Pion p){
		this.p = p;	
		this.pi++;
	}
	
	public void deselect(){
		for (Case case1 : cases) {
			case1.setSelectionnee(false);
		}
	}
	
	public Pion getCase(){
		int i = this.pi;
		while(this.pi == i){try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}};
		return this.p;
		
	}
}
