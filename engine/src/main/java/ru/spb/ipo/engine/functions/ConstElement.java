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


import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.elements.IntElement;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.Utils;

import java.util.List;

/**
 * Author: Michael.Bogdanov
 * Date: 03.01.2005
 */
public class ConstElement extends Function {

    private Element el;
    	
   
    public void initFunction(Node node) throws TaskDeserializationException, SystemException {
        super.initFunction(node);        
    }

    public Element compute(Element parameter) {
    	//if (fns == null) return null;
    	if (el == null) {
			Element [] elements = new Element [fns.length];
			for (int i = 0; i < fns.length; i++) {
				elements[i] = fns[i].compute(parameter);
			}
    	}    	
                
        /*for(int i = 0; i < fns.length; i++) {
            el.setIntAt(fns[i].getElement(parameter).getInt(), i + 1);
        }*/ 
        return el;
    }

    public static AbstractFunction generateElement(Node node) throws TaskDeserializationException, SystemException {
        List<Node> enodes = node.getFunctionList();

        if (enodes != null && enodes.size() != 0) {
           Function af = new ConstElement();
           af.initFunction(node);
           return af;
        }

        String txt = node.getText();
        int t = new Integer(txt);
        return new IntElement(t);
    }

}
