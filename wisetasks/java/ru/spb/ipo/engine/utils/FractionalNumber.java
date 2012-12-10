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

package ru.spb.ipo.engine.utils;

import java.math.BigInteger;

public class FractionalNumber implements Comparable {
	
	private BigInteger numerator;
	
	private BigInteger denominator;

	public FractionalNumber(BigInteger numerator) {
		this(numerator, BigInteger.ONE);
	}
	
	public FractionalNumber(int numerator) {
		this(BigInteger.valueOf(numerator));
	}
	
	public FractionalNumber(int numerator, int denominator) {
		this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
	}
	
	public FractionalNumber(BigInteger numerator, BigInteger denominator) {
		if (denominatorIsZero()) throw new IllegalArgumentException("Denominator must be none zero");
		this.numerator = numerator;
		this.denominator = denominator;
        normalize();
    }
	
	
	public FractionalNumber add(FractionalNumber number) {
		BigInteger newNumerator = numerator.multiply(number.denominator).add(denominator.multiply(number.numerator));  
		BigInteger newDenominator = denominator.multiply(number.denominator);
		return normalize(newNumerator, newDenominator);		
	}
	
	public FractionalNumber multiply(FractionalNumber number) {
		BigInteger newNumerator = numerator.multiply(number.numerator);  
		BigInteger newDenominator = denominator.multiply(number.denominator);
		return normalize(newNumerator, newDenominator);		
	}
	
	public FractionalNumber divide(FractionalNumber number) {
		if (number.isZero()) throw new IllegalArgumentException("number must be not zero");
		BigInteger newNumerator = numerator.multiply(number.denominator);  
		BigInteger newDenominator = denominator.multiply(number.numerator);
		return normalize(newNumerator, newDenominator);		
	}
	
	public FractionalNumber substract(FractionalNumber number) {
		BigInteger newNumerator = numerator.multiply(number.denominator).subtract(denominator.multiply(number.numerator));  
		BigInteger newDenominator = denominator.multiply(number.denominator);
		return normalize(newNumerator, newDenominator);		
	}
	
	public FractionalNumber pow(FractionalNumber power) {
		BigInteger myPower = power.getBigInteger();
		return pow(myPower);
	}
	
	public FractionalNumber pow(BigInteger power) {
		BigInteger myPower = power;
		return normalize(numerator.pow(myPower.intValue()), denominator.pow(myPower.intValue()));
	}
	
	public BigInteger getDenominator() {
		return denominator;
	}
	
	public void setNumerator(BigInteger numerator) {
		this.numerator = numerator;
	}
	
	private FractionalNumber normalize(BigInteger newNumerator, BigInteger newDenominator) {		
		//BigInteger generalDivisor = euclid(newNumerator, newDenominator);
		return new FractionalNumber(newNumerator, newDenominator);
	}
	
	private void normalize() {
        //make denominator always positive
        if (denominator.signum() == -1) {
            numerator = BigInteger.ZERO.subtract(numerator);
            denominator = BigInteger.ZERO.subtract(denominator);
        }

        BigInteger generalDivisor = numerator.gcd(denominator);
//        BigInteger generalDivisor = euclid(numerator, denominator);
		numerator = numerator.divide(generalDivisor);
		denominator = denominator.divide(generalDivisor);
    }


	public BigInteger getBigInteger() {
		normalize();
		if (!BigInteger.ONE.equals(denominator)) {
			throw new RuntimeException("Cannot cast this number to BigInteger");
		}
		return numerator; 
	}	
	
	public BigInteger round() {
		normalize();
		return numerator.divide(denominator);		 
	}
	
	public String toString() {
		normalize();
		return numerator + "/" + denominator;
	}
	
	private boolean denominatorIsZero() {
		if (BigInteger.ZERO.equals(denominator)) {
			return true;
		}
		return false;
	}
	
	public boolean isZero() {
		if (BigInteger.ZERO.equals(numerator)) {
			return true;
		}
		return false;
	}
	
	public boolean equals(Object o) {
		if (o instanceof FractionalNumber == false) {
			return false;			
		}
		FractionalNumber number = (FractionalNumber)o;
		number.normalize();
		this.normalize();
		if (numerator.equals(number.getNumerator())&& denominator.equals(number.getDenominator())) {
			return true;			
		}
		return false;
	}
	//TODO implements equals(), hash()

	
	
	public BigInteger getNumerator() {
		return numerator;
	}

	public int compareTo(Object o) {
		if (o instanceof FractionalNumber == false) {
			return -1;			
		}
		FractionalNumber oth = (FractionalNumber) o;
		return numerator.multiply(oth.getDenominator()).compareTo(denominator.multiply(oth.getNumerator()));		
	}
	
	public int hashCode() {		
		return numerator.hashCode() / 2 + denominator.hashCode() / 2;
	}
}
