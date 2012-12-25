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

public class RationalElement extends IntElement {

	private RationalNumber fnumber = null;

	public RationalElement(RationalNumber value) {
		super(0);
		fnumber = value;
	}
	
	public void setInt(int i){
		fnumber = new RationalNumber(i);
	}

	public int getInt() {
//		if (fnumber.getBigInteger().compareTo(MAX_INTEGER) <= 0 && bi.fnumber.getBigInteger()compareTo(MIN_INTEGER) >= 0) {
//			return fnumber.intValue();
//		} else {
			throw new RuntimeException("The value of BigInteger element cann't be placed into Integer range. Value is " + fnumber.toString());
//		}
	}
	
	public String toString(){
		return String.valueOf(fnumber.toString());		
	}

	public int compareTo(Object o) {
		if (!(o instanceof RationalElement)) return 1;
		RationalElement e = (RationalElement) o;
		if (e == null) return 1;		
		return fnumber.compareTo(e.getRationalNumber());
	}

	public RationalNumber getRationalNumber() {
		return fnumber;		
	}
}