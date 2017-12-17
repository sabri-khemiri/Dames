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


public class Case extends JPanel implements MouseListener{
	private int posX,posY;
	private boolean selectionnee;
	private boolean couleur;
	private Plateau p;

	public Case(boolean couleur ,int x, int y, Plateau p){
		this.posX = x;
		this.posY = y;
		setLayout(new GridLayout(1,0));
		this.couleur = couleur;
		setCouleur();
		this.p = p;
	}

	public boolean isSelectionnee() {
		return selectionnee;
	}

	public void setSelectionnee(boolean selectionnee) {
		this.selectionnee = selectionnee;
		if(selectionnee){
			setForeground(Color.PINK);
		}
		else {
			setCouleur();
		}
	}
	private void setCouleur(){
		if(this.couleur){
			setBackground(Color.WHITE);
			setForeground(new Color(200, 200, 200));
		}else{
			setBackground(Color.GRAY);
			setForeground(new Color(20, 20, 20));
		}
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
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
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		setSelectionnee(true);
		p.setCase(new model.Pion(posX,posY));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setSelectionnee(false);
	}
}
