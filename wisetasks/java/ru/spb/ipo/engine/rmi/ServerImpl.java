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

package ru.spb.ipo.engine.rmi;

import ru.spb.ipo.engine.ProblemsForContest;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.UserAnswerParseException;
import ru.spb.ipo.engine.task.ClientTask;
import ru.spb.ipo.engine.task.JavaTaskFactory;
import ru.spb.ipo.engine.task.TaskFactory;
import ru.spb.ipo.wisetaks2.compile.KotlinTaskFactory;

/**
 * User: Michael Bogdanov
 * Date: 26.03.2005 
 */
public class ServerImpl /*extends UnicastRemoteObject*/ implements Server {

    private ContestProblemAccessor accessor;

    public ServerImpl() throws TaskDeserializationException, SystemException {
        //super();
        TaskFactory factory = new JavaTaskFactory();
        accessor = new LocalContestProblemAccessor(factory, new KotlinTaskFactory());
    }
    
    public ServerImpl(ContestProblemAccessor accessor) {
        this.accessor = accessor;
    }
                          
    public ContestProxy[] getContestList() throws TaskDeserializationException, SystemException {
        return accessor.getContestList();        
    }

    public ProblemProxy[] getProblemList(UserChoice uc) throws TaskDeserializationException, SystemException {                
        return ProblemsForContest.getProblemsPerContest(uc.getContestId(), accessor).getProbelmList();
    }

    public boolean verify(UserChoice uc, ClientTask ct) throws TaskDeserializationException, SystemException, UserAnswerParseException {
        return ProblemsForContest.getProblemsPerContest(uc.getContestId(), accessor).getProblem((int)uc.getProblemId()).verify(ct);
    }

    public ClientTask getProblem(UserChoice uc) throws SystemException, TaskDeserializationException {
		return ProblemsForContest.getProblemsPerContest(uc.getContestId(), accessor).getProblem((int)uc.getProblemId()).getClientTask();
    }

    public UserChoice getUC(UserChoice userChoice, long contestId) {
        UserChoice uc = userChoice;
        uc.setContestId((int)contestId);
        uc.setContestName(accessor.getContestName(contestId));
        return uc;
    }

	public UserChoice authorize(String login, String password)
			throws SystemException {		
		return new UserChoice(login);
	}

	public ContestProblemAccessor getAccessor() {
		return accessor;
	}
	
	public static void main(String[] args) {
		
	}
}
