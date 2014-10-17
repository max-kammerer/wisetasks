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

package ru.spb.ipo.engine.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JApplet;

import ru.spb.ipo.engine.utils.file.AppletFileAccess;
import ru.spb.ipo.engine.utils.file.FileAccessor;
import ru.spb.ipo.engine.utils.file.OfflineFileAccessor;

public class FileAccessUtil {

	private static boolean isApplet = false;
	 
	private static FileAccessor accessor = new OfflineFileAccessor(".");
	
	public static void startApplet(JApplet applet) {
		SystemProperties.put(SystemProperties.IS_APPLET, "true");
		isApplet = true;
		accessor = new AppletFileAccess(applet);
	}
	
	public static void start(String codeBase) {
		accessor = new OfflineFileAccessor(codeBase);		
	}
	
	public static InputStream getInputStream(String fileName) throws IOException {
		return accessor.getInputStream(fileName);		
	}
	
	public static String [] list(String dirName, final String... filter) throws IOException {
		return accessor.list(dirName, filter);		
	}	
	
	public static ImageIcon getIcon(String fileName) throws IOException {
		return accessor.getIcon(fileName);
	}
	
	public static boolean isApplet() {
		return isApplet;
	}
	
	public String [] list(InputStream is) throws IOException {
		return accessor.list(is);
	}

}
