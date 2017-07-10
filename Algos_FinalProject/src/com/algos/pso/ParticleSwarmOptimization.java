package com.algos.pso;

import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;


public class ParticleSwarmOptimization {
	
	int swarmSize = 30;
	int noOfIterMax = 100;
	double const1 = 2.0;
	double const2 = 2.0;
	private Vector<Particle> swarm = new Vector<Particle>();
	private double[] pBest = new double[swarmSize];
	private Vector<Position> pBestPosition = new Vector<Position>();
	private double gBest;
	private Position gBestPosition;
	private double[] fitnessValList = new double[swarmSize];
	public static final double lowVelocity = -1;
	public static final double highVelocity = 1;
	public static final double posXLow = 1;
	public static final double posXHigh = 4;
	public static final double posYLow = -1;
	public static final double posYHigh = 1;
	double W_UPPERBOUND = 1.0;
	double W_LOWERBOUND = 0.0;
	
	
	public static final double maxError = 1E-20;
	
		Random rand = new Random();
		
	
	public int[] findRankOfTask(int[] input)
	{
		int[] rank = new int[input.length]; 
		TreeMap<Integer,Integer> map = new TreeMap<>();
		for(int i=0;i<input.length;i++) {
		    map.put(input[i],i);
		}
		for(int value : map.values()) {
		    //print ranks in order of array values
		    System.out.println(value);
		    
		}
		return rank;
	}
	
	
	public void execute() {
		
		//  Initialize swarm population
		initializeSwarm();
		
		// update the fitness value of each particle in the swarm
		updateFitnessValueList();
		
		for(int i=0; i<swarmSize; i++) {
			pBest[i] = fitnessValList[i];
			pBestPosition.add(swarm.get(i).getPosition());
		}
		
		int iter = 0;
		double w;
		double error = 9999;
		
		while(iter < noOfIterMax && error > maxError) {
			// step 1 - update pBest
			for(int i=0; i<swarmSize; i++) {
				if(fitnessValList[i] < pBest[i]) {
					pBest[i] = fitnessValList[i];
					pBestPosition.set(i, swarm.get(i).getPosition());
				}
			}
				
			// step 2 - update gBest
			int bestParticleIndex = getMinPos(fitnessValList);
			if(iter == 0 || fitnessValList[bestParticleIndex] < gBest) {
				gBest = fitnessValList[bestParticleIndex];
				gBestPosition = swarm.get(bestParticleIndex).getPosition();
			}
			
			w = W_UPPERBOUND - (((double) iter) / noOfIterMax) * (W_UPPERBOUND - W_LOWERBOUND);
			
			for(int i=0; i<swarmSize; i++) {
				double r1 = rand.nextDouble();
				double r2 = rand.nextDouble();
				
				Particle particle = swarm.get(i);
				
				// step 3 - update velocity
				double[] newVel = new double[2];
				
			// calculate the new velocity 	
				newVel[0] = (w * particle.getVelocity().getPos()[0]) + 
							(r1 * const1) * (pBestPosition.get(i).getLoc()[0] - particle.getPosition().getLoc()[0]) +
							(r2 * const2) * (gBestPosition.getLoc()[0] - particle.getPosition().getLoc()[0]);
				newVel[1] = (w * particle.getVelocity().getPos()[1]) + 
							(r1 * const1) * (pBestPosition.get(i).getLoc()[1] - particle.getPosition().getLoc()[1]) +
							(r2 * const2) * (gBestPosition.getLoc()[1] - particle.getPosition().getLoc()[1]);
				Velocity vel = new Velocity(newVel);
				particle.setVelocity(vel);
				
				// step 4 - update location
				double[] newPosition = new double[2];
				
				// calculate the new position 
				
				newPosition[0] = particle.getPosition().getLoc()[0] + newVel[0];
				newPosition[1] = particle.getPosition().getLoc()[1] + newVel[1];
				Position position = new Position(newPosition);
				particle.setPosition(position);
			}
			
			// minimize the function :::::::::  
			error = evaluate(gBestPosition) - 0; 
			
			
			System.out.println("Iteration value " + iter + ": ");
			System.out.println(" global best value x" + gBestPosition.getLoc()[0]);
			System.out.println(" global best value y " + gBestPosition.getLoc()[1]);
			System.out.println(" global Fitness Function Value " + evaluate(gBestPosition));
			
			// update the number of iterations
			iter++;
			updateFitnessValueList();
		}
		
		System.out.println("\n Solution found after " + (iter - 1) + ", iterations");
		System.out.println("Output is  :: ");
		System.out.println("     Best value for x is : " + gBestPosition.getLoc()[0]);
		System.out.println("     Best value for y is : " + gBestPosition.getLoc()[1]);
	}
	
	public void initializeSwarm() {
		Particle particle;
		for(int i=0; i<swarmSize; i++) {
			particle = new Particle();
			
			// initialize the position of the particle randomly
			double[] loc = new double[2];
			double[] vel = new double[2];
			Position location = null;
			Velocity velocity = null;
			

			loc[0] = posXLow + rand.nextDouble() * (posXHigh - posXLow);
			loc[1] = posYLow + rand.nextDouble() * (posYHigh - posYLow);
			
			location = new Position(loc);
			
			// initlaize velocity of the particle randomly
		
			vel[0] = lowVelocity + rand.nextDouble() * (highVelocity - lowVelocity);
			vel[1] = lowVelocity + rand.nextDouble() * (highVelocity - lowVelocity);
			
			velocity = new Velocity(vel);
			
			particle.setPosition(location);
			particle.setVelocity(velocity);
			swarm.add(particle);
		}
		
		
		
	}
	
	public void updateFitnessValueList() {
		for(int i=0; i<swarmSize; i++) {
			
			// calculate fitness value :
			
			fitnessValList[i] = swarm.get(i).getFitnessValue();
		}
	}
	
	public static double evaluate(Position position) {
		double result = 0;
		double x = position.getLoc()[0]; 
		double y = position.getLoc()[1];  
		double sum = x * x + y * y;
		result =  0.5 + (Math.pow(Math.sin(Math.sqrt(sum)), 2) - 0.5) / Math.pow(1 + 0.001 * sum, 2);; 
				
		
		return result;
	}
	
	public static int getMinPos(double[] list) {
		int pos = 0;
		double minValue = list[0];
		
		for(int i=0; i<list.length; i++) {
			if(list[i] < minValue) {
				pos = i;
				minValue = list[i];
			}
		}
		
		return pos;
	}
	
}
