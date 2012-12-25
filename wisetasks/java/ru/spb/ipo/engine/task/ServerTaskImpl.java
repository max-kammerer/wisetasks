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

package ru.spb.ipo.engine.task;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.Icon;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.UserAnswerParseException;
import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.utils.FileAccessUtil;
import ru.spb.ipo.engine.utils.RationalNumber;
import ru.spb.ipo.engine.utils.Parser;
import ru.spb.ipo.engine.verifiers.Verifier;

public class ServerTaskImpl implements ServerTask {

    private TaskFactory factory;

    private Document root;

    private XmlTask task;

    /**
     * Description parameters
     */
    private ParametersSandbox descriptionSandbox;    
    
    /**
     * Verifier parameters
     */
    private ParametersSandbox verifierSandbox;
    
    private String taskFile;

    private long problemId;
    
    public ServerTaskImpl(String taskFile, long problemId, TaskFactory factory) throws IOException, XmlException, ParserConfigurationException, SAXException {
        this.taskFile = taskFile;
        this.problemId = problemId;

        task = factory.createXmlTask(taskFile);
        descriptionSandbox = new ParametersSandbox(task.getDesriptionParams());
        verifierSandbox = new ParametersSandbox(task.getVerifierParams());
    }

    /**
     * Запускает верификатор, если есть параметры верификации, то сначала генерирует условие задачи.
     * @param ct - объект хранящий информацию о задаче,
     * переданный пользователю и прешедший от него с ответом.
     * @return
     * @throws SystemException 
     * @throws XmlException 
     */
    public boolean verify(ClientTask ct) throws TaskDeserializationException, SystemException, UserAnswerParseException {
        Map currentParams = ct.getGenParams();
        Node math  = Preprocessor.generateTask(task.getMathDescription(), currentParams);
        String userAnswerRaw = ct.getAnswer();

        if (!verifierSandbox.isEmpty()) {
            Iterator iterator = verifierSandbox.getIterator();
            int index = 0;
            while (iterator.hasNext()) {
                Map parameterSet =  (Map)iterator.next();
                boolean bp = checkAnswer(userAnswerRaw, parameterSet, math);
                System.out.println("test " + index + " " + ((!bp) ? "failed" : "approved"));
                index++;

                if (!bp) return false;
            }
            return true;
        } else {
            return checkAnswer(userAnswerRaw, new HashMap(), math);
        }
    }

    private boolean checkAnswer(String userAnswerRaw, Map parameterSet, Node math) throws TaskDeserializationException, UserAnswerParseException, SystemException {
        String userAnswer = Preprocessor.insertParameters2Answer(userAnswerRaw, parameterSet);
        Parser parser = new Parser ();
        RationalNumber[] pvs = parser.parseUserAnswer(userAnswer);

        Node node = task.getNewCode();
        if (node == null) {
            Node localMathDesc = Preprocessor.expandMacros(math, parameterSet);
            return getVerifier(localMathDesc).verify(pvs);
        } else {
            throw new RuntimeException("Not implemented");
        }
    }


    public String getTitle() {
        return task.getTitle();
    }

    public ClientTask getClientTask() throws SystemException {
        Map currentParams = descriptionSandbox.getRandomParameters();

        return getClientTaskWithParameters(currentParams); 
    }

     public ClientTask getClientTaskWithParameters(Map parameters) throws SystemException {
        Node desc = null;
        try {
            desc = Preprocessor.generateTask(task.getDescription(), parameters);
        } catch (TaskDeserializationException e) {
        	throw new SystemException("Couldn't generate task because of task configuration file inconsistency \n" + e.getMessage(), e);
        }        
        
        ClientTaskImpl task = new ClientTaskImpl(getTitle(), desc.getText(), parameters, problemId, verifierSandbox.getParameterNames());
        Node imgs = desc.getChildIfExists(IMAGES);
        if (imgs != null) {
        	List<Node> images = imgs.getChilds(IMAGE);
        	if (images.size() != 0) {
        		List<Icon> icons = new ArrayList<Icon>();
        		String path = "tasks" + File.separator + "imgs" + File.separator;
        		for (Node image: images) {
    				String file = path + image.getText();
    				try {
                        Icon icon = FileAccessUtil.getIcon(file);
    					if (icon == null) {
                            icons.clear();
                            break;
                        }
                        icons.add(icon);
                    } catch (Exception e){
    					e.printStackTrace();
    					icons.clear();
    					break;
    				}
    			}        		
        		task.setImages(icons);
        	}
        }
        return task;
    }

    private Verifier getVerifier(Node md) throws TaskDeserializationException, SystemException {    	
		return Verifier.generateVerifier(md);    	
    }

    private boolean getNewVerifier(Node newCode) throws TaskDeserializationException, SystemException {
        List params = verifierSandbox.getParameterNames();
        String code = newCode.getText();
        return false;
    }
    
}
