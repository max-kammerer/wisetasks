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

import ru.spb.ipo.engine.elements.ContainerElement;
import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.task.Node;

import java.util.List;


/**
 * Decart multiplication of n sets.
 */
public class DecartSet extends Set {

    private Set [] sets;

    private long size;

    private int setsNumber;

    public DecartSet() {}

    public <T extends Set> DecartSet(List<T> sets){
        this.sets = sets.toArray(new Set[sets.size()]);
        setsNumber = this.sets.length;

        size = 1;
        for (int i = 0; i < setsNumber; i++) {
            size *= sets.get(i).getSize();
        }
    }

    public void initSet(Node node) throws TaskDeserializationException, SystemException {
        List<Node> lsets = node.getChilds("set");
        sets = new Set[lsets.size()];
        setsNumber = sets.length;

        for (int i = 0; i < lsets.size(); i++) {
            sets[i] = generateSet(lsets.get(i));
        }

        size = 1;     
        for (int i = 0; i < setsNumber; i++) {
              size *= sets[i].getSize();
        }
    }

    public long getSize() {
        return size;
    }

    public Element getElement(long index) {
        long msize = size;
        long temp = msize;
        Element [] elms = new Element[setsNumber];
        for(int i = 0 ; i < setsNumber; i++) {
            msize = msize / sets[i].getSize();
            temp = (index - 1) / msize ;
            elms [i] = sets[i].getElement(temp + 1);
            index = (index - 1) % msize + 1;
        }

        return new ContainerElement(elms);
    }


    public int getLength() {
       return sets.length;
   }

    public SetIterator iterator() {
        final SetIterator [] iterators = new SetIterator [getLength()];
        for (int i = 0; i < iterators.length; i++) {
           iterators[i] = sets[i].iterator();
        }
        
        return new SetIterator() {

            private boolean beforeFirst = true;
            private Element [] last;
            public boolean hasNext() {
                if (beforeFirst) return true;
                int i = setsNumber -1;
                while (i >= 0) {
                    if (iterators[i].hasNext()) {
                        return true;
                    } else {
                        i--;
                    }
                }
                return false;
            }

            public Element next() {
                Element [] elms = new Element[setsNumber];
                if (beforeFirst) {
                    beforeFirst = false;
                    for (int i = 0; i < iterators.length; i++) {
                        elms[i] = iterators[i].next();
                    }
                    last = elms;
                    return new ContainerElement(elms);
                }
                if (hasNext()) {
                    int i = setsNumber -1;
                    boolean f = true;
                    while (f) {
                        if (iterators[i].hasNext()) {
                            elms[i] = iterators[i].next();
                            f = false;
                        } else {
                            iterators[i].reset();
                            elms[i] = iterators[i].next();
                            i--;
                        }
                    }
                    for (int j = 0; j < i; j++) {
                        elms[j] = last[j];
                    }
                    last = elms;
                    return new ContainerElement(elms);
                } else return null;
            }

            public void reset() {
                for (int i = 0; i < setsNumber; i++){
                    iterators[i].reset();
                }
                beforeFirst = true;
            }
        };
    }
}
