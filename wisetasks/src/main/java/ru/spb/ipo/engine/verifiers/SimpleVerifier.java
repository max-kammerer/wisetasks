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
import java.text.SimpleDateFormat;

import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.functions.Function;
import ru.spb.ipo.engine.sets.Set;
import ru.spb.ipo.engine.sets.SetIterator;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.RationalNumber;
import ru.spb.ipo.engine.utils.Parser;

public class SimpleVerifier extends Verifier {

    private Function af = null;

    private Set source;
    
    private RationalNumber normilizer;

    public SimpleVerifier(Node node) throws SystemException, TaskDeserializationException {
        source = Set.generateSet(node.getChild("sourceSet").getChild("set"));
        Node verifier = node.getChild(VERIFIER);
        af = Function.generateFunction(verifier.getChild("function"));
        String norm = verifier.getAttrIfExists("normalize", null);
        if (norm != null) {
        	normilizer = new Parser().parse(norm);
        }
    }

    public boolean verify(RationalNumber[] answers) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss SSS");
        long size = source.getSize();
        long iteration = 0;
        BigInteger count = BigInteger.ZERO;
        SetIterator it = source.iterator();
        while(it.hasNext()) {
            Element e = it.next();
            //System.out.println(e);
            if (Element.ptrue.equals(af.compute(e))) {
            	count = count.add(BigInteger.ONE);
            }
            setCompleted((float)iteration++/size);            
        }
        RationalNumber systemAnswer = new RationalNumber(count);
        if (normilizer != null) {
        	systemAnswer = systemAnswer.divide(normilizer);        	
        } 
        setCompleted(1.0f);

        if (systemAnswer.equals(answers[0])) {
            return true;
        }

        return false;
    }
}
