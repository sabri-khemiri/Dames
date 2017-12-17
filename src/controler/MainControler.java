package controler;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Jeu;
import model.Mouvement;
import view.IHMGraphique.Plateau;

public class MainControler {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		//Création d'une partie vierge
		Jeu j = new Jeu();
		
		//Lancement de l'IHM Graphique
		JFrame f = new JFrame();
		f.setSize(600, 600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Plateau p = new Plateau(10, j);
		f.add(p);
		f.setVisible(true);
		
		
		while(!j.isFinPartie()){
			ArrayList<Mouvement> m  = j.getMoves(j.getJoueur1());
			if(m.size() > 0){
				
				Mouvement mouv;
				do{
					mouv = new Mouvement(p.getCase(),p.getCase());
				}				
				while(!j.jouer(mouv));
				p.setPion(j.getJoueur1().getPions(), j.getJoueur2().getPions());
				m  = j.getMoves(j.getJoueur2());
				if(m.size() > 0){		
					do{
						mouv = new Mouvement(p.getCase(),p.getCase());
					}				
					while(!j.jouer(mouv));
					p.setPion(j.getJoueur1().getPions(), j.getJoueur2().getPions());
				}
				else
					break;				
			}else 
				break;

		}
		System.out.println(j.getJeuEtat());
	}
}
