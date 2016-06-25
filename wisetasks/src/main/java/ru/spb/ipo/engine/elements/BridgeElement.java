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

import ru.spb.ipo.engine.utils.RationalNumber;

public class BridgeElement extends Element  {

	private Element original;
	
	public BridgeElement(Element original) {
		this.original = original;
	}
	
	public Element clone() {
		return original.clone();
	}

	public Element compute(Element element) {
		return original.compute(element);
	}

	public boolean equals(Object o) {
		return original.equals(o);
	}

	public Element getElementAt(int index) {
		return original.getElementAt(index);
	}

	public Element[] getElements() {		
		return original.getElements();
	}

	public RationalNumber getRationalNumber() {
		return original.getRationalNumber();
	}

	public int getInt() {		
		return original.getInt();
	}

	public int getLength() {
		return original.getLength();
	}

	public void setElementAt(int index, Element element) {
		original.setElementAt(index, element);
	}

	public void setInt(int i) {
		original.setInt(i);
	}

	public int compareTo(Object o) {
		if (o != null && o instanceof BridgeElement) {
			return original.compareTo(((BridgeElement)o).original);			
		} else {
			return original.compareTo(o);
		}
	}

	public Element getOriginal() {
		return original;
	}

	public void setOriginal(Element original) {
		this.original = original;
	}

	public String toString() {		
		return original.toString();
	}	
}
