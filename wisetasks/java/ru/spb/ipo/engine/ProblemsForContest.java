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

package ru.spb.ipo.engine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.rmi.ContestProblemAccessor;
import ru.spb.ipo.engine.rmi.ProblemProxy;
import ru.spb.ipo.engine.task.ServerTask;
import ru.spb.ipo.engine.utils.FileAccessUtil;
import ru.spb.ipo.engine.utils.Utils;

/**
 * Author: Michael.Bogdanov
 * Date: 02.12.2004
 */
public class ProblemsForContest {

    private ContestProblemAccessor accessor;

    private String mainConfig;

    private Node rootTasks;

    private DocumentBuilder parser;

    private Document rootConfig;

    private Node [] tasks;

    //map
    private Map<Long, ServerTask> serverProblems;
    
    private ProblemProxy[] problemProxies;
    
    private String taskDir;

    private static Map contestMap = new TreeMap();
    

    private long contestId;

    protected ProblemsForContest(String mainConfig, ContestProblemAccessor accessor) throws XmlException, Exception {
        this.accessor = accessor;
        this.mainConfig = mainConfig;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        parser = factory.newDocumentBuilder();
        rootConfig = parser.parse(FileAccessUtil.getInputStream(mainConfig));         

        rootTasks = Utils.getChild(rootConfig, "tasks");

        tasks = Utils.getNodes(rootTasks, "task");

        serverProblems = new HashMap<Long, ServerTask>();

        taskDir = rootTasks.getAttributes().getNamedItem("taskDir").getNodeValue();
    }
    
    
    protected ProblemsForContest(long contestId, ContestProblemAccessor accessor) throws TaskDeserializationException, SystemException {
        this.contestId = contestId;
        this.accessor = accessor;
        try {
        	problemProxies = accessor.getProblemList(contestId);
        } catch (IOException e) {
        	throw new TaskDeserializationException("Ошибка при составлении списка задач для " + accessor.getContestName(contestId) + ": \n" + e.getMessage());
		}

        serverProblems = new HashMap<Long, ServerTask>();
    }

    public ProblemProxy[] getProbelmList() {
        return problemProxies;
    }

    public ServerTask getProblem(long id) throws SystemException, TaskDeserializationException {
    	ServerTask task = serverProblems.get(id);
    	if (task != null) {
    		return task;
    	} else {
    		task = accessor.getProblem(contestId, id);
    		serverProblems.put(id, task);
    	}
        return task;
    }

    public static ProblemsForContest getProblemsPerContest(long contestId, ContestProblemAccessor accessor) throws TaskDeserializationException, SystemException {
        ProblemsForContest problems = (ProblemsForContest) contestMap.get(contestId);
        if (problems == null) {
        	synchronized (ProblemsForContest.class) {
        		problems = (ProblemsForContest) contestMap.get(contestId);
				if (problems == null) {
					problems = new ProblemsForContest(contestId, accessor);
		            contestMap.put(contestId, problems);
				}
			}            
        }
        return problems;
    }

}
