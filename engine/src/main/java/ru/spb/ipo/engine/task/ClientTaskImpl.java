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

import java.util.*;

import javax.swing.Icon;

public class ClientTaskImpl implements ClientTask, java.io.Serializable {
    private String title;
    private String description;
    private Map genParams;
    private String answer;
    
    private List<String> paramButtons;
    
    private List<Icon> images;
    
    private long problemId;

    public ClientTaskImpl(String title, String description, Map genParams, long problemId, List<String> paramButtons) {
        this.description = description;
        this.genParams = Collections.unmodifiableMap(genParams);
        this.title = title;
        this.problemId = problemId;
        this.paramButtons = paramButtons;

        if (this.paramButtons == null) {
            this.paramButtons = Collections.emptyList();
        }
    }


    public String getDescription() {
        return description;
    }

    public Map getGenParams() {
        return genParams;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }
    
    public List<String> getParameterButtons(){
    	return paramButtons;    	
    }

	public List<Icon> getImages() {
		return images;
	}

	public void setImages(List<Icon> images) {
		this.images = images;
	}


	public long getProblemId() {
		return problemId;
	}
}
