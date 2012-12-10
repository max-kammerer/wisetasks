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
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.FractionalNumber;

import java.util.Map;

public class Projection extends Function {

    private int axis;

    public void initFunction(Node node) throws TaskDeserializationException, SystemException {
    	super.initFunction(node);
    	Map m = super.getAttributes(node);
    	if (m.containsKey("axis")) {
            axis = ((FractionalNumber)m.get("axis")).getBigInteger().intValue();
        }
    }

    public Element compute(Element parameter) {
        return fns[0].compute(parameter).getElementAt(axis);
    }

}
