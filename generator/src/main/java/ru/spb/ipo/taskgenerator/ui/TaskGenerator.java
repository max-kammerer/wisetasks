package ru.spb.ipo.taskgenerator.ui;

import org.w3c.dom.Document;
import ru.spb.ipo.taskgenerator.model.KernelElement;
import ru.spb.ipo.taskgenerator.util.Actions;
import ru.spb.ipo.taskgenerator.util.TaskTreeRenderer;
import ru.spb.ipo.taskgenerator.util.XMLEditorKit;
import ru.spb.ipo.taskgenerator.xml.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class TaskGenerator extends JFrame {

	private JScrollPane listScroll = null;
	private JTree modelTree = null;	

	/**
	 * This method initializes 
	 * 
	 */
	public TaskGenerator() {
		super();
		myClient = this;
		initialize();		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		showAtCenter();
		//Config.getInstance();
		setTitleWithFile(null);
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new java.awt.Dimension(810,700));        
        this.setJMenuBar(getMainMenu());
        this.setContentPane(getMainPanel());
        this.setTitle(TITLE);        
	}

	/**
	 * This method initializes listScroll	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getListScroll() {
		if (listScroll == null) {
			listScroll = new JScrollPane();
			listScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,1,0,1));			
			listScroll.setViewportView(getModelTree());			
		}
		return listScroll;
	}

	/**
	 * This method initializes modelTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getModelTree() {
		if (modelTree == null) {
			modelTree = new JTree();			
			Actions.load(new File("emptytask.xml"));			
			modelTree.setCellRenderer(new TaskTreeRenderer());
			modelTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
						public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
							JTree tree = (JTree)e.getSource();
							if (tree.getSelectionCount() == 0) return;  
							//KernelElement element = ((TaskTreeNode)tree.getLastSelectedPathComponent()).getElement();
							Actions.elementSelected(((TaskTreeNode)tree.getLastSelectedPathComponent()));							
						}
					});
			modelTree.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3) {
						Actions.showContextMenu(e);
					}					
				}
				
			});
		}
		return modelTree;
	}
	
	/**
	 * This method initializes xmlPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getXmlPanel() {
		if (xmlPanel == null) {
			KernelElement root = ((TaskTreeNode)getModelTree().getModel().getRoot()).getElement();
			Document doc = Writer.generateXmlTree(root);
			String str = Writer.getString(doc);
			//xmlArea.setText(str);			
			xmlPanel = new JPanel();
			xmlPanel.setLayout(new BorderLayout());
			xmlPanel.add(getCodeScroll(), java.awt.BorderLayout.CENTER);
		}
		return xmlPanel;
	}

	/**
	 * This method initializes mainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());			
			mainPanel.setSize(new java.awt.Dimension(600,700));
			mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
			mainPanel.add(getJSplitPane(), java.awt.BorderLayout.CENTER);
		}
		return mainPanel;
	}


	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new SmartSplitPane();
			//jSplitPane.setDividerLocation(0.5);
			jSplitPane.setDividerLocation(390);
			jSplitPane.setDividerSize(7);						
			jSplitPane.setLeftComponent(getJPanel());
			jSplitPane.setRightComponent(getXmlPanel());
			
		}
		return jSplitPane;
	}

	/**
	 * This method initializes xmlEditor	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	public JEditorPane getXmlEditor() {
		if (xmlEditor == null) {
			xmlEditor = new JEditorPane();			
			xmlEditor.setEditorKitForContentType("text/xml", new XMLEditorKit());
			xmlEditor.setContentType("text/xml");
			xmlEditor.setEditable(false);						
			Actions.updateXml();
		}
		return xmlEditor;
	}

	/**
	 * This method initializes codeScroll	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getCodeScroll() {
		if (codeScroll == null) {
			codeScroll = new JScrollPane();			
			codeScroll.setViewportView(getXmlEditor());
		}
		return codeScroll;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setVgap(5);
			jPanel = new JPanel();
			jPanel.setLayout(borderLayout);
			jPanel.add(getSmartPanel(), java.awt.BorderLayout.SOUTH);
			jPanel.add(getListScroll(), java.awt.BorderLayout.CENTER);			
		}
		return jPanel;
	}

	/**
	 * This method initializes smartPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSmartPanel() {
		if (smartPanel == null) {
			smartPanel = new SmartPanel();
		}
		return smartPanel;
	}

	/**
	 * This method initializes contextMenu	
	 * 	
	 * @return javax.swing.JPopupMenu	
	 */
	public JPopupMenu getContextMenu() {
		if (contextMenu == null) {
			contextMenu = new ContextMenu();
		}
		return contextMenu;
	}

	/**
	 * This method initializes mainMenu	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMainMenu() {
		if (mainMenu == null) {
			mainMenu = new MainMenu();
		}
		return mainMenu;
	}

	public static void main(String [] str) {
		new TaskGenerator();
	}
	
	private void showAtCenter() {
		setLocationRelativeTo(null);		
        setVisible(true);
	}
	
	public void setTitleWithFile(String fileName) {
		if (fileName == null || "".equals(fileName)) {
			setTitle(TITLE + " - " + "Новая задача");			
		} else {
			setTitle(TITLE + " - " + fileName);
		}
	}
	
	private static TaskGenerator myClient;
	private JPanel xmlPanel = null;
	private JPanel mainPanel = null;
	private JSplitPane jSplitPane = null;
	private JEditorPane xmlEditor = null;
	private JScrollPane codeScroll = null;
	private JPanel jPanel = null;
	private JPanel smartPanel = null;
	private JPopupMenu contextMenu = null;  //  @jve:decl-index=0:visual-constraint="472,255"
	private JMenuBar mainMenu = null;
	public static TaskGenerator getFrame() {
		return myClient;
	}
	
	public static ImageIcon ELEMENT_ICON;
	public static ImageIcon FUNCTION_ICON;
	public static ImageIcon SET_ICON;
	public static final String TITLE = "Генератор Задач"; 
	static {
		loadIcons();
	}
	private static void loadIcons() {
		ELEMENT_ICON = new ImageIcon("images/element3.png");
		FUNCTION_ICON = new ImageIcon("images/function.png");
		SET_ICON = new ImageIcon("images/set.png");
	}
	
}  //  @jve:decl-index=0:visual-constraint="-3,-3"
