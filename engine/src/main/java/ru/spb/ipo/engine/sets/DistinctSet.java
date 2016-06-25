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
import java.util.List;

import ru.spb.ipo.engine.elements.ContainerElement;
import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.Utils;

public class DistinctSet extends Set {

	private ArrayList<ContainerElement> unique;
    private int len;

	
	protected void initSet(Node node) throws SystemException, TaskDeserializationException {
		List<Node> lsets = node.getChilds("set");
		if (lsets.size() == 2) {
			if (!"SubstitutionSet".equals(lsets.get(1).getAttr("type")))
				throw new TaskDeserializationException("DistinctSet: 2-е параметр множество должно быть SubstitutionSet");
		}
		else if (lsets.size() != 1)
			throw new TaskDeserializationException("DistinctSet должен иметь одно или два параметра-множества");
		
		Set source = generateSet(lsets.get(0));
		len = source.getLength();
		if (lsets.size() == 2) {
			String tn = lsets.get(1).getAttr("dim");
			if (tn == null || Integer.parseInt(tn) != source.getLength())
				throw new TaskDeserializationException("DistinctSet: длина кортежей 1-го множества должна совпадать с dim");
		}
		
		SubstitutionSet perm;
		if (lsets.size() == 2)
			perm = (SubstitutionSet)generateSet(lsets.get(1));
		else
			perm = new SubstitutionSet(len);
		
		unique = new ArrayList<ContainerElement>();
		SetIterator it = source.iterator();
		while (it.hasNext()) {
            ContainerElement current = (ContainerElement)it.next();
            boolean found = false;
			for (int i = 0; i < unique.size() && !found; i++) {
				if (perm.canMake(current, unique.get(i))) {
					found = true;
				}
			}
			if (!found) 
				unique.add((ContainerElement)(current).clone());
		}		
	}


	public Element getElement(long index) {
		return unique.get((int)index - 1);
	}

	public int getLength() {
		return len;
	}

	public long getSize() {
		return unique.size();
	}


    public SetIterator iterator() {
        return new SetIterator() {
           int  index = 0;

            public boolean hasNext() {
                return index < unique.size();
            }

            public Element next() {
                return getElement(++index);
            }

            public void reset() {
                index = 0;
            }
        };
    }
}
