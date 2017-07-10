package com.algos.psoNew;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PSOVizualization implements Runnable, ActionListener{
	
	
	JFrame jframe;
	JLabel message;
	int numberOfIterations;
	int dim0 = 0;
	int dim1 = 1;
	PSO_CloudComputing swarm;
	GraphicsJComponent graphicsJComponent;
	
	public PSOVizualization(PSO_CloudComputing swarm, int numberOfIterations) {
		// Initialize
		this.swarm = swarm;
		this.numberOfIterations = numberOfIterations;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		Dimension screenSize = new Dimension(1080, 700);

		// Create and set up the window.
		jframe = new JFrame("Particle Swarm Optimization");
	//	jframe.setSize(800, 600);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		this.createUserInterface(jframe.getContentPane());

		// Display the window.
	//	jframe.pack();
		jframe.setPreferredSize(screenSize);
	    jframe.setMinimumSize(screenSize);
	    jframe.setMaximumSize(screenSize);

	    jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);

		this.graphicsJComponent.runSwarm();
		this.graphicsJComponent.repaint();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// for Color in jframe 
//	@Override
	public void run1() {
		// TODO Auto-generated method stub
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		Dimension screenSize = new Dimension(800, 600);

		// Create and set up the window.
		jframe = new JFrame("Particle Swarm Optimization");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		this.createUserInterface(jframe.getContentPane());

		// Display the window.
		jframe.setPreferredSize(screenSize);
	    jframe.setMinimumSize(screenSize);
	    jframe.setMaximumSize(screenSize);

	    jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);

		this.graphicsJComponent.runSwarm();
		this.graphicsJComponent.repaint();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createUserInterface(Container container)
	{
	int rowNum = 0;

	Dimension size = new Dimension(800, 600);

	container.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.fill = GridBagConstraints.BOTH;

	gbc.weightx = 1;
	gbc.weighty = 1;

	// Add an area to draw
			graphicsJComponent = new GraphicsJComponent(this);
			gbc.gridx = 0;
			gbc.gridy = rowNum;
			gbc.gridwidth = 10;
			gbc.gridheight = 4;
			container.add(graphicsJComponent, gbc);
			rowNum += 10;

	gbc.fill = GridBagConstraints.BOTH;
	gbc.weightx = 0;
	gbc.weighty = 0;

	message = new JLabel(); 
	message.setText("Ok");
	gbc.gridx = 0;
	gbc.gridy = rowNum++;
	gbc.gridwidth = 4;
	gbc.gridheight = 1;
	container.add(message, gbc);
	
	}

	public int getNumberOfIterations() {
		return numberOfIterations;
	}

	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}

	public PSO_CloudComputing getSwarm() {
		return swarm;
	}

	public void setSwarm(PSO_CloudComputing swarm) {
		this.swarm = swarm;
	}

	public int getDim0() {
		return dim0;
	}

	public void setDim0(int dim0) {
		this.dim0 = dim0;
	}

	public int getDim1() {
		return dim1;
	}

	public void setDim1(int dim1) {
		this.dim1 = dim1;
	}
	
	public void clear()
	{
		graphicsJComponent.clear();	
	}
	
	public void showSwarm() {
		graphicsJComponent.showSwarm();
	}

	public JLabel getMessage() {
		return message;
	}

	public void setMessage(String text) {
		message.setText(text);
	}
	
	
	
}
