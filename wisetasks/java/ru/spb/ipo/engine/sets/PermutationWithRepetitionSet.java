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
import ru.spb.ipo.engine.elements.ContainerElement;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.MathOperations;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.SystemException;

import java.util.*;
import java.math.BigInteger;

/**
 * User: mike
 * Date: 10.05.2009
 */
public class PermutationWithRepetitionSet extends Set {
    private int llength;
    private long tlength;

    private int slength;

    List<Element> listOfElements;
    private long size;
    private Map<Element, Integer> elements;

    public PermutationWithRepetitionSet() {}

    public PermutationWithRepetitionSet(Set source) {
        this.llength = (int)source.getSize();
        slength = (int)source.getSize();

        size = MathOperations.layout(source.getSize(), llength).longValue();
        tlength = (int) MathOperations.layout(llength,llength).longValue();
        checkDublicates(source);
    }

    private void checkDublicates(Set source) {
        elements = new HashMap<Element, Integer>();
        listOfElements = new ArrayList<Element>();
        SetIterator it = source.iterator();
        while(it.hasNext()) {
            Element el = (Element)it.next().clone(); ///unclone
            listOfElements.add(el);
            Integer count = elements.get(el);
            if (count == null) {
                count = 1;
            } else {
              count++;
            }
            elements.put(el, count);
        }
        Collections.sort(listOfElements);                                    
        Collection<Integer> values = elements.values();
        BigInteger totalSize = MathOperations.layout(llength,llength);
        for (Integer count : values) {
            totalSize = totalSize.divide(MathOperations.factorial(count));
        }
        size = tlength = totalSize.longValue();
    }


    protected void initSet(Node node) throws TaskDeserializationException, SystemException {
        Node sset = node.getChild("set");
        Set source = Set.generateSet(sset);
        slength = (int)source.getSize();
        llength = slength;

        size = MathOperations.layout(source.getSize(), llength).longValue();
        tlength = (int) MathOperations.layout(llength,llength).longValue();
        checkDublicates(source);
    }

    public long getSize() {
        return size;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Element getElement(long index) {
    	throw new UnsupportedOperationException("Operation getElement(long index) isn't supported in CombinationSet class");
    }

    public Set clone() {
    	throw new UnsupportedOperationException("Operation clone isn't supported in CombinationSet class");
    }

     public int getLength() {
        return llength;
    }

    public SetIterator iterator() {
        return new SetIterator(){
                private int [] layout = new int [(int)llength];
                private Element el;
                private long index;

                public boolean hasNext() {
                    return index < size;
                }

                public Element getCurrent() {
                    return el;
                }

                public Element next() {
                    if (!hasNext()) return null;

                    if ((index == 0) || (index % tlength == 0)) {
                        for (int j = 1; j <= llength; j ++) layout[j - 1] = j;
                    }
                    else {
                        int little = layout.length - 2;

                        while((little >= 0) && (layout[little] > layout[little + 1])) little --;

                        //if (little < 0) return;

                        int big = layout.length - 1;

                        while (layout[little] > layout[big]) big --;

                        int temp =  layout[little];
                        layout[little] = layout[big];
                        layout[big] = temp;

                        for (int i = 1; i < (layout.length - little + 1)/2; i++) {
                            temp = layout[little  + i];
                            layout[little  + i] = layout[layout.length - i];
                            layout[layout.length - i] = temp;
                        }
                    }
                    if (layout2Element()) {
                        index++;
                        return el;
                    } else {
                        return next();
                    }
                }

                public void reset() {
                    index = 0;
                }

                private boolean layout2Element() {
                    Map<Element, Integer> map = new HashMap<Element, Integer>();
                    Element [] elms = new Element [llength];
                    for (int i = 0; i < llength; i++) {
                        Element el = listOfElements.get(layout[i]-1);
                        elms[i] = el;
                        Integer index = map.get(el);
                        if (index != null) {
                            if (index > layout[i]) {
                              return false;
                            }
                        }
                        index = layout[i];
                        map.put(el, index);
                    }
                    this.el = new ContainerElement(elms);
                    return true;
                }
        };
    }
}

