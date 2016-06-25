package ru.spb.ipo.generator.word;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ru.spb.ipo.generator.base.ListElement;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;
import ru.spb.ipo.generator.cards.TypeModell;

public class WordSet extends ConstraintPanel {

	private static final long serialVersionUID = 1L;
	
	private List alphabit = new ArrayList();  //  @jve:decl-index=0:
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JLabel jLabel = null;
	private JButton addToken = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField tokenList = null;
	private JComboBox nabor = null;
	private JComboBox wordList = null;
	private JButton jButton = null;

	private JScrollPane jScrollPane = null;
	/**
	 * This is the default constructor
	 */
	public WordSet(BaseGeneratorUI gen) {
		super(gen);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(433, 90);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(null, "\u0418\u0441\u0445\u043e\u0434\u043d\u043e\u0435 \u043c\u043d\u043e\u0436\u0435\u0441\u0442\u0432\u043e", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		this.add(getJPanel(), null);
		this.add(getJPanel1(), null);
		setTokenList();
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("Буква");
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(getJPanel2(), null);
			jPanel.add(getJPanel3(), null);
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
			jLabel2 = new JLabel();
			jLabel2.setText("  Выбранный алфавит   ");			
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BorderLayout());
			jPanel1.add(jLabel2, BorderLayout.WEST);
			jPanel1.add(getJScrollPane(), BorderLayout.CENTER);
			jPanel1.add(getJButton(), BorderLayout.EAST);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			jPanel2 = new JPanel();
			jPanel2.setLayout(flowLayout);
			jPanel2.add(jLabel, null);
			jPanel2.add(getWordList(), null);
			jPanel2.add(getAddToken(), null);
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
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(FlowLayout.RIGHT);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = -1;
			gridBagConstraints.gridy = -1;
			jLabel1 = new JLabel();
			jLabel1.setText("Количество букв в слове");
			jPanel3 = new JPanel();
			jPanel3.setLayout(flowLayout1);
			jPanel3.add(jLabel1, null);
			jPanel3.add(getNabor(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes addToken	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddToken() {
		if (addToken == null) {
			addToken = new JButton();
			addToken.setText("Добавить букву");
			addToken.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ListElement el = (ListElement)getWordList().getSelectedItem();
					if (!alphabit.contains(el)) {
						alphabit.add(el);
						Collections.sort(alphabit, new Comparator() {
							public int compare(Object o1, Object o2) {								
								return o1.toString().compareTo(o2.toString());
							}							
						});
						setTokenList();
					}
				}
			});
		}
		return addToken;
	}

	private void setTokenList() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < alphabit.size(); i++) {
			ListElement e1 = (ListElement)alphabit.get(i);						
			String s = e1.toString();
			sb.append(s);
			if (i < alphabit.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(" }");					
		tokenList.setText(sb.toString());
	}
	
	/**
	 * This method initializes tokenList	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getTokenList() {
		if (tokenList == null) {
			tokenList = new JTextField();
			tokenList.setEditable(false);
			//tokenList.setMinimumSize(new Dimension(100, 40));
//			tokenList.setPreferredSize(new Dimension(200, 25));
		}
		return tokenList;
	}

	/**
	 * This method initializes nabor	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	protected JComboBox getNabor() {
		if (nabor == null) {
			nabor = new JComboBox();
			nabor.setModel(new DefaultComboBoxModel(new String [] {"3", "4", "5", "6", "7", "8", "9", "10"}));
			nabor.setSelectedIndex(2);
		}
		return nabor;
	}

	/**
	 * This method initializes wordList	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getWordList() {
		if (wordList == null) {
			wordList = new JComboBox();			
			wordList.setModel(new TypeModell(WordGenerator.getTokenList()));
			wordList.setSelectedIndex(0);
		}
		return wordList;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Очистить");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					alphabit.clear();
					setTokenList();
				}
			});
		}
		return jButton;
	}

	public List getAlphabit() {
		return alphabit;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			jScrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL, 12, 20, 0, 100));
			Object old = UIManager.get("ScrollBar.width");
			UIManager.put("ScrollBar.width", new Integer(9));
			JScrollBar s = new JScrollBar(JScrollBar.HORIZONTAL, 0, 10, 0, 100);
			jScrollPane.setHorizontalScrollBar(s);
			UIManager.put("ScrollBar.width", old);
			jScrollPane.setViewportView(getTokenList());
			
		}
		return jScrollPane;
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
