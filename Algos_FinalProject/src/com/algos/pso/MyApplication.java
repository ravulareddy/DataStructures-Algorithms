package com.algos.pso;

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;

import com.algos.psoNew.Graphs;

public class MyApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ParticleSwarmOptimization pso = new ParticleSwarmOptimization();
		// run pso 
		pso.execute();
	
		
		int[] array1 = new int[]{16, 12, 28, 36};
	//	pso.findRankOfTask(array1);
		
		Graphs graphs = new Graphs();
		try {
			graphs.createGraph(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
