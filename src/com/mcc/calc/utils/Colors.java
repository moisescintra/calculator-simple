package com.mcc.calc.utils;

import java.awt.Color;

public final class Colors extends Color{
	private static final long serialVersionUID = 1L;

	private Colors() {
		//private - NOT Instantiate
		//Para que nao seja possivel instanciar a classe
		//### 
		super(0);
	}

	public final static Color panel() { return new Color( 46, 49, 50);}

	public final static Color textPanel() { return WHITE;}

	public final static Color keyBFunction() { return new Color( 68, 68, 68);}

	public final static Color keyBOperation() { return new Color( 242, 163, 60);}

	public final static Color keyBNums() { return new Color( 99, 99, 99);}

	public final static Color textKeyB() { return WHITE;}
	}
