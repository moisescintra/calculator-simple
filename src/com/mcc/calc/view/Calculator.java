package com.mcc.calc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Calculator extends JFrame{
	private static final long serialVersionUID = 1L;

	private final int WIDTH = 432;
	private final int HEIGHT = 522;

	private Calculator() {

		configLayout();

		setSize( getWIDTH(), getHEIGHT());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Calculator();
	}

	private void configLayout() {
		setLayout(new BorderLayout());

		Display display = new Display();
		display.setPreferredSize( new Dimension( getWIDTH(), getHEIGHT()/5));
		add( display, BorderLayout.NORTH);

		Keyboard keyboard = new Keyboard();
		add( keyboard, BorderLayout.CENTER);
		
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}
}
