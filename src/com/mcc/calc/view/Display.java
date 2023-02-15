package com.mcc.calc.view;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mcc.calc.model.Memory;
import com.mcc.calc.model.ObserverMemory;
import com.mcc.calc.utils.Colors;

public class Display extends JPanel implements ObserverMemory{
	private static final long serialVersionUID = 1L;

	private final JLabel label;

	public Display() {

		Memory.getInstance().observerAdd(this);

		setBackground( Colors.panel());
		label = new JLabel(Memory.getInstance().getActualText());
		label.setForeground( Colors.textPanel());
		label.setFont( new Font( "courier", Font.PLAIN, 30));

		setLayout(new FlowLayout( FlowLayout.RIGHT, 2, 60));

		add(label);
	}

	@Override
	public void changedValue(String newValue) {
		label.setText(newValue);
	}
}
