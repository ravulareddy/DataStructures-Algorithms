package com.algos.psoNew;



public class MyApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PSO_CloudComputing pso = new PSO_CloudComputing(2);
		Graphs graphs = new Graphs();
		// run pso 
	//	pso.execute();
	
		
		double[] array1 = new double[]{0.5, 0.4, 0.3, 0.2};
		pso.findRankOfTask(array1);
		
	//	double result = PSO_CloudComputing.evaluate(array1);
	//	System.out.println("result :: "+ result);
		
		pso.createSampleParticle(2);
		
	// 	run the algorithm 
	
		
		//
	//	create graph ::
		try
		{
	//	graphs.createGraph(2);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Not able to generate graph");
		}
		
	// set values
		pso.setInertia(0.99);
		pso.setConst1(0.9);
		pso.setConst2(0.9);
		pso.setNoOfIterMax(800);
		
		// Set position (and velocity) constraints.
		int dimension = 2;
			double maxPosition[] = new double[dimension];
			double minPosition[] = new double[dimension];
			
			double maxVel[] = new double[dimension];
			double minVel[] = new double[dimension];
			
			for (int i = 0; i < dimension; i++)
			{
		//		maxPosition[i] = 100;
		//		minPosition[i] = -100;
				
				maxPosition[i] = 30;
				minPosition[i] = 0;
				
				maxVel[i] = 0.1;
				minVel[i] = -0.1;
			}
			
		// set max and min 
		pso.setMaxPosition(maxPosition);
		pso.setMinPosition(minPosition);
		
		pso.setMaxVelocity(maxVel);
		pso.setMinVelocity(minVel);
			
		// run the algorithm 
//		pso.execute(2);
			
		// Display graph
		PSOVizualization psoVizualization = new PSOVizualization(pso, pso.getNoOfIterMax());
		psoVizualization.run();

		// Show best position
		double bestPosition[] = psoVizualization.getSwarm().getgBestPosition();
		System.out.println("Best position: [" + bestPosition[0] + ", " + bestPosition[1] + " ]\n Best fitness: " + psoVizualization.getSwarm().getgBest());
	
		
	}
	
	
	public void printArray(double arr[])
	{
		for(int i=0; i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}
	}
	
	

}
