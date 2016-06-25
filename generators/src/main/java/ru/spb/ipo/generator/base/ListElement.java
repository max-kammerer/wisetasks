/**
 * 
 */
package ru.spb.ipo.generator.base;

import ru.spb.ipo.generator.cards.TypeModell.Generator;

public class ListElement {

	private String text;

	private Generator gen;
	
	public ListElement(String text, Generator gen) {
		this.text = text;
		this.gen = gen;
	}

	public String toString() {			
		return text;
	}
	
	public String generateXml() {
		return gen.generateXml();		
	}

	public Generator getGen() {
		return gen;
	}
	
	
}