package ru.spb.ipo.taskgenerator.util;

import java.awt.Color;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

public class XMLEditorKit extends StyledEditorKit {
	 
    /**
     * Constructor.
     */
    public XMLEditorKit() {
        super();
    }//XMLEditorKit()
    
    /**
     * Overwrites super.
     */
    public String getContentType() {
        return "text/xml";
    }//getContentType()
    
    /**
     * Overwrites super.
     */
    public Document createDefaultDocument() { 
        return new XMLDocument(); 
    }//createDefaultDocument()
    
    class XMLDocument extends DefaultStyledDocument {
    	 
    	 
        /** Attributes for XML element tag. */
        private MutableAttributeSet tag = null;
        /** Attributes for element value. */
        private MutableAttributeSet elementValue = null;
        
        private MutableAttributeSet attrName = null;
        
        private MutableAttributeSet attrValue = null;
        
        private MutableAttributeSet comment = null;
     
        /**
         * Constructor.
         */
        public XMLDocument() {
            tag = new SimpleAttributeSet();
            StyleConstants.setForeground(tag, Color.BLUE);
            
            elementValue = new SimpleAttributeSet();
            StyleConstants.setForeground(elementValue, Color.BLACK);
            
            attrValue = new SimpleAttributeSet();
            StyleConstants.setForeground(attrValue, new Color(30, 140, 30));
            
            attrName = new SimpleAttributeSet();
            StyleConstants.setForeground(attrName, Color.BLUE);
            
            comment = new SimpleAttributeSet();
            //StyleConstants.setForeground(comment, new Color(200, 30, 30));
            StyleConstants.setForeground(comment, Color.GRAY);
            
        }
     
        /**
         * Override AbstractDocument.
         */
        public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
            super.insertString(offset, str, a);
            colorize(offset, str.length());
            
        }//insertString()
     
        /**
         * Override AbstractDocument.
         */
        public void remove(int offset, int length) throws BadLocationException {
            super.remove(offset, length);
            colorize(getCharacterElement(offset).getStartOffset(), getCharacterElement(offset).getEndOffset());
        }//remove()
     
        /**
         * Called when document content changed to revalidate syntax highlightning.
         * Only works correctly for well-formed XML.
         */
        private void colorize(int offset, int length) throws BadLocationException {
            String text = getText(0, getLength());
            
            int startIndex = offset;
            int endIndex = offset + length + 1;
            int openIndex = 0;
            int closeIndex = 0;
            boolean outOfTag = true;
            
            while (startIndex <= endIndex) {
                openIndex  = text.indexOf('<', startIndex); //next '<'
                closeIndex = text.indexOf('>', startIndex); //next '>'
                
                if (outOfTag) {
                    if (openIndex >= 0) {
                        setCharacterAttributes(startIndex, openIndex - startIndex, elementValue, false);
                        startIndex = openIndex;
                        outOfTag = false;
                    } else {
                        setCharacterAttributes(startIndex, endIndex + 1, elementValue, false);
                        break;
                    }
                } else { //withinTag
                    if (closeIndex >= 0) {
                    	boolean isComment = false; 
                    	try {
                    		isComment = "!--".equals(text.substring(startIndex + 1, startIndex + 1 + 3)) && 
                    		"--".equals(text.substring(closeIndex - 2, closeIndex));
                    	} catch (Exception e){
                    		isComment = false;
                    	}
                    	
                    	if (!isComment) {
	                        setCharacterAttributes(startIndex, closeIndex - startIndex + 1, tag, false);
	                        colorizeAttributes(text, startIndex, closeIndex - startIndex + 1);
                    	} else {
                    		setCharacterAttributes(startIndex, closeIndex - startIndex + 1, comment, false);
                    	}
                        startIndex = closeIndex + 1;
                        outOfTag = true;
                    } else {
                        setCharacterAttributes(startIndex, endIndex + 1, tag, false);
                        colorizeAttributes(text, startIndex, endIndex + 1);
                        break;
                        }
                    }
                }//next occurence
        }
        
        private void colorizeAttributes(String text, int elementStart, int length) {
        	text  = text.substring(0, elementStart + length);        	
        	int occurence = text.indexOf("\"", elementStart);
        	String str = text.substring(elementStart, elementStart + length);
        	while (occurence > 0) {
        		int nextOccurence = text.indexOf("\"", occurence + 1);
        		if (nextOccurence > -1) {
        			setCharacterAttributes(occurence, nextOccurence + 1 - occurence, attrValue, false);
        			occurence = text.indexOf("\"", nextOccurence + 1);
        		} else {
        			occurence = -1;
        		}        	
        	}
        }
     
    }

}//XMLEditorKit
 


