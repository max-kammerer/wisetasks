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

package ru.spb.ipo.engine.sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.functions.AbstractFunction;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.Utils;

/**
 * Множество - перечисление, порядок элементов не сохраняется.
 */
public class EnumerationSet extends Set {

    private List<Element> list = new ArrayList();

    public EnumerationSet(Element [] elements) {
        Collections.addAll(list, elements);
    }

    public EnumerationSet() {
        list = new ArrayList();
    }

    public void initSet (Node node) throws TaskDeserializationException, SystemException {
        List<Node> nl = node.getChilds("constElement");
        for (Node child : nl) {
            Element el = (Element)AbstractFunction.generateAbstractFunction(child);
            list.add(el);
            String repeat = child.getAttrIfExists("dublicate", null);
            if (repeat != null) {
                int repeatNumber = Integer.valueOf(repeat);
                for (int j = 1; j < repeatNumber; j++) {
                    list.add(el.clone());
                }
            }
        }
    }

  
	public Element getElement(long index) {		
		return (Element) list.get((int)index - 1);		
	}

	public int getLength() {
		return 1;
	}
	
	public long getSize() {
		return list.size();
	}

    public SetIterator iterator() {
        return new ListIterator(list);        
    }
}