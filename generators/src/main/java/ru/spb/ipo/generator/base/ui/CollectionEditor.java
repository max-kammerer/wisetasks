package ru.spb.ipo.generator.base.ui;

import javax.swing.*;
import java.awt.*;

public class CollectionEditor extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	/**
	 * This is the default constructor
	 */
	public CollectionEditor() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {		
		this.setContentPane(getJContentPane());
		this.setTitle("Редактор сборников задач");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(657, 432));
		setLocationRelativeTo(null);
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
			jPanel = new TestPanel();
		}
		return jPanel;
	}
	
	public static void main(String[] args) {
		new CollectionEditor().setVisible(true);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
