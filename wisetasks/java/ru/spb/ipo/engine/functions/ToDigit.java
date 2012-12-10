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

import ru.spb.ipo.engine.elements.ContainerElement;
import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.elements.IntElement;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.task.Node;

public class ToDigit extends Function{

    private IntElement fe;

    public void initFunction(Node node) throws TaskDeserializationException, SystemException {
        super.initFunction(node);
        fe = new IntElement(0);
    }

    public Element compute(Element parameter) {    	
    	Element e = fns[0].compute(parameter);
    	fe.setInt(computeAnswer(e));    	        
        return fe;
    }

    public static int computeAnswer(Element e) {    	
    	if (!(e instanceof ContainerElement)) {
    		return e.getInt();
    	} else {
    		int size = e.getLength();    		
    		int res = 0;    		
    		int power = 1;
    		for (int i = size; i >= 1; i--) {
				res = power * e.getElementAt(i).getInt() + res;
				power = power * 10;
			}
    		return res;
    	}
    }    
}