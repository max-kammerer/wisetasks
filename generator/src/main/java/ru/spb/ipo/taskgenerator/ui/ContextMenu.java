package ru.spb.ipo.taskgenerator.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ru.spb.ipo.taskgenerator.config.Config;
import ru.spb.ipo.taskgenerator.config.FunctionElement;
import ru.spb.ipo.taskgenerator.model.Element;
import ru.spb.ipo.taskgenerator.model.KernelElement;
import ru.spb.ipo.taskgenerator.util.Actions;
import ru.spb.ipo.taskgenerator.util.ElementUtil;


public class ContextMenu  extends JPopupMenu {

	private JMenu functions = null;
	private JMenu sets = null;
	private JMenu additions = null;
	private JMenuItem delete = null;
	private JMenuItem parameter = null;
	public ContextMenu() {
        super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.add(getFunctions());
        this.add(getSets());
        this.add(getAdditions());
        this.add(getParameter());
        this.addSeparator();
        this.add(getDelete());                
			
	}

	/**
	 * This method initializes functions	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFunctions() {
		if (functions == null) {
			ActionListener fal = new ItemListener(ElementUtil.E_FUNCTION);
			functions = new JMenu();
			functions.setText("Добавить функцию");
			JMenuItem temp;
			Collection c = Config.getInstance().getFunctions();
//			String [] fns = (String []) c.toArray(new String[c.size()]);
			
	        for (Iterator it = c.iterator(); it.hasNext(); ) {
	        	FunctionElement el = (FunctionElement)it.next();
	        	if ("SEPARATOR".equals(el.getName())) {
	        		functions.addSeparator();	        		
	        	} else {
	        		temp = new JMenuItem(el.getName());
	        		temp.addActionListener(fal);
	        		functions.add(temp);
	        	}	            
	            
	        }
		}
		return functions;
	}

	/**
	 * This method initializes sets	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getSets() {
		if (sets == null) {
			ActionListener sal = new ItemListener(ElementUtil.E_SET);
			sets = new JMenu();
			sets.setText("Добавить множество");
			Collection c = Config.getInstance().getSets().keySet();
			String [] ss = (String []) c.toArray(new String[c.size()]);
			for (int i = 0; i < ss.length; i++) {
				JMenuItem temp = new JMenuItem(ss[i]);
	            temp.addActionListener(sal);
				sets.add(temp);
	        }	        
		}
		return sets;
	}

	/**
	 * This method initializes additions	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getAdditions() {
		if (additions == null) {
			ActionListener aal = new ItemListener(ElementUtil.E_ADDITION);		
			additions = new JMenu();
			additions.setText("Дополнительно");
			Collection c = Config.getInstance().getCommands().keySet();
			String [] commands = (String []) c.toArray(new String[c.size()]);
			for (int i = 0; i < commands.length; i++) {
				JMenuItem temp = new JMenuItem(commands[i]);
	            temp.addActionListener(aal);
				additions.add(temp);
	        }
		}
		return additions;
	}

	public void show(KernelElement element, Component invoker, int x, int y) {		
		// TODO Auto-generated method stub
		int operations = Config.NO_OPERATIONS;
		if (element instanceof Element) {
			ru.spb.ipo.taskgenerator.config.Element configElement = Config.getInstance().getRootModelElement(((Element)element).getName());
			if (configElement != null) {
				operations = configElement.getIntOperations();
			}
		}		
		updateMenu(operations);
		super.show(invoker, x, y);
	}
	
	public void updateMenu(int operations) {
		if ((operations & Config.ADD_FUNCTION) > 0) {
			getFunctions().setEnabled(true);
		} else {
			getFunctions().setEnabled(false);
		}
		
		if ((operations & Config.ADD_SET) > 0) {
			getSets().setEnabled(true);
		} else {
			getSets().setEnabled(false);
		}
		
		if ((operations & Config.ADD_COMMAND) > 0) {
			getAdditions().setEnabled(true);
		} else {
			getAdditions().setEnabled(false);
		}
		
		if ((operations & Config.DELETE) > 0) {
			getDelete().setEnabled(true);
		} else {
			getDelete().setEnabled(false);
		}
		
		if ((operations & Config.ADD_PARAM) > 0) {
			getParameter().setEnabled(true);
		} else {
			getParameter().setEnabled(false);
		}
	}
	
	class ItemListener implements ActionListener {
		
		private int type;
		
		public ItemListener(int type) {
			this.type = type;
		}
		
		public void actionPerformed(ActionEvent e) {
			String kind = ((JMenuItem)e.getSource()).getText();			
			Actions.insert(type, kind);
		}
	
	}

	/**
	 * This method initializes delete	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDelete() {
		if (delete == null) {
			delete = new JMenuItem();			
			delete.setText("Удалить");
			delete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Actions.delete();
				}
			});
		}
		return delete;
	}

	/**
	 * This method initializes parameter	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getParameter() {
		if (parameter == null) {
			ActionListener pal = new ItemListener(ElementUtil.E_PARAMETER);
			parameter = new JMenuItem();
			parameter.setText("Добавить параметр");
			parameter.addActionListener(pal);
		}
		return parameter;
	}
}
