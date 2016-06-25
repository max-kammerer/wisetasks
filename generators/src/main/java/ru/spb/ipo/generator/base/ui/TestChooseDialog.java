package ru.spb.ipo.generator.base.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import ru.spb.ipo.generator.base.FileUtil.ListIdEntry;
import ru.spb.ipo.generator.base.FileUtil;

public class TestChooseDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JScrollPane jScrollPane = null;

	private JList testList = null;

	private JPanel jPanel1 = null;

	private JButton add = null;

	private JButton cancel = null;

	private ListIdEntry result;
	
	/**
	 * @param owner
	 */
	public TestChooseDialog(Frame owner) {
		super(owner, true);
		initialize();
		setLocationRelativeTo(owner);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(367, 252);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Выберите сборник для сохранения");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
			jContentPane.add(getJPanel1(), BorderLayout.SOUTH);
		}
		return jContentPane;
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
			jPanel.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return jPanel;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTestList());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes testList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getTestList() {
		if (testList == null) {
			testList = new JList();
			testList.setModel(TestPanel.createModel(FileUtil.getTestList()));
			testList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			testList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					getAdd().setEnabled(true);
				}
			});
			testList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
						if (getTestList().getSelectedValue() != null) {
							getAdd().doClick();
						}
					}
				}
			});
			
		}
		return testList;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			jPanel1 = new JPanel();
			jPanel1.setLayout(flowLayout);
			jPanel1.add(getAdd(), null);
			jPanel1.add(getCancel(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes add	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAdd() {
		if (add == null) {
			add = new JButton();
			add.setText("Сохранить");
			add.setEnabled(false);
			add.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					result = (ListIdEntry)getTestList().getSelectedValue();
					TestChooseDialog.this.dispose();
				}
			});
		}
		return add;
	}

	/**
	 * This method initializes cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancel() {
		if (cancel == null) {
			cancel = new JButton();
			cancel.setText("Отмена");
			cancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					TestChooseDialog.this.dispose();
				}
			});
		}
		return cancel;
	}

	public ListIdEntry getResult() {
		return result;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
