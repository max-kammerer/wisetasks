package ru.spb.ipo.generator.basket;

import ru.spb.ipo.generator.base.ComplexElement;

public class BasketBallComplexElement implements ComplexElement {

	protected int color, numbers;
	
	public BasketBallComplexElement(int color, int number) {
		this.color = color; 
		this.numbers = number;	
	}
	
	public String generateXml() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toDescription() {		
		return  numbers + " "  + BasketXmlGenerator.ballColors[color] + "";
	}


	public String toString() {
		return toDescription();
	}
	
	
}
