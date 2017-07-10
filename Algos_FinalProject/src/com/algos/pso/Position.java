package com.algos.pso;

public class Position {
	
	private double[] loc;

	public Position(double[] loc) {
		super();
		this.loc = loc;
	}

	public double[] getLoc() {
		return loc;
	}

	public void setLoc(double[] loc) {
		this.loc = loc;
	}
}
