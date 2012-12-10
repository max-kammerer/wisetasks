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

/**
 * User: Michael Bogdanov
 * Date: 26.03.2005 
 */
public class UserChoice implements java.io.Serializable {

	private String clientId;
	
    private long contestId;

    private long problemId;
    
    private String contestName;

    public UserChoice(String clientId) {
    	this.clientId = clientId;
    }
    
    public String getContestName() {
		return contestName;
	}

	protected void setContestName(String testName) {
		this.contestName = testName;
	}

	public long getContestId() {
        return contestId;
    }

    protected void setContestId(long contestId) {
        this.contestId = contestId;
    }

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }

	public String getClientId() {
		return clientId;
	}
	
	public void clear() {
		contestId = -1;
		problemId = -1;
		contestName = null;
	}
    
} 