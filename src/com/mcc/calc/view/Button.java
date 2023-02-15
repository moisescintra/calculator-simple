package com.mcc.calc.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.mcc.calc.utils.Colors;

public class Button extends JButton{
	private static final long serialVersionUID = 1L;

	public Button(String text, Color color) {
		setFont(new Font("courier", Font.PLAIN, 25));
		setText(text);
		setForeground(Colors.textKeyB());
		setOpaque(true);
		setBackground(color);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
