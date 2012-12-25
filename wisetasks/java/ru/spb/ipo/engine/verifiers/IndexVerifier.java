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

package ru.spb.ipo.engine.verifiers;

import java.math.BigInteger;

import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.functions.AbstractFunction;
import ru.spb.ipo.engine.functions.Function;
import ru.spb.ipo.engine.sets.Set;
import ru.spb.ipo.engine.sets.SetIterator;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.RationalNumber;

public class IndexVerifier extends Verifier {

    private Function af = null;
    
    private Element element2index = null;

    private Set source;

    public IndexVerifier(Node node) throws SystemException, TaskDeserializationException {
        source = Set.generateSet(node.getChild("sourceSet").getChild("set"));
        af = Function.generateFunction(node.getChild("verifier").getChild("function"));
        element2index = (Element)AbstractFunction.generateAbstractFunction(node.getChild("verifier").getChild("indexingElement").getChild("constElement"));
    }
	
	public boolean verify(RationalNumber[] answers) {
        long size = source.getSize();
        long iteration = 0;
        BigInteger count = BigInteger.ZERO;
        boolean isFound = false;
        SetIterator it = source.iterator();
        while(it.hasNext()) {
            Element e = it.next();
            //System.out.println(e);
            if (Element.ptrue.equals(af.compute(e))) {
            	count = count.add(BigInteger.ONE);
            	if (e.equals(element2index)) {
            		isFound = true;
            		break;
            	}
            }
            setCompleted((float)iteration++/size);            
        }
        setCompleted(1.0f);
        if (isFound) {
        	if (new RationalNumber(count).equals(answers[0])) {
        		return true;
        	}
        }
        return false;		
	}	
}
