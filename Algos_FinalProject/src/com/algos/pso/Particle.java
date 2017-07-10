package com.algos.pso;

public class Particle {

	
	private Position position;
	private double fitnessValue;
	private Velocity velocity;
	
	public Particle() {
		super();
	}

	public Particle(double fitnessValue, Velocity velocity, Position position) {
		super();
		this.fitnessValue = fitnessValue;
		this.velocity = velocity;
		this.position = position;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	
	public double getFitnessValue() {
		fitnessValue = ParticleSwarmOptimization.evaluate(position);
		return fitnessValue;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	
}
