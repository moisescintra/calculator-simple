package com.mcc.calc.model;

@FunctionalInterface
public interface ObserverMemory {

	public void changedValue(String newValue);
}
