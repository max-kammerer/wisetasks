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

import java.util.Formatter;

import ru.spb.ipo.engine.sets.Set;
import ru.spb.ipo.engine.sets.SetIterator;
import ru.spb.ipo.engine.functions.ConstElement;

public class PermutationElement extends ContainerElement {

	public PermutationElement(Element[] children) {
		super(children);
	}
	
	public PermutationElement(int dimension) {
		super(new Element[dimension]);
		for (int i = 0; i < dimension;)
			this.setElementAt(i, new IntElement(++i));
	}
	
	public PermutationElement(Set s) {
		super(new Element[(int)s.getSize()]);
        SetIterator it = s.iterator();
		for (int i = 0; i < (int)s.getSize() && it.hasNext(); i++) {
			this.setElementAt(i, (Element) it.next().clone());
		}
	}

    public PermutationElement(ContainerElement element) {
		super(element.getElements());        
	}

    /** create a new list by applying permutation to parameter **/
	public ContainerElement applyTo(ContainerElement a) {
		if (this.getLength() != a.getLength())
			throw new RuntimeException(
					new Formatter()
					.format("Permutation length %1$d does not match list length %2$d", this.getLength(), a.getLength())
					.toString());
		Element [] b = new Element[a.getLength()];
		for (int i = 0; i < a.getLength(); i++)
			b[i] = a.getElementAt(this.getElementAt(i+1).getInt());
		return new ContainerElement(b);
	}
	
	/** create a new list by applying permutation to parameter **/
	public PermutationElement applyTo(PermutationElement a) {
		if (this.getLength() != a.getLength())
			throw new RuntimeException(
					new Formatter()
					.format("Permutation length %1$d does not match list length %2$d", this.getLength(), a.getLength())
					.toString());
		Element [] b = new Element[a.getLength()];
		for (int i = 0; i < a.getLength(); i++)
			b[i] = a.getElementAt(this.getElementAt(i+1).getInt());
		return new PermutationElement(b);
	}
	
}
