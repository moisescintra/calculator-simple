package com.mcc.calc.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.mcc.calc.model.Memory;
import com.mcc.calc.utils.Colors;

public class Keyboard extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints bagConstrainsts = new GridBagConstraints();

	public Keyboard() {
		GridBagLayout layout = new GridBagLayout();

		setLayout(layout);

		bagConstrainsts.weightx = 1;
		bagConstrainsts.weighty = 1;
		bagConstrainsts.fill = GridBagConstraints.BOTH;

		buttonAdd("AC", Colors.keyBFunction(), 0, 0);
		buttonAdd("+/-", Colors.keyBFunction(), 1, 0);
		buttonAdd("%", Colors.keyBFunction(), 2, 0);
		buttonAdd("/", Colors.keyBOperation(), 3, 0);

		buttonAdd("7", Colors.keyBNums(), 0, 1);
		buttonAdd("8", Colors.keyBNums(), 1, 1);
		buttonAdd("9", Colors.keyBNums(), 2, 1);
		buttonAdd("*", Colors.keyBOperation(), 3, 1);

		buttonAdd("4", Colors.keyBNums(), 0, 2);
		buttonAdd("5", Colors.keyBNums(), 1, 2);
		buttonAdd("6", Colors.keyBNums(), 2, 2);
		buttonAdd("-", Colors.keyBOperation(), 3, 2);

		buttonAdd("1", Colors.keyBNums(), 0, 3);
		buttonAdd("2", Colors.keyBNums(), 1, 3);
		buttonAdd("3", Colors.keyBNums(), 2, 3);
		buttonAdd("+", Colors.keyBOperation(), 3, 3);

		bagConstrainsts.gridwidth = 2;
		buttonAdd("0", Colors.keyBNums(), 0, 4);
		bagConstrainsts.gridwidth = 1;
		buttonAdd(",", Colors.keyBNums(), 2, 4);
		buttonAdd("=", Colors.keyBOperation(), 3, 4);
	}

	private void buttonAdd (String text, Color color, int x, int y) {
		bagConstrainsts.gridx = x;
		bagConstrainsts.gridy = y;
		Button button = new Button( text, color);
		button.addActionListener( this);
		add( button, bagConstrainsts);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			Memory.getInstance().commandProcess(button.getText());
		}
	}
}
