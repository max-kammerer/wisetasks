/*
 * This file is part of Wisetasks
 *
 * Copyright (C) 2006-2008, 2012  Michael Bogdanov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.spb.ipo.engine.elements;

import java.math.BigInteger;

import ru.spb.ipo.engine.utils.RationalNumber;


public class IntElement extends Element {

	private int value;
	private RationalNumber fnumber = null;

	public IntElement(int value) {
		this.value = value;
	}
	
	public void setInt(int i){
		value = i;
	}

	public void setElementAt(int index, Element element) {		
		throw new UnsupportedOperationException("It's a simple element but not container");
	}
	
	public int getInt() {
		return value;
	}
	
	public Element [] getElements() {
		return null; //MAYBE this
	}
	
	public Element getElementAt(int index) {
		throw new UnsupportedOperationException("It's a simple element but not a container");		
	}	

	public String toString(){
		return String.valueOf(value);		
	}

	public int getLength() {
		return 1;		
	}
	
	public int compareTo(Object o) {		
		if (!(o instanceof IntElement)) return 1;
		IntElement e = (IntElement) o;
		if (e == null) return 1;
		
		if (value < e.getInt()) return -1;
		if (value > e.getInt()) return 1;
		return 0;		
	}


	public RationalNumber getRationalNumber() {
		if (fnumber == null) {
			fnumber = new RationalNumber(BigInteger.valueOf(value));
		} else {
			if (fnumber.getBigInteger().intValue() != value) {			
				fnumber = new RationalNumber(BigInteger.valueOf(value));
			}
		}		
		return fnumber;		
	}
	
	
}
