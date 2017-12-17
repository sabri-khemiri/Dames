package model;

public class Mouvement {
	private Pion pion;
	private Pion caseVide;
	
	public Mouvement(Pion pion, Pion caseVide) {
		this.pion = pion;
		this.caseVide = caseVide;
	}
	
	public Mouvement(Mouvement mouvement) {
		this.pion = new Pion(mouvement.getPion());
		this.caseVide = new Pion(mouvement.getCaseVide());
	}
	
	public Pion getPion() {
		return pion;
	}
	
	public void setPion(Pion pion) {
		this.pion = pion;
	}
	
	public Pion getCaseVide() {
		return caseVide;
	}
	
	public void setCaseVide(Pion caseVide) {
		this.caseVide = caseVide;
	}
	
	@Override
	public String toString() {
		return "Mouvement [pion=" + pion + ", caseVide=" + caseVide + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caseVide == null) ? 0 : caseVide.hashCode());
		result = prime * result + ((pion == null) ? 0 : pion.hashCode());
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
		Mouvement other = (Mouvement) obj;
		if (caseVide == null) {
			if (other.caseVide != null)
				return false;
		} else if (!caseVide.equals(other.caseVide))
			return false;
		if (pion == null) {
			if (other.pion != null)
				return false;
		} else if (!pion.equals(other.pion))
			return false;
		return true;
	}
}
