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
import java.util.Map;


import ru.spb.ipo.engine.elements.ContainerElement;
import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.elements.IntElement;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.FractionalNumber;
//TODO rewrite don't use cache
public class Count extends Function {

    private Element fe;
    private int length;
    private Element [] array;
    private int axis;

    public void initFunction(Node node) throws TaskDeserializationException, SystemException {
    	super.initFunction(node);
    	Map m = super.getAttributes(node);
    	if (m.containsKey("axis")) {
            axis = ((FractionalNumber)m.get("axis")).getBigInteger().intValue();
        }
    }

    public Element compute(Element parameter) {
        Element element = fns[0].compute(parameter);
        if (fe  == null) {
            length = element.getLength();            
            array = new Element[length];
            Element []  temp = new Element[length];
        	for (int i = 0; i < temp.length; i++) {
				temp[i] = new IntElement(0);
			}
        	fe = new ContainerElement(temp);
        }
        
        for (int i = 1; i <= length; i++) {
            if (axis != 0)
                array [i-1] = element.getElementAt(i).getElementAt(axis);
            else array [i-1] = element.getElementAt(i);
        }
        Arrays.sort(array);
        int index = 1;
        Element prev = array[0];
        int count = 1;
        for (int i = 1; i < length; i++) {
            if (array[i].equals(prev)) count++;
            else {            	
                fe.getElementAt(index).setInt(count);
                index++;
                count = 1;
            }
            prev = array[i];
        }
        fe.getElementAt(index).setInt(count);
        index++;        
        for (int i = index; i <= length; i++)
        	fe.getElementAt(i).setInt(0);
        if (fe.getElements()  != null) Arrays.sort(fe.getElements());
        
        return fe;
    }
}
