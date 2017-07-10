package com.algos.psoNew;



public class MyThreadPSO extends Thread{
	
	int displayStep = 1;
	
	PSOVizualization psoVizualization;
	public MyThreadPSO(PSOVizualization psoVizualization) {
		super("My Thread Particle Swarm Optimization :::");
		this.psoVizualization = psoVizualization;
		start();
	}
	

	
	public void run() {
		for( int i = 0; i < psoVizualization.getNumberOfIterations(); i++ ) {
			// Show something every displayStep iterations
			if( (i % displayStep) == 0 ) {
				psoVizualization.setMessage("Iteration: " + i + "  Best fitness: " + psoVizualization.getSwarm().getgBest() + "          ");
				psoVizualization.clear();
				psoVizualization.showSwarm();
			}

			// Execute the program swarm
			psoVizualization.getSwarm().execute(2);
		}
		psoVizualization.setMessage("Finished: Best fitness: " + psoVizualization.getSwarm().getgBest() + "          ");
	}

}
