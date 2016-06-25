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
import ru.spb.ipo.engine.elements.PermutationElement;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.functions.AbstractFunction;

public class SubstitutionSet extends Set {

	private List<PermutationElement> items;
	private int dimension; // длина каждой перестановки в наборе перестановок
	
	public SubstitutionSet() {
	}
	
	public SubstitutionSet(int dim) { // создает тривиальную группу перестановок из одного единичного элемента
		dimension = dim;
		items = new ArrayList<PermutationElement>();
		items.add(new PermutationElement(dim));
	}
	
	public SubstitutionSet(int dim, PermutationElement [] basis) {
		dimension = dim;
		items = new ArrayList<PermutationElement>();
		applyBasisRecursively(new PermutationElement(dim), basis);
	}
	
	private void applyBasisRecursively(PermutationElement t, PermutationElement [] basis) {
		if (!items.contains(t)) {
			items.add(t);
			for (PermutationElement next : basis) {
				applyBasisRecursively(next.applyTo(t), basis);
			}
		}
	}

	protected void initSet(Node node) throws SystemException, TaskDeserializationException {
		List<Element> containers = new ArrayList<Element>() ;
        List<Node> nl = node.getChilds("constElement");
		for (Node aNl : nl) {
			Element el = (Element) AbstractFunction.generateAbstractFunction(aNl);
			containers.add(el);
		}

		String dimAttr = node.getAttrIfExists("dim", null);
		if (dimAttr != null) {
			dimension = new Integer(dimAttr);
		}
		
		PermutationElement [] basis = new PermutationElement [containers.size()];
		
		for (int i = 0; i < containers.size(); i++) {
			ContainerElement container = (ContainerElement)containers.get(i);
			if (container.getLength() != dimension)
				throw new TaskDeserializationException("SubstitutionSet: размер вложенных базисных перестановок должен быть равен dim");
			basis[i] = new PermutationElement(container);
		}
		
		items = new ArrayList<PermutationElement>();
		applyBasisRecursively(new PermutationElement(dimension), basis);
	}

	boolean canMake(ContainerElement a, ContainerElement b) {
		boolean found = false;
		for (int i = 0; i < items.size() && (!found); i++)
			found = (items.get(i)).applyTo(a).equals(b);
		return found;	
	}

	public Element getElement(long index) {
		return items.get((int) index - 1);
	}

	public int getLength() {
		return dimension;
	}

	public long getSize() {
		return items.size();
	}


    public SetIterator iterator() {
        return new SetIterator() {
                int index = 0;
                 
                public boolean hasNext() {
                    return index < getSize();
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
