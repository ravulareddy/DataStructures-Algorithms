package com.algos.psoNew;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GraphicsJComponent extends JComponent{
	
	/**
	 * 
	 */
	
	PSOVizualization psoVizualization;
	
	public GraphicsJComponent(PSOVizualization psoVizualization)
	{
			this.psoVizualization = psoVizualization;
			setBackground(Color.WHITE);
			setOpaque(true);
	}

	// paint backgrounf
	protected void paintComponent(Graphics graphics) {
		
		if(graphics == null)
		{
			System.out.println("Graphics is empty");
		}
		if( isOpaque() ) {
			graphics.setColor(getBackground());
			graphics.fillRect(0, 0, getWidth(), getHeight());
			
	//		g.setColor(getBackground());
	//		g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
/*	public void paint(Graphics g) {
		  int[] x =new int[]{65,  122,  77,  20};
		  int[] y =new int[]{226,  258, 341,  310};
		  g.setColor(Color.RED);  
		  g.drawPolygon (x, y, x.length);
	}
	

	
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.fillOval(0, 0, 30, 30);
		g2d.drawOval(0, 50, 30, 30);		
		g2d.fillRect(50, 0, 30, 30);
		g2d.drawRect(50, 50, 30, 30);

		g2d.draw(new Ellipse2D.Double(0, 100, 30, 30));
	}
	
	*/
	
	public void clear() {
		paintComponent(this.getGraphics());
	//	paint(this.getGraphics());
	}
	
	// run swarm
	protected void runSwarm() {
		Thread thread = new MyThreadPSO(psoVizualization);
		try {
			thread.join();
		} catch(InterruptedException e) {
			System.out.println("Thread Interrupted::");
		} 
	}

	// display points on screen
	protected void showSwarm() {
		psoVizualization.getSwarm().displayPointsJFrame(getGraphics(), getForeground(), getWidth(), getHeight(), psoVizualization.getDim0(), psoVizualization.getDim1(), true);
	}

}
