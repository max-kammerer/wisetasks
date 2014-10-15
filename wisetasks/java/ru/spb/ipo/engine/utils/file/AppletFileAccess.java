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

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.ImageIcon;
import javax.swing.JApplet;

import ru.spb.ipo.engine.utils.UIUtil;


public class AppletFileAccess extends AbstractFileAccessor  {
		
	private JApplet applet;
		
	private Map lists = new HashMap(); 

	public AppletFileAccess(JApplet applet) {
		this.applet = applet;		
		
		ZipInputStream jar = null;
		try {			
			URL url = new URL(applet.getCodeBase() + applet.getParameter("archive"));			
			Object o = url.getContent();
			jar = new ZipInputStream((InputStream)o);
			ZipEntry entry = null;
			
			while ((entry = jar.getNextEntry()) != null) {				
				String name = entry.getName();
				name = getSafe(name);
                if (name.toLowerCase().endsWith(".xml") && name.contains("/")) {
					int l = name.lastIndexOf("/");
					String folder = name.substring(0, l);
					List list = (List)lists.get(folder);
					if (list == null) {
						list = new ArrayList();
						lists.put(folder, list);
                        System.out.println("foldeer" + folder);
                    }
					list.add(name.substring(l + 1));
				}				
			}			
		} catch (Exception e) {
			UIUtil.showError(applet.getRootPane(), "Не могу загрузить файл: " + e.getMessage(), e);
			e.printStackTrace();			
		} finally {
			if (jar != null) {
				try {
				 jar.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
		
	public InputStream getInputStream(String fileName) throws IOException {
        System.out.println("f " + fileName);
        return applet.getClass().getResourceAsStream(getContext() + getSafe(fileName));
	}
		
	public Object getContent(String fileName) throws IOException {
		return new URL(getContext() + fileName).getContent();
	}
		
	public String [] list(String dirName, final String... filter) throws IOException {
        System.out.println("da" + dirName);
        List list = (List)lists.get(getSafe(dirName));
		return (String [])list.toArray(new String[list.size()]);			
	}	
		
	public ImageIcon getIcon(String fileName) throws IOException {			
		InputStream imageStream = applet.getClass().getResourceAsStream(getSafe("/" + fileName));
		BufferedInputStream bis = new BufferedInputStream(imageStream);
		byte [] bytes = new byte [10000];
		int size = bis.read(bytes);
		bis.close();
		Image image = applet.getToolkit().createImage(bytes, 0, size);
		return new ImageIcon(image);
	}
		
	public String getContext() {			
		return "/";
	}
}
