package ru.spb.ipo.generator.base;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FileUtil {

	public static class ListIdEntry {
		
		private String id;
		
		private String name;
		
		private String info;
		
		private ListIdEntry parent;
		
		private int position;
		
		public ListIdEntry getParent() {
			return parent;
		}

		public void setParent(ListIdEntry parent) {
			this.parent = parent;
		}

		public ListIdEntry(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String toString() {			
			//return name + " [" + id + ", "  + info + " ]";
			return name;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}
	}
	
	public static ListIdEntry [] getTestList() {
			    
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    Document doc = null;
	    try {
	        DocumentBuilder parser = factory.newDocumentBuilder();
	        doc = parser.parse(new File("tests.xml"));
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    NodeList nl = doc.getElementsByTagName("test");
	    ListIdEntry [] entries = new ListIdEntry[nl.getLength()];
	    
	    for (int i = 0; i < nl.getLength(); i++) {	    	
	        String title = nl.item(i).getAttributes().getNamedItem("title").getNodeValue();
	        String file = nl.item(i).getAttributes().getNamedItem("folder").getNodeValue();
	        entries[i] = new ListIdEntry(file, title);
	        entries[i].setInfo("" + i);	        
	    }	    
		return entries;
	}
	
	public static String getTestFolder(ListIdEntry testEntry) {
		return testEntry.getId();
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//	    Document doc = null;
//	    try {
//	        DocumentBuilder parser = factory.newDocumentBuilder();
//	        doc = parser.parse(new File("." + File.separator + testEntry.getId()));
//	    } catch (Exception e) {
//	        e.printStackTrace(); 
//	    }
//	    String  folder = doc.getDocumentElement().getAttributeNode("taskDir").getNodeValue();
//	    return "." + File.separator + "tasks" + File.separator + folder + File.separator;
    }
	
	
	public static ListIdEntry createTest(String name, File file) throws Exception {
		String path = file.getAbsolutePath();
		int k = path.indexOf("task");
		String dirName = path.substring(k );
//		if (dirName.startsWith("/")|| dirName.startsWith("\\")) {
//			dirName = dirName.substring(1)
//		}
//		String folder =  dirName;
		//String shortName = folder + ".xml";
		
		//File folderFile = new File("." + File.separator + "tasks" + File.separator + folder + File.separator);
		File folderFile = new File("." + File.separator + dirName);
		if (!folderFile.exists()) {
			folderFile.mkdir();
		}		
//		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream("." + File.separator + "tasks" + File.separator + shortName), "windows-1251");
//		
//		String str = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
//			 "<tasks taskDir=\"" + folder + "\" title=\"" + BaseGenerator.wrapString(name) + "\">\n" +			    
//			"</tasks>";
//		fw.write(str);
//        fw.close();

        //String relativePath = "tasks" + File.separator + folder;
		String relativePath = dirName;		
        String toInsert = "<test title=\"" + BaseGenerator.wrapString(name) + "\" folder=\"" + relativePath + "\"/>";
        
		insertString(getTestListFile(), toInsert, "</tests>");
		return new ListIdEntry(relativePath, name);
	}
	
	public static File getTestListFile() {
		return new File("tests.xml");
	}
	
	public String getTestsFolder() {
		return "tasks";
	}
	
	

//	public static ListIdEntry [] getTasks(ListIdEntry entry) {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        Document rootConfig = null;
//        try {
//        	DocumentBuilder parser = factory.newDocumentBuilder();
//        	rootConfig = parser.parse(new File(entry.getId()));
//         } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//         }
//
//     	NodeList tasks = rootConfig.getElementsByTagName("task");
//     	ListIdEntry [] entries = new ListIdEntry[tasks.getLength()]; 
//     	for (int i = 0; i < tasks.getLength(); i++) {
//     		Node node = tasks.item(i);
//     		String title = node.getAttributes().getNamedItem("title").getNodeValue();
//     		String file = node.getAttributes().getNamedItem("file").getNodeValue();
//     		entries[i] = new ListIdEntry(file, title);
//     		entries[i].setInfo(rootConfig.getDocumentElement().getAttribute("taskDir"));
//     		entries[i].setParent(entry);
//     	}     	
//		return entries;
//	}
	
	public static ListIdEntry [] getTasks(final ListIdEntry entry) {
	
	    File taskFolderFile = new File(entry.getId());
	    final File [] taskFiles = taskFolderFile.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getAbsolutePath().toLowerCase().endsWith(".xml");
			}        	
	    });
	    
	    final ListIdEntry [] entries = new ListIdEntry[taskFiles.length];
	    for (int i = 0; i < taskFiles.length; i++) {
	    	final int index = i; 
			try {
				SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
				
				parser.parse(taskFiles[i], new DefaultHandler() {
	
					public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
						if ("task".equals(qName)) {
							entries[index] = new ListIdEntry(taskFiles[index].getAbsolutePath(), attributes.getValue("title"));
							entries[index].setParent(entry);
						}
					}
				});
			} catch (Exception e){
				e.printStackTrace();				
			}
	    }
    	return entries;
	}	
	//
	
	public static ListIdEntry  renameTest(ListIdEntry entry) {
		return entry;
	}
	
	public static ListIdEntry deleteTest(ListIdEntry entry) {
		return entry;
	}

	public static void insertString(File file, String toInsert, String before) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "windows-1251");        
        StringBuffer sb = new StringBuffer();
        char [] ba = new char [1024];
        int size = 0;
        while (!isr.ready());
        do {	                    	
            size = isr.read(ba);
        	sb.append(ba, 0, size);		                    
        } while (size == ba.length);
        isr.close();
        
        int endList = sb.indexOf(before);
        sb.insert(endList, toInsert + "\n");        
        
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), "windows-1251");					
        fw.write(sb.toString());
        fw.close();	            
	}
	
	public void insertLine() {
//		FileOutputStream fd = new FileOutputStream("");
//		FileChannel fc = fd.getChannel();
//		fc.position(12);		
	}
}
