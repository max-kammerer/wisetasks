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

import ru.spb.ipo.engine.exception.AuthentificationException;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.UserAnswerParseException;
import ru.spb.ipo.engine.task.ClientTask;

/** 
 * User: Michael Bogdanov
 * Date: 26.03.2005 
 */
public interface Server {

    public ContestProxy[] getContestList() throws TaskDeserializationException, SystemException;

    public ProblemProxy[] getProblemList(UserChoice ucerChoice) throws TaskDeserializationException, SystemException;

    public boolean verify(UserChoice ucerChoice, ClientTask ct) throws TaskDeserializationException, SystemException, UserAnswerParseException;

    public ClientTask getProblem(UserChoice ucerChoice) throws SystemException, TaskDeserializationException;

    public UserChoice getUC(UserChoice uc, long contestId) throws SystemException;
    
    public UserChoice authorize(String login, String password) throws SystemException, AuthentificationException;
}
