package view.IHMGraphique;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class Pion extends JPanel{
	private boolean couleur;

	public Pion(boolean couleur){
		setOpaque(false);

		setLayout(new GridLayout(1,0));
		this.couleur = couleur;
		setCouleur();
	}

	private void setCouleur(){
		if(this.couleur){
			setBackground(Color.BLUE);
		}else{
			setBackground(Color.PINK);
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		Paint paint;
		Graphics2D g2d;
		if (g instanceof Graphics2D) {
			g2d = (Graphics2D) g;
		}
		else {
			System.out.println("Error");
			return;
		}
		paint = new GradientPaint(0,0, getBackground(), getWidth(), getHeight(), getForeground());
		g2d.setPaint(paint);
		g.fillOval((int)(getWidth()*0.1), (int)(getHeight()*0.1), (int)(getWidth()*0.8), (int)(getHeight()*0.8));
	}

	
}
