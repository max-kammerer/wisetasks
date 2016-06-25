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
import ru.spb.ipo.engine.utils.MathOperations;
import ru.spb.ipo.engine.utils.Utils;

public class LayoutSet extends Set{

    private int llength;
    private int tlength;

    private int slength;

    private Set set;

    private long size;
    private long index;

    public LayoutSet() {}

    public LayoutSet(Set source, int llength) {
        this.llength = llength;
        slength = (int)source.getSize();

        set = source;
        
        size = MathOperations.layout(source.getSize(), llength).longValue();
        index = 0;
        tlength = (int) MathOperations.layout(llength,llength).longValue();
    }

    public boolean hasNext() {
        if (index < size) return true;
        /*for (int i = 0; i < combination.length; i++) {
    		if (combination[i] != slength + i - combination.length + 1) return true;
    	}*/        
        return false;
    }

    protected void initSet(Node node) throws TaskDeserializationException, SystemException {
        Node sset = node.getChild("set");
        Set source = Set.generateSet(sset);
        slength = (int)source.getSize();
        String length = node.getAttr("length");
        llength = new Integer(length);

        set = source;

        size = MathOperations.layout(source.getSize(), llength).longValue();
        index = 0;
        tlength = (int) MathOperations.layout(llength,llength).longValue();
    }

    public long getSize() {
        return size;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Element getElement(long index) {
    	throw new UnsupportedOperationException("Operation getElement(long index) isn't supported in CombinationSet class");
    }

     public int getLength() {
        return llength;
    }

    public SetIterator iterator() {
        return new SetIterator() {

            private int [] layout = new int[llength];
            private int [] combination = new int[llength];

            private long index;

            public boolean hasNext() {
                return index < size;                
            }

            public Element next() {
                if (!hasNext()) return null;

                if ((index == 0) || (index % tlength == 0)) {
                    for (int j = 1; j <= llength; j ++) layout[j - 1] = j;
                    newCombination();
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

                index++;
                return layout2Element();                
            }

            public void reset() {
                index = 0;
            }

            private Element layout2Element() {
                Element [] elms  = new Element[llength];
                for (int i = 0; i < llength; i++) {
                    elms[i] = set.getElement(combination[layout[i]-1]);
                }
                return new ContainerElement(elms);
            }

            private void newCombination() {
                if (index == 0) {
                    for (int i = 0; i < llength; i++) {
                        combination[i] = i + 1;
                    }
                } else {
                    boolean f = true;
                    int i = llength - 1;
                    while(f && i >= 0) {
                        if (combination[i] < slength - (llength - 1 - i)) {
                            combination[i]++;
                            f = false;
                        } else i--;
                    }
                    for (int j = i + 1; j < llength; j++)
                        combination[j] = combination[i] + j - i;
                }

            }
                        
        };
    }
}
