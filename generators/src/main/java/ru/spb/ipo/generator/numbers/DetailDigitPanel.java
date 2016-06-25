package ru.spb.ipo.generator.numbers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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

public class DetailDigitPanel extends JPanel {

	private static final long serialVersionUID = 1L;
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
	private JPanel rightCond = null;
	private JFrame parent;

	/**
	 * This is the default constructor
	 */
	public DetailDigitPanel(JFrame parent) {
		super();
		initialize();
		this.parent = parent;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(470, 150);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(null, "\u041e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 \u0441 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430\u043c\u0438", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		this.add(getJPanel(), null);
		this.add(getJPanel3(), null);
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
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			jLabel1 = new JLabel();
			jLabel1.setText("—умма элементов");
			jLabel1.setToolTipText("Ёлемент на указанном месте или их сумма (\"перетащите\" номер в область ввода)");
			jPanel3 = new JPanel();
			jPanel3.setLayout(flowLayout);
			jPanel3.add(jLabel1, null);
			jPanel3.add(getLeftCond(), null);
			jPanel3.add(getCondBox(), null);
			jPanel3.add(getRightCond(), null);
			jPanel3.add(getAddCond(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes condBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCondBox() {
		if (condBox == null) {
			condBox = new JComboBox();
			condBox.setModel(new DefaultComboBoxModel(new String [] {"<", "=", ">"}));			
		}
		return condBox;
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
			for (int i = 1; i <= 10; i++) {
				JPanel pa = new JPanel();
				pa.setLayout(new BorderLayout(0,0));
				pa.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));				
				JLabel la = new JLabel();
				pa.add(la, BorderLayout.CENTER);
				la.setText(" [" + i + "] ");
				la.setToolTipText("\"ѕеретащите\" номер в область ввода");
				la.setTransferHandler(new TransferHandler("text") {
					
					public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
						if (comp == leftCond || comp == rightCond) {
							return true;
						}
						return false;
					}
					
				});
				MouseListener listener = new DragMouseAdapter();
				la.addMouseListener(listener);
				jPanel4.add(pa);				
			}
		}
		return jPanel4;
	}
	
	/**
	 * This method initializes addCond	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddCond() {
		if (addCond == null) {
			addCond = new JButton();
			addCond.setText("ƒобавить");
			addCond.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (leftCond.getComponentCount() == 0 || rightCond.getComponentCount() == 0) {
						JOptionPane.showMessageDialog(DetailDigitPanel.this, "ќдно из полей пусто!");
					} else {
						int i = leftCond.getComponentCount();
						String [] left = new String [i]; 
						for (int k = 0; k < i; k++) {
							left[k] = ((JLabel)leftCond.getComponent(k)).getText();
							left[k] = left[k].substring(2,3);
						}
						
						i = rightCond.getComponentCount();
						String [] right = new String [i]; 
						for (int k = 0; k < i; k++) {
							right[k] = ((JLabel)rightCond.getComponent(k)).getText();
							right[k] = right[k].substring(2,3);
						}
						ComplexElement el = new DetailDigitGenerator(left, right, (String)getCondBox().getSelectedItem());
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
			clearCond.setText("ќчистить списки");
			clearCond.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getLeftCond().removeAll();
					getLeftCond().repaint();
					
					getRightCond().removeAll();
					getRightCond().repaint();
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
			leftCond.setToolTipText("Ёлемент на указанном месте или их сумма (\"перетащите\" номер в область ввода)");
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

	/**
	 * This method initializes rightCond	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRightCond() {
		if (rightCond == null) {
			FlowLayout flowLayout3 = new FlowLayout();
			flowLayout3.setAlignment(FlowLayout.LEFT);
			flowLayout3.setVgap(0);
			flowLayout3.setHgap(0);
			rightCond = new JPanel();
			rightCond.setToolTipText("Ёлемент на указанном месте или их сумма (\"перетащите\" номер в область ввода)");
			rightCond.setLayout(flowLayout3);
			rightCond.setPreferredSize(new Dimension(120, 20));
			rightCond.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			rightCond.setDropTarget(new DropTarget(rightCond, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetListener () {

				public void dragEnter(DropTargetDragEvent dtde) {
					
				}

				public void dragExit(DropTargetEvent dte) {
					// TODO Auto-generated method stub
					
				}

				public void dragOver(DropTargetDragEvent dtde) {
					// TODO Auto-generated method stub
					
				}

				public void drop(DropTargetDropEvent dtde) {										
					try {
						dtde.acceptDrop(dtde.getDropAction());
						String object = (String)dtde.getTransferable().getTransferData(dtde.getCurrentDataFlavors()[0]);
						rightCond.add(new JLabel(object));
					} catch (Exception e) {
						e.printStackTrace();
					} finally {						
						rightCond.validate();						
					}
				}

				public void dropActionChanged(DropTargetDragEvent dtde) {
					// TODO Auto-generated method stub
					
				}
				
			}, 
			true, null));

		}
		return rightCond;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
