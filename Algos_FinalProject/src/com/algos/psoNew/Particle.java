package com.algos.psoNew;

import java.util.HashMap;
import java.util.Map;

public class Particle {

// best fitness till now 
	double bestParticleFitness; 
	// best position till now
	double bestParticlePosition[];
	// current position , velocity and fitness vlaue
	private Position position;
	private double currFitnessValue;
	private Velocity velocity;
	private int dimension;
	private Map cost;
	
	public Particle()
	{
		super();
	}
	
	public Particle(int dimension, Velocity velocity, Position position ) {
		super();
		this.dimension = dimension;
		this.position = position;
		bestParticlePosition = new double[dimension];
		this.velocity = velocity;
		// initialize randomly
		bestParticleFitness = 5;
		currFitnessValue = 5;
		for (int i = 0; i < position.getPosition().length; i++)
		{
			// randomly initialize 
			bestParticlePosition[i] = Double.NaN;
		}
	}

	public Particle(double fitnessValue, Velocity velocity, Position position) {
		super();
		this.currFitnessValue = fitnessValue;
		this.velocity = velocity;
		this.position = position;
		bestParticlePosition = new double[2];

		bestParticleFitness = Double.NaN;
		fitnessValue = Double.NaN;;
		// Initialize using uniform distribution
		
		for (int i = 0; i < position.getPosition().length; i++)
		{
			// randomly initialize 
			bestParticlePosition[i] = Double.NaN;;
		}
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	
	public double getFitnessValue() {
	//	currFitnessValue = PSO_CloudComputing.evaluate(position.getPosition());
		return currFitnessValue;
	}
	
	public void setFitnessValue(double fitness, boolean minimizeWaitingTime) {
		this.currFitnessValue = fitness;
		if ((minimizeWaitingTime && (fitness > bestParticleFitness)) 
				|| (!minimizeWaitingTime && (fitness < bestParticleFitness)) ) {
			for (int i = 0; i < position.getPosition().length; i++)
			{
				bestParticlePosition[i] = position.getPosition()[i];
			}
			bestParticleFitness = fitness;
		}
	}
	
	

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	public void checkForConstraints(double[] minPosition, double[] maxPosition, double[] minVelocity, double[] maxVelocity) {
	
		if ((minPosition == null) && (maxPosition == null) && (minVelocity == null) && (maxVelocity == null))
		{
			return;
		}
		if ((minPosition != null) && (maxPosition != null) && (minVelocity != null) && (maxVelocity != null)) 
		{	
			for (int i = 0; i < position.getPosition().length; i++) {
				if(minPosition[i] > position.getPosition()[i])
				{
				position.getPosition()[i] =  minPosition[i] ;
				}
				
			    if(maxPosition[i] < position.getPosition()[i]) 
			    	{
			    	position.getPosition()[i] =  maxPosition[i];
			    	}
			    if(minVelocity[i] > velocity.getVelocity()[i])
			    {

				    velocity.getVelocity()[i] = minVelocity[i];
			    }
			    if(maxVelocity[i] < velocity.getVelocity()[i])
			    {
				    velocity.getVelocity()[i] =  maxVelocity[i];
			    }
		}
		}
		else {
		
			if ((minPosition != null) && (maxPosition != null)) 
			{
				for (int i = 0; i < position.getPosition().length; i++) {
					
					if(minPosition[i] > position.getPosition()[i])
					{
					position.getPosition()[i] =  minPosition[i] ;
					}
					
				    if(maxPosition[i] < position.getPosition()[i]) 
				    	{
				    	position.getPosition()[i] =  maxPosition[i];
				    	}
				}
				}
			else {
				
				if (minPosition != null)
				{
					for (int i = 0; i < position.getPosition().length; i++)
					{
						if(minPosition[i] > position.getPosition()[i])
						{
						position.getPosition()[i] =  minPosition[i] ;
						}
						
					    if(maxPosition[i] < position.getPosition()[i]) 
					    	{
					    	position.getPosition()[i] =  maxPosition[i];
					    	}
					}
					
					}
				}

			
			if ((minVelocity != null) && (maxVelocity != null))
				{
				for (int i = 0; i < velocity.getVelocity().length; i++) {
					 if(minVelocity[i] > velocity.getVelocity()[i])
					    {

						    velocity.getVelocity()[i] = minVelocity[i];
					    }
					    if(maxVelocity[i] < velocity.getVelocity()[i])
					    {
						    velocity.getVelocity()[i] =  maxVelocity[i];
					    }
				}
				}
			else {
				
				if (minVelocity != null)
					for (int i = 0; i < velocity.getVelocity().length; i++)
					{
						if(minVelocity[i] > velocity.getVelocity()[i])
					    {
						velocity.getVelocity()[i] = minVelocity[i];
					    }	
					}
				if (maxVelocity != null) 
					{
					for (int i = 0; i < velocity.getVelocity().length; i++)
					{
						 if(maxVelocity[i] < velocity.getVelocity()[i])
						    {
							    velocity.getVelocity()[i] =  maxVelocity[i];
						    }	
					}
					}
			}
		}
	}
	
	
	public void init(double maxPosition[], double minPosition[], double maxVelocity[], double minVelocity[]) {
		for (int i = 0; i < position.getPosition().length; i++) {
			
		// Initialize
			position.getPosition()[i] = (maxPosition[i] - minPosition[i]) * Math.random() + minPosition[i];
			velocity.getVelocity()[i] = (maxVelocity[i] - minVelocity[i]) * Math.random() + minVelocity[i];

			bestParticlePosition[i] = Double.NaN;
		}
	}
	
}
