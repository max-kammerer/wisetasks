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

package ru.spb.ipo.engine.functions;

import java.util.Arrays;

import ru.spb.ipo.engine.elements.Element;

public class Like extends Function {
	
    private int length;
    private Element [] array1, array2;
	
    public Element compute(Element parameter) {
    	if ((fns[0] == null) || (fns[1] == null)) System.err.println("fns [0] or [1] is null");
    	
        Element el1 = fns[0].compute(parameter);
        Element el2 = fns[1].compute(parameter);        
        if (array1 == null) {
            length = el1.getLength();            
            array1 = new Element[length];
            array2 = new Element[length];            
        }
        
        for (int i = 1; i <= length; i++) {
            array1 [i-1] = el1.getElementAt(i);
            array2 [i-1] = el2.getElementAt(i);
        }
        
        Arrays.sort(array1);
        Arrays.sort(array2);
        for (int i = 0; i < array1.length; i++) {
			if (!array1[i].equals(array2[i])) {				
				return Element.pfalse; 
			}
		}        
        return Element.ptrue;
    }

	
	
}
