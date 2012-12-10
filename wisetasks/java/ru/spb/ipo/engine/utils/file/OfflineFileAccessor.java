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

package ru.spb.ipo.engine.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

public class OfflineFileAccessor extends AbstractFileAccessor  {

	private String codeBase;
	
	public OfflineFileAccessor() {
		
	}
	
	public OfflineFileAccessor(String codeBase) {
		this.codeBase = codeBase;
	}

    public void setCodeBase(String codeBase) {
        this.codeBase = codeBase;
    }

    public InputStream getInputStream(String fileName) throws IOException {
		return new FileInputStream(fileName);				
	}
	
	
	public String [] list(String dirName, final String filter) throws IOException {
        dirName = dirName.replaceAll("\\\\", "/");
		return new File(getContext() +  dirName).list(new FilenameFilter() {
			public boolean accept(File dir, String pathname) {
				return pathname.toLowerCase().endsWith(filter);
			}        	
        });				
	}	
	
	public ImageIcon getIcon(String fileName) throws IOException {
		ImageIcon icon = new ImageIcon(getContext() + fileName);		
		return icon.getImage() == null ? null : icon;
	}
	
	public String getContext() {
		return codeBase + "/";		
	}


	public String[] list(InputStream is) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}	
}
