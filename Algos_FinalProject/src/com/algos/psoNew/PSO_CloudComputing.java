package com.algos.psoNew;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;


public class PSO_CloudComputing {
	
	// default values are set 
	private int swarmSize = 30;
	private int noOfIterMax = 100;
	private double inertia = 0.78;
	
	// constant for calculating new velocity
	// default values are set for constants
	private double const1 = 0.9; // for pbest
	private double const2 = 0.9; // for gbest
	private static int totalNoOfIterations = 0;
	private int gbestInd = -1;
	
	private Vector<Particle> swarm = new Vector<Particle>();
	private double[] pBest = new double[swarmSize];
	private Vector<Position> pBestPosition = new Vector<Position>();

	
	
	private double gBest;
	private double gBestPosition[];
	private double[] fitnessValList = new double[swarmSize];
	
	// set default values 
	public static final double lowVelocity = -1;
	public static final double highVelocity = 1;
	public static final double posXLow = 1;
	public static final double posXHigh = 4;
	public static final double posYLow = -1;
	public static final double posYHigh = 1;
	
	
	private double maxPosition[];
	private double maxVelocity[];
	private double minPosition[];
	private double minVelocity[];
	
	
	private Particle particle;
	private Particle particlesArr[];
	private ArrayList<Particle> particleList;
	
	
	Random rand = new Random();
	
	public PSO_CloudComputing(int numberOfParticles) {

		this.swarmSize = numberOfParticles;
		
	}
	
	public void execute(int dimension)
	{
		Particle particle = createSampleParticle(dimension);
		// intialize swarm
		initializeSwarm(30, particle, 100);
		if(particlesArr == null)
		{
			init();
		}
		
		// evaulate fitness function val
		calculateParticlesFitnessValue(dimension);
		//update the fitness value
		for (int i = 0; i < particlesArr.length; i++) {
		// update velocity and position 
			updateFitnessValue(particlesArr[i]);
		// check for constraints velcoity and position  
			
		particlesArr[i].checkForConstraints(minPosition, maxPosition, minVelocity, maxVelocity);
		}
		
		
		System.out.print(" global best value x" + gBestPosition[0]);
		System.out.println(" global best value y " + gBestPosition[1]);
		System.out.println(" global Fitness Function Value " + evaluate(gBestPosition));
		
		}
	
	public double[] findRankOfTask(double[] position)
	{
		double[] rank = new double[position.length]; 
		TreeMap<Double,Integer> map = new TreeMap<>();
		for(int i=0;i<position.length;i++) {
		    map.put(position[i],i);
		}
		for(int value : map.values()) {
		    //print ranks in order of array values
		    System.out.println(value);
		    
		}
		return rank;
	}
	
	public void initializeSwarm(int noOfParticles, Particle particle, int maxNoOfIterations) {
		
		if(noOfParticles <=1)
		{
			System.out.println("No of particles must be greater than 1");
		}
		else
		{
		this.particle = particle;
		this.swarmSize = noOfParticles;
		this.noOfIterMax = maxNoOfIterations;
		}
		
	}
	
	public Particle createSampleParticle(int dim)
	{
		Particle particle;
		
		// initialize the position of the particle randomly
		double[] pos = new double[2];
		double[] vel = new double[2];
		Position position = null;
		Velocity velocity = null;
		for(int i=0; i< pos.length;i++)
		{
			// initlaize position of the particle randomly	
		pos[i] = posXLow + rand.nextDouble() * (posXHigh - posXLow);
		
		// initlaize velocity of the particle randomly
		vel[i] = lowVelocity + rand.nextDouble() * (highVelocity - lowVelocity);
		}
		position = new Position(pos);
		velocity = new Velocity(vel);
		
		particle = new Particle(dim, velocity, position);
		
		return particle;
	}
	
	public void init() {
		// Init particles
		particlesArr = new Particle[swarmSize];

		// Check constraints (they will be used to initialize particles)
		if (maxPosition == null) 
				return;
		if (minPosition == null) 
			return;
		if (maxVelocity == null) {
			// Default maxVelocity[]
			int dim = 2;
			maxVelocity = new double[dim];
			for (int i = 0; i < dim; i++)
				maxVelocity[i] = (maxPosition[i] - minPosition[i]) / 2.0;
		}
		if (minVelocity == null) {
			// Default minVelocity[]
			int dim = 2;
			minVelocity = new double[dim];
			for (int i = 0; i < dim; i++)
				minVelocity[i] = -maxVelocity[i];
		}
		
		// Init each particle
		for (int i = 0; i < swarmSize; i++) {
			particlesArr[i] = createSampleParticle(2); 
			particlesArr[i].init(maxPosition, minPosition, maxVelocity, minVelocity); // Initialize it
		}


	}
	
	
	public Iterator<Particle> iterator() {
		if (particleList == null) {
			particleList = new ArrayList<Particle>(particlesArr.length);
			for (int i = 0; i < particlesArr.length; i++)
				particleList.add(particlesArr[i]);
		}

		return particleList.iterator();
	}

	
	public void calculateParticlesFitnessValue(int dimension) {

	// if particles are not created
		if (particlesArr == null)
		{
			System.out.println("Particles are not created before :::::::: "+particlesArr);
			return; 
		}
		
		for (int i = 0; i < particlesArr.length; i++) {
			
	//		System.out.println("Particles position and velocity : Position" + particlesArr[i].getPosition().getPosition());
	//		System.out.println("Particles position and velocity : Velocity" + particlesArr[i].getVelocity().getVelocity());
			
			double pbest = evaluateParticleFitnessFunc(particlesArr[i]);
			
			 // Update counter
			totalNoOfIterations ++;
			// Update global best position
			if (gBest < pbest) {
				gBest = pbest; 
				
				gbestInd = i;
				if (gBestPosition == null)
					{
					gBestPosition = new double[dimension];
					}
				double position[] = particlesArr[gbestInd].getPosition().getPosition();
				for (int j = 0; j < position.length; j++)
				{
					gBestPosition[j] = position[j];
			//		System.out.println("gBestPosition[j] :; "+gBestPosition[j]);
				}
			}

		}
		}
	
	
	public void updateFitnessValue(Particle particle) {
		double position[] = particle.getPosition().getPosition();
		double velocity[] = particle.getVelocity().getVelocity();
		double pBestPosition[] = particle.bestParticlePosition;
		
		//	if(totalNoOfIterations < noOfIterMax)
		//	{
				// Update velocity and position
				for (int i = 0; i < position.length; i++) {
					// Update position
					position[i] = position[i] + velocity[i];
		
					// Update velocity
					velocity[i] = inertia * velocity[i] // Inertia
							// Local best -- explore near local best
							+ Math.random() * const1 * (pBestPosition[i] - position[i]) 
							// Global best -- converge towards global min
							+ Math.random() * const2 * (gBestPosition[i] - position[i]); 
				}
		//	}
		
	}
	
	
	public double evaluate(double position[])
	{
		double result = 0;
		int count = 0;
		for(int i=0; i<position.length ; i++)
		{
		double x = position[i]; 
		
		result += (x * (position.length-i)); 
	//	System.out.println(result);	
		
		while(result < -100)
		{
			result = result % 10;
			count++;
	//		System.out.println("count < :" + count);
		}
		count = 0;
		while(result > 100)
		{
			result = result % 10;
			count++;
	//		System.out.println("count > :" + count);
		}
		
		}
		
		totalNoOfIterations ++;
		
		return result;
	}
	
	public double evaluateParticleFitnessFunc(Particle particle) {
		double position[] = particle.getPosition().getPosition();
		double fitnessVal = evaluate(position);
		particle.setFitnessValue(fitnessVal, true);
		return fitnessVal;
	}
	
	
	
public double graphics_f = 10.0;
	
	public void displayPointsJFrame(Graphics graphics, Color foreground, int width, int height, int dim0, int dim1, boolean velocity) {
		graphics.setColor(foreground);

		if (particlesArr != null) {
			double posWidth = width / (maxPosition[dim0] - minPosition[dim0]);
			double posHght = height / (maxPosition[dim1] - minPosition[dim1]);
			double minPosWidth = minPosition[dim0];
			double minPosHght = minPosition[dim1];

			double scaleVelW = width / (graphics_f * (maxVelocity[dim0] - minVelocity[dim0]));
			double scaleVelH = height / (graphics_f * (maxVelocity[dim1] - minVelocity[dim1]));
			double minVelW = minVelocity[dim0] + (maxVelocity[dim0] - minVelocity[dim0]) / 2;
			double minVelH = minVelocity[dim1] + (maxVelocity[dim1] - minVelocity[dim1]) / 2;
			
			
				for (int i = 0; i < particlesArr.length; i++) {
				int velx, vely, x, y;
				
				double pos[] = particlesArr[i].getPosition().getPosition();
				double vel[] = particlesArr[i].getVelocity().getVelocity();
				
		//		System.out.println("position and velocity : position" + pos.length);
		//		System.out.println("position and velocity : velocity" + vel.length);
				x = (int) (posWidth * (pos[dim0] - minPosWidth));
				y = height - (int) (posHght * (pos[dim1] - minPosHght));
		//		System.out.println(" x :  " + x + "y : "+ y);
				
				graphics.drawOval(x, y, 9, 9);
				graphics.fillOval(x, y, 9, 9);
				graphics.setColor(Color.MAGENTA);
				//(x - 1, y - 1, 3, 3);
				
				// display velocity if true which will draw line 
				if (velocity) {
					
					velx = (int) (scaleVelW * (vel[dim0] - minVelW));
					vely = (int) (scaleVelH * (vel[dim1] - minVelH));
					graphics.drawLine(x, y, x + velx, y + vely);
				}
			}
		}
	}

	public int getSwarmSize() {
		return swarmSize;
	}

	public void setSwarmSize(int swarmSize) {
		this.swarmSize = swarmSize;
	}

	public double getInertia() {
		return inertia;
	}

	public void setInertia(double inertia) {
		this.inertia = inertia;
	}

	public double[] getpBest() {
		return pBest;
	}

	public void setpBest(double[] pBest) {
		this.pBest = pBest;
	}

	public double getgBest() {
		return gBest;
	}

	public void setgBest(double gBest) {
		this.gBest = gBest;
	}

	public Particle getParticle() {
		return particle;
	}

	public void setParticle(Particle particle) {
		this.particle = particle;
	}

	public int getNoOfIterMax() {
		return noOfIterMax;
	}

	public void setNoOfIterMax(int noOfIterMax) {
		this.noOfIterMax = noOfIterMax;
	}

	public double getConst1() {
		return const1;
	}

	public void setConst1(double const1) {
		this.const1 = const1;
	}

	public double getConst2() {
		return const2;
	}

	public void setConst2(double const2) {
		this.const2 = const2;
	}

	public double[] getMaxPosition() {
		return maxPosition;
	}

	public void setMaxPosition(double[] maxPosition) {
		this.maxPosition = maxPosition;
	}

	public double[] getMaxVelocity() {
		return maxVelocity;
	}

	public void setMaxVelocity(double[] maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	public double[] getMinPosition() {
		return minPosition;
	}

	public void setMinPosition(double[] minPosition) {
		this.minPosition = minPosition;
	}

	public double[] getMinVelocity() {
		return minVelocity;
	}

	public void setMinVelocity(double[] minVelocity) {
		this.minVelocity = minVelocity;
	}

	public Vector<Position> getpBestPosition() {
		return pBestPosition;
	}

	public void setpBestPosition(Vector<Position> pBestPosition) {
		this.pBestPosition = pBestPosition;
	}

	public double[] getgBestPosition() {
		return gBestPosition;
	}

	public void setgBestPosition(double[] gBestPosition) {
		this.gBestPosition = gBestPosition;
	}
	
	

}
