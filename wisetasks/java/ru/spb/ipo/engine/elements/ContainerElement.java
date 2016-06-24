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


public class ContainerElement extends Element {
	
	private Element [] array = null;

	public ContainerElement(Element[] children) {
		array = children;		
	}
	
	public void setInt(int i){
		//TODO exception
	}
	
	public void setElementAt(int index, Element element) {
		if (array != null) {
			array[index % array.length] = element;
		}
	}
	
	public int getInt() {
		throw new UnsupportedOperationException("It's a container class but not simple element");		
	}
	
	public Element [] getElements() {
		return array;
	}

	public Element getElementAt(int index) {
		if (array == null) return null;
		if (index < 0) return null;
		return array[(index - 1) % array.length];
	}

	public String toString(){
		if (array == null) return String.valueOf("Empty element");
		StringBuilder sb = new StringBuilder("{ ");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i].toString());
			if (i != array.length - 1) {
                sb.append(", ");
            }
        }
		sb.append("}");
		return sb.toString();
	}
	
	public int getLength() {
		if (array == null) return 0;
		return array.length;		
	}

	public int compareTo(Object o) {
		if (!(o instanceof Element)) return 1;
		Element e = (Element) o;

		if (array.length != e.getLength()) {			
			return -1;
		} 
		
		for (int i = 0; i < array.length; i++) {
			int c = array[i].compareTo(e.getElementAt(i + 1));
			if (c != 0) return c;
		}		
		return 0;
	}
	
	public RationalNumber getRationalNumber() {
		return null;
	}
	
}
