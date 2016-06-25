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

import ru.spb.ipo.engine.elements.Element;

import java.util.List;
import java.util.Iterator;

/**
 * User: mike
 * Date: 18.05.2009
 */
public class ListIterator implements SetIterator{

    private List list;
    private Iterator it;

    public ListIterator(List list) {
        this.list = list;
        reset();
    }

   public boolean hasNext() {
        return it.hasNext();
    }

    public Element next() {
        if (!it.hasNext()) {
            return null;
        }
        return (Element) it.next();        
    }

    public void reset() {
        it = list.iterator();
    }

}
