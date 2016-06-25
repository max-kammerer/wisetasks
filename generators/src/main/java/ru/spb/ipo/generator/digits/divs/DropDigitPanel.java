package ru.spb.ipo.generator.digits.divs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;
import ru.spb.ipo.generator.numbers.DetailDigitGenerator;
import ru.spb.ipo.generator.numbers.NumberGenerator;

public class DropDigitPanel extends ConstraintPanel {

	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="10,36"
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JLabel jLabel = null;
	private JPanel jPanel3 = null;
	private JLabel jLabel1 = null;
	private JComboBox condBox = null;
	private JPanel jPanel4 = null;
	private JButton addCond = null;
	private JPanel jPanel5 = null;
	private JButton clearCond = null;
	private JPanel leftCond = null;	
	private JFrame parent;
	private JPanel jPanel6 = null;

	/**
	 * This is the default constructor
	 */
	public DropDigitPanel(BaseGeneratorUI parent) {
		super(parent);
		initialize();
		this.parent = parent;
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new TitledBorder("Правило формирования нового числа"));
		panel.add(getJPanel4(), BorderLayout.CENTER);
		panel.add(getJPanel5(), BorderLayout.EAST);
		panel.add(getJPanel(), null);
		panel.add(getJPanel3(), null);
		this.add(panel, null);
		this.add(new DivConditionPanel(getGenerator()));
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.setSize(new Dimension(436, 99));
			jPanel.add(getJPanel4(), BorderLayout.CENTER);
			jPanel.add(getJPanel5(), BorderLayout.EAST);
			//jPanel.add(getJPanel6(), BorderLayout.NORTH);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			
		}
		return jPanel1;
	}

	
    private class DragMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent)e.getSource();
            TransferHandler handler = c.getTransferHandler();            
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());			
			jPanel2.add(jLabel, new GridBagConstraints());
			
		}
		return jPanel2;
	}
	
	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			BorderLayout flowLayout = new BorderLayout(5, 5);			
			jLabel1 = new JLabel();
			jLabel1.setText("Комбинация");
			jLabel1.setToolTipText("Комбинация цифр числа и цифр от 0 до 9");
			jPanel3 = new JPanel();
			jPanel3.setLayout(flowLayout);
			jPanel3.add(jLabel1, BorderLayout.WEST);
			jPanel3.add(getLeftCond(), BorderLayout.CENTER);
		
			//jPanel3.add(getAddCond(), BorderLayout.EAST);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(FlowLayout.CENTER);			
			jPanel4 = new JPanel();
			jPanel4.setLayout(flowLayout1);
			reCreateNumbers(8);
		}
		return jPanel4;
	}
	
	protected void reCreateNumbers(int size) {
		jPanel4.removeAll();
		jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.Y_AXIS));
		
		JPanel x_numbers = new JPanel(new FlowLayout());		
		for (int i = 1; i <= size; i++) {
			JPanel pa = new JPanel();
			pa.setLayout(new BorderLayout(0,0));
			pa.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));				
			JLabel la = new JLabel();
			pa.add(la, BorderLayout.CENTER);
			la.setText(" [" + i + "] ");
			la.setToolTipText( i + " цифра числа. \"Перетащите\" в область ввода");
			la.setTransferHandler(new TransferHandler("text") {
				
				public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
					if (comp == leftCond) {
						return true;
					}
					return false;
				}
				
			});
			MouseListener listener = new DragMouseAdapter();
			la.addMouseListener(listener);
			x_numbers.add(pa);				
		}
		
		JPanel numbers = new JPanel(new FlowLayout());		
		for (int i = 1; i <= 10; i++) {
			JPanel pa = new JPanel();
			pa.setLayout(new BorderLayout(0,0));
			pa.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));				
			JLabel la = new JLabel();
			pa.add(la, BorderLayout.CENTER);
			la.setText("  " + (i % 10) + "  ");
			la.setToolTipText("Цифра " + (i % 10) +  ". \"Перетащите\" в область ввода");
			la.setTransferHandler(new TransferHandler("text") {
				
				public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
					if (comp == leftCond) {
						return true;
					}
					return false;
				}
				
			});
			MouseListener listener = new DragMouseAdapter();
			la.addMouseListener(listener);
			numbers.add(pa);				
		}

		jPanel4.add(numbers);
		jPanel4.add(x_numbers);
		jPanel4.revalidate();
		jPanel4.validate();
		jPanel4.repaint();
	}
	
	/**
	 * This method initializes addCond	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddCond() {
		if (addCond == null) {
			addCond = new JButton();
			addCond.setText("Добавить");
			addCond.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (leftCond.getComponentCount() == 0) {
						JOptionPane.showMessageDialog(DropDigitPanel.this, "Одно из полей пусто!");
					} else {
						int i = leftCond.getComponentCount();
						String [] left = new String [i]; 
						for (int k = 0; k < i; k++) {
							left[k] = ((JLabel)leftCond.getComponent(k)).getText();
							left[k] = left[k].substring(2,3);
						}
						ComplexElement el = new DetailDigitGenerator(left, null, null);
						((DefaultListModel)((NumberGenerator)parent).getFunctionList().getModel()).addElement(el);
					}
				}
			});
		}
		return addCond;
	}

	/**
	 * This method initializes setPanel
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(new FlowLayout());
			jPanel5.add(getClearCond(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes clearCond	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getClearCond() {
		if (clearCond == null) {
			clearCond = new JButton();
			clearCond.setText("Сбросить");
			clearCond.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getLeftCond().removeAll();
					getLeftCond().repaint();					
				}
			});
		}
		return clearCond;
	}

	/**
	 * This method initializes leftCond	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLeftCond() {
		if (leftCond == null) {
			FlowLayout flowLayout2 = new FlowLayout();
			flowLayout2.setAlignment(FlowLayout.LEFT);
			flowLayout2.setVgap(0);
			flowLayout2.setHgap(0);
			leftCond = new JPanel();
			leftCond.setToolTipText("Комбинация цифр числа и цифр от 0 до 9 (\"перетащите\" цифры, указанные выше, сюда)");
			leftCond.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			leftCond.setLayout(flowLayout2);
			leftCond.setPreferredSize(new Dimension(120, 20));			
			leftCond.setDropTarget(new DropTarget(leftCond, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetListener () {

				public void dragEnter(DropTargetDragEvent dtde) {
					// TODO Auto-generated method stub
					
				}

				public void dragExit(DropTargetEvent dte) {
					
				}

				public void dragOver(DropTargetDragEvent dtde) {
					// TODO Auto-generated method stub
					
				}

				public void drop(DropTargetDropEvent dtde) {										
					try {
						int i = leftCond.getComponentCount();
						if (i >= 9) {
							JOptionPane.showMessageDialog(DropDigitPanel.this, "В числе должно быть не более 9 цифр!" , "Слишком много цифр", JOptionPane.WARNING_MESSAGE);
							return;
						}
						dtde.acceptDrop(dtde.getDropAction());
						String object = (String)dtde.getTransferable().getTransferData(dtde.getCurrentDataFlavors()[0]);
						leftCond.add(new JLabel(object));
					} catch (Exception e) {
						e.printStackTrace();
					} finally {						
						leftCond.validate();						
					}
				}

				public void dropActionChanged(DropTargetDragEvent dtde) {
					// TODO Auto-generated method stub
					
				}
				
			}, 
			true, null));

		}
		return leftCond;
	}

	
	public void fillMaps(Map source, Map func, Map task) {
		int i = leftCond.getComponentCount();
		String [] left = new String [i]; 
		for (int k = 0; k < i; k++) {
			left[k] = ((JLabel)leftCond.getComponent(k)).getText();			
			left[k] = left[k].trim() ;			
		}
		func.put("shift", left);
	}

	protected void clear() {
		getClearCond().doClick();
	}

	public boolean checkCanSave() {
		int i = leftCond.getComponentCount();
		if (i == 0) {
			JOptionPane.showMessageDialog(this, "Не задана перестановка!" , "Не задана перестановка", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * This method initializes jPanel6	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setLayout(new FlowLayout());
			for (int i = 1; i <= 10; i++) {
				JPanel pa = new JPanel();
				pa.setLayout(new BorderLayout(0,0));
				pa.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));				
				JLabel la = new JLabel();
				pa.add(la, BorderLayout.CENTER);
				la.setText(" " + (i % 10) + " ");
				la.setToolTipText("\"Перетащите\" номер в область ввода");
				la.setTransferHandler(new TransferHandler("text") {
					
					public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
						if (comp == leftCond) {
							return true;
						}
						return false;
					}
					
				});
				MouseListener listener = new DragMouseAdapter();
				la.addMouseListener(listener);
				jPanel6.add(pa);				
			}
		}
		return jPanel6;
	}
}
