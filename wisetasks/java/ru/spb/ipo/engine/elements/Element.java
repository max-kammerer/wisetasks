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
import java.util.List;

import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.RationalNumber;


public abstract class Element extends ru.spb.ipo.engine.functions.AbstractFunction
        implements java.lang.Cloneable, java.lang.Comparable {

    public final static Element ptrue = new IntElement(1);

    public final static Element pfalse = new IntElement(0);

    protected static BigInteger MAX_INTEGER = BigInteger.valueOf(Integer.MAX_VALUE);
    
    protected static BigInteger MIN_INTEGER = BigInteger.valueOf(Integer.MIN_VALUE);
    
	public Element compute(Element element) {
		return this;
	}
	
	abstract public void setInt(int i);
	
	/*
	 * Functions must not use this method with current_set_element.
	 * and with other returning elements.
	 */
    abstract public void setElementAt(int index, Element element);

    public Object clone() {
        try {
            return super.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //надо расширить метод.
    public static Element generateElement(Node node)throws XmlException {

        List<Node> enodes = node.getChilds("constElement");

        if ((enodes != null) && (enodes.size() != 0)) {
           Element [] ea  = new Element [enodes.size()];
            for (int i = 0; i < enodes.size(); i++) {
                ea[i] = Element.generateElement(enodes.get(i));
            }
            return new ContainerElement(ea);
        }

        String txt = node.getText().trim();
        int t = new Integer(txt);
        return  new IntElement(t);
    }

    public boolean equals(Object o) {
        if (this.compareTo(o) == 0) {
            return true;
        }
        return false;
    }

	public int hashCode() {		
		return getInt();
	}

	abstract public int getInt();

    abstract public Element [] getElements();

    abstract public Element getElementAt(int index);

    abstract public int getLength();

    abstract public RationalNumber getRationalNumber();
}
