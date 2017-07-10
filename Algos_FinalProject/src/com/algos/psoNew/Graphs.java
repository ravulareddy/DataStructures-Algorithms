package com.algos.psoNew;

import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

import java.awt.Color; 
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries;  
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class Graphs extends JFrame{
	
	Random random = new Random();
	
	private XYDataset createDataset() {
	    XYSeriesCollection result = new XYSeriesCollection();
	    XYSeries series = new XYSeries("Random");
	    for (int i = 0; i <= 100; i++) {
	        double x = random.nextDouble();
	        double y = random.nextDouble();
	        series.add(x, y);
	    }
	    result.addSeries(series);
	    return result;
	}

	
	public void createGraph(int dimension) throws IOException{
		
		System.out.println("graph :: ");
		
		JFreeChart xylineChart = ChartFactory.createXYLineChart(
		         "Particle Swarm Optimization" ,
		         "Category" ,
		         "Score" ,
		         createDataset() ,
		         PlotOrientation.VERTICAL ,
		         true , true , false);
		         
		      ChartPanel chartPanel = new ChartPanel( xylineChart );
		      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
		      final XYPlot plot = xylineChart.getXYPlot( );
		      
		      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
		      for(int i= 0; i< 5000; i++)
		      {  
		      renderer.setSeriesPaint( 0 , Color.RED );
		      renderer.setSeriesPaint( 1 , Color.GREEN );
		      renderer.setSeriesPaint( 2 , Color.YELLOW );
		      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
		      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
		      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
		    
		      }
		      
		      plot.setRenderer( renderer ); 
		      setContentPane( chartPanel ); 
		
	}
	
	
}
