package com.mcc.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {

	private static final Memory instance = new Memory();
	//### 
	// Name - SINGLETON
	//##

	private String actualText = "0";

	private String bufText = "";

	private CommandType bufCommand = null;

	private boolean replace = false;

	private final List<ObserverMemory> observers = new ArrayList<>();

	private Memory() {
		//Para que nao seja possivel instanciar a classe
	}

	public static Memory getInstance() {
		return instance;
	}

	public void observerAdd(ObserverMemory observer) {
		observers.add(observer);
	}

	public void commandProcess(String value) {
		CommandType commandType = getCommandType(value);

		if(commandType == CommandType.AC) {
			setActualText("0");
			setReplace(false);
		} else if (commandType == CommandType.CHANGESIGNAL) {
			if(getActualText().equals("0"))
				return;
			if(getActualText().contains("-"))
				setActualText(getActualText().substring(1));
			else
				setActualText( "-" + getActualText());
		} else if(commandType == CommandType.NUMS || commandType == CommandType.COMMA) {
			if(commandType == CommandType.COMMA && getActualText().contains(","))
				return;
			setActualText( isReplace() ? value 
					: (getActualText().equals("0") ? 
						(commandType == CommandType.COMMA ? "0" + value : value) 
						: getActualText() + value));
			setReplace(false);
		} else {
			setReplace(true);
			setActualText(getOperationResult(commandType));
			setBufText(getActualText());
			setBufCommand(commandType);
		}

		observers.forEach(o -> o.changedValue(getActualText()));
	}

	private String getOperationResult(CommandType commandType) {
		if(getBufCommand() == null || getBufCommand() == CommandType.EQUAL || getBufCommand() == CommandType.PERCENT)
			return getActualText();

		Double buf = Double.parseDouble(getBufText().replace(",", "."));

		Double actual = Double.parseDouble(getActualText().replace(",", "."));

		Double result = 0d;
		
		boolean percent = false;

		if(commandType == CommandType.PERCENT)
			percent = true;

		if(getBufCommand() == CommandType.ADD)
			result = buf + (percent ? buf * (actual/100) : actual);
		else if(getBufCommand() == CommandType.SUBT)
			result = buf - (percent ? buf * (actual/100) : actual);
		else if(getBufCommand() == CommandType.MULT)
			result = buf * (percent ? (actual/100) : actual);
		else if(getBufCommand() == CommandType.DIV)
			result = buf / (percent ? (actual/100) : actual);

		String resultString = result.toString().replace(".", ",");

		return resultString.endsWith(",0") ? resultString.replace(",0", "") : resultString;
	}

	private CommandType getCommandType(String text) {
		try {
			Integer.parseInt(text);
			return CommandType.NUMS;
		} catch (NumberFormatException e) {
			if(text.equals("AC"))
				return CommandType.AC;
			else if(text.equals("/"))
				return CommandType.DIV;
			else if(text.equals("*"))
				return CommandType.MULT;
			else if(text.equals("-"))
				return CommandType.SUBT;
			else if(text.equals("+"))
				return CommandType.ADD;
			else if(text.equals(","))
				return CommandType.COMMA;
			else if(text.equals("="))
				return CommandType.EQUAL;
			else if(text.equals("+/-"))
				return CommandType.CHANGESIGNAL;
			else if(text.equals("%"))
				return CommandType.PERCENT;
		}

		return null;
	}

	private enum CommandType {
		NUMS, DIV, MULT, SUBT, ADD, PERCENT, CHANGESIGNAL, AC, COMMA, EQUAL
	}

	public String getActualText() {
		return actualText.isEmpty() ? "0" : actualText;
	}

	public void setActualText(String actualText) {
		this.actualText = actualText;
	}

	public boolean isReplace() {
		return replace;
	}

	public void setReplace(boolean replace) {
		this.replace = replace;
	}

	public String getBufText() {
		return bufText;
	}

	public void setBufText(String bufText) {
		this.bufText = bufText;
	}

	public CommandType getBufCommand() {
		return bufCommand;
	}

	public void setBufCommand(CommandType bufCommand) {
		this.bufCommand = bufCommand;
	}
}
