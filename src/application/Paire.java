package application;

import java.io.Serializable;

public class Paire  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5962339855475206339L;
	private Integer x;
	private Integer y;
	
	public Paire(Integer x,Integer y) {
		this.x=x;
		this.y=y;
	}
	
	public Paire() {
		x=-1;
		y=-1;
	}
	
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	public void setCoordonnees(Integer x, Integer y) {
		this.x=x;
		this.y=y;
	}
	
	public boolean equals(Integer x1, Integer y1) {
		return (x1==x && y1==y);
	}
}
