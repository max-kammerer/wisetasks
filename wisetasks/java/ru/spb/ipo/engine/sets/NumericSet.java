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
import ru.spb.ipo.engine.elements.IntElement;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.Utils;
import ru.spb.ipo.engine.exception.XmlException;

public class NumericSet extends Set {

    private int first;
    private int last;


    private long size;

    public NumericSet (int first, int last) throws XmlException {
        this.first = first;
        this.last = last;
        size  = last - first + 1;
        check();
    }

    public NumericSet() throws XmlException {
        this(0, 0);
    }

    public void initSet (Node node) throws XmlException {
        Integer firstAttr = Utils.getInteger(node.getAttrIfExists("first", null));
        Integer lastAttr = Utils.getInteger(node.getAttrIfExists("last", null));

        if (firstAttr != null) {
            first = firstAttr.intValue();
        }

        if (lastAttr != null) {
            last = lastAttr.intValue();
        }
        
        size  = last - first + 1;
        check();
    }

    private void check() throws XmlException {
        if (first > last) {
            throw new XmlException("NumericSet error: last parameter is lesser than first!");
        }
    }

    public long getSize() {
        return size;
    }

    public Element getElement(long index) {
        return new IntElement((int) (first + (index - 1) % size));      
    }

    public int getLength() {
        return 1;
    }

    public SetIterator iterator() {
        return new SetIterator() {
            
            private boolean beforeFirst = true;

            private int current = first;

            public boolean hasNext() {
                if (beforeFirst && (first <= last)) return true;
                if (current < last) return true;
                return false;
            }

            public Element getCurrent() {
                return new IntElement(current);                
            }

            public Element next() {
                if (!hasNext()) return null;
                if (beforeFirst){
                    beforeFirst = false;
                } else {
                    current++;
                }
                return getCurrent();
            }


            public void reset() {
                current = first;
                beforeFirst = true;
            }
        };
    }
}
