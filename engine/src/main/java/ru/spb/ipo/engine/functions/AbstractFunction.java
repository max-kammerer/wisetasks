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

import java.util.HashMap;
import java.util.Map;


import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.Parser;


abstract public class AbstractFunction {

    private static final CurrentElement ce = new CurrentElement();

    abstract public Element compute(Element parameter);

    public static AbstractFunction generateAbstractFunction(Node node) throws TaskDeserializationException, SystemException{

        if ("element".equals(node.getNodeName()))
            return ConstElement.generateElement(node);

        if ("constElement".equals(node.getNodeName()))
            return Element.generateElement(node);

        if ("function".equals(node.getNodeName()))
            return Function.generateFunction(node);

        if ("current-set-element".equals(node.getNodeName()))
            return ce;

        return null;
    }
    
    private static Parser parser = new Parser();
    
    public static Map<String, String> getAttributes(Node n) {
    	Map<String, String> attrs = n.getAttrs();
    	Map result = new HashMap();
    	if (attrs == null) {
            return result;
        }

        for (Map.Entry<String, String> entry: attrs.entrySet()) {
    		if (!"type".equals(entry.getKey())) {
    			result.put(entry.getKey(), parser.parse(entry.getValue()));
    		}
    	}    	
    	return result;
    }

}
