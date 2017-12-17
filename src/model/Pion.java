package model;

public class Pion {
	public static enum PionEtat{
		PION,
		DAME;
	}
	
	private PionEtat etat;
	private int posX;
	private int posY;
	
	public Pion() {
		this.etat = PionEtat.PION;
	}
	
	public Pion(int x, int y){
		this();
		this.posX = x;
		this.posY = y;
	}
	
	public Pion(int x, int y, PionEtat e){
		this(x,y);
		this.etat = e;
	}
	
	public Pion(Pion p){
		this();
		this.posX = p.getPosX();
		this.posY = p.getPosY();
	}

	public PionEtat getEtat() {
		return etat;
	}

	public void setEtat(PionEtat etat) {
		this.etat = etat;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public void setPosition(int x, int y){
		this.posX = x;
		this.posY = y;
	}

	@Override
	public String toString() {
		return "Pion : (Etat=" + etat + ", Position=[" + posX + "," + posY + "])";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pion other = (Pion) obj;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}
}
