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

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import ru.spb.ipo.client.ui.ClientUI;
import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.functions.Function;
import ru.spb.ipo.engine.functions.ToDigit;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.FractionalNumber;
import ru.spb.ipo.engine.utils.Parser;
import ru.spb.ipo.engine.utils.Utils;
import ru.spb.ipo.engine.sets.SetIterator;

public class ListVerifier extends Verifier {

    private Function af = null;

    private ru.spb.ipo.engine.sets.Set source;
    
    private FractionalNumber normilizer;

    public ListVerifier(Node node) throws SystemException, TaskDeserializationException {
        source = ru.spb.ipo.engine.sets.Set.generateSet(node.getChild("sourceSet").getChild("set"));
        Node verifier = node.getChild("verifier");
        af = Function.generateFunction(verifier.getChild("function"));
        String norm = verifier.getAttrIfExists("normalize", null);
        if (norm != null) {
        	normilizer = new Parser().parse(norm);
        }
    }

    public boolean verify(FractionalNumber [] answers) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss SSS");
//        System.out.println(sdf.format(new Date()));
        //System.out.println("Starting solver ...");
        Set userAnswers = new HashSet();
        for (int i = 0; i < answers.length; i++) {
        	userAnswers.add(answers[i]);
		}
        long size = source.getSize();
        long iteration = 0;
        SetIterator it = source.iterator();
        while(it.hasNext()) {
            Element e = it.next();
            //System.out.println(e);
            
            if (Element.ptrue.equals(af.compute(e))) {
            	//TODO
            	FractionalNumber answ = new FractionalNumber(ToDigit.computeAnswer(e));
            	if (userAnswers.contains(answ)) {
            		userAnswers.remove(answ);
            	} else {
            		return false;
            	}
            }
            setCompleted((float)iteration++/size);            
        }
         
        setCompleted(1.0f);
//        System.out.println(sdf.format(new Date()));
//        //System.out.println("Task solved");
//        System.out.println("user answer = " + answer);
//        System.out.println("System answer = " + count);
//        System.out.println("System answer = " + systemAnswer);
        if (userAnswers.isEmpty()) return true;
        return false;
    }
}