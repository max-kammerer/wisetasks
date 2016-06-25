package ru.spb.ipo.generator.base.ui;

import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.FileUtil;
import ru.spb.ipo.generator.basket.BasketGenerator;
import ru.spb.ipo.generator.cards.CardGenerator;
import ru.spb.ipo.generator.colors.ColorsGenerator;
import ru.spb.ipo.generator.digits.divs.DivGenerator;
import ru.spb.ipo.generator.digits.mods.ModGenerator;
import ru.spb.ipo.generator.equation.EquationGenerator;
import ru.spb.ipo.generator.numbers.NumberGenerator;
import ru.spb.ipo.generator.word.IndexWordGenerator;
import ru.spb.ipo.generator.word.WordGenerator;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * UI skeleton for editors.
 *
 * You must implement:
 *      getFunctionPanel()
 *      getSetPanel()
 *      createGenerator(...)
 *      getHelpString()
 */
public abstract class BaseGeneratorUI extends JFrame {

    private JPanel jContentPane = null;

    private JPanel leftPanel = null;

    private JPanel taskTitlePanel = null;

    private JPanel mathDescriptionPanel = null;

    //set and functions
    private JPanel mathDescriptionWidgetPanel = null;

    private JPanel functionListPanel = null;

    protected ConstraintPanel setPanel = null;

    private JLabel taskTitleLabel = null;

    private JTextField taskTitle = null;

    private JPanel rightPanel = null;

    protected JButton generateDescriptionButton = null;

    private JPanel generateDescriptionButtonPanel = null;

    protected JTextArea taskDesc = null;

    private JScrollPane jScrollPane = null;

    private JScrollPane jScrollPane1 = null;

    protected JList functionList = null;

    private JMenuItem saveItem = null;

    private JFileChooser fileChooser;  //  @jve:decl-index=0:visual-constraint="689,457"

    private JFileChooser imageChooser;

    private JPanel removeAllFunctionButtonPanel = null;

    private JButton removeAllFuntionsButton = null;

    private JMenuItem aboutProgramItem = null;

    protected ConstraintPanel functionPanel = null;

    private JPanel taskDescriptionPanel = null;

    private JButton save2DefaultButton = null;

    private JPanel save2DefaultButtonPanel = null;

    private JPanel mainPanel = null;

    private JPanel images = null;

    private JPanel imageButtonsPanel = null;

    private JPanel imagePanel = null;

    private JButton clearImagesButton = null;

    private JButton addImageButton = null;

    private JScrollPane jScrollPane2 = null;

    private JPanel imageListPanel = null;

    private ArrayList<String> imagesList = new ArrayList<String>();  //  @jve:decl-index=0:

    private JPopupMenu conditionPopup = null;  //  @jve:decl-index=0:visual-constraint="951,232"

    private JMenuItem deleteConditionButton = null;

    private File prevSave = null;  //  @jve:decl-index=0:

    //menu
    private JMenuBar mainMenuBar = null;

    private JMenu fileMenu = null;

    private JMenuItem newProblemItem = null;

    private JMenuItem saveToPrev = null;

	private JMenuItem scale = null;

    private JMenu imagesMenu = null;

    private JMenuItem exitItem = null;

    private JMenu infoMenu = null;

    private JMenu constructorMenu = null;

    private Object [] [] constructors = new Object[] [] {
            {"Редактор \"Колода карт\"", CardGenerator.class},
            {"Редактор \"Слова над конечным алфавитом\"", WordGenerator.class},
            {"Редактор \"Упорядоченные числовые наборы\"", NumberGenerator.class},
            {"Редактор \"Индексация слов\"", IndexWordGenerator.class},
            {"Редактор \"Количество решений уравнения\"", EquationGenerator.class},
            {"Редактор \"Шары и урны\"", BasketGenerator.class},
            {"Редактор \"Задачи о раскрасках\"", ColorsGenerator.class},
            {"separator", "separator"},
            {"Редактор \"Задачи на делимость\"", DivGenerator.class},
            {"Редактор \"Арифметика остатков\"", ModGenerator.class},
            {"separator", "separator"},
            {"Редактор Сборников", CollectionEditor.class}
    };

    public BaseGeneratorUI() {
        super();
    }

    /**
     * This method initializes taskTitlePanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getTaskTitlePanel() {
        if (taskTitlePanel == null) {
            taskTitleLabel = new JLabel();
            taskTitleLabel.setText("Название  ");
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = GridBagConstraints.VERTICAL;
            gridBagConstraints.gridy = -1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridx = -1;
            taskTitlePanel = new JPanel();
            taskTitlePanel.setLayout(new BorderLayout());
            taskTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            taskTitlePanel.add(taskTitleLabel, BorderLayout.WEST);
            taskTitlePanel.add(getTaskTitle(), BorderLayout.CENTER);
        }
        return taskTitlePanel;
    }

    /**
     * This method initializes mathDescriptionPanel
     *
     * @return javax.swing.JPanel
     */
    protected JPanel getMathDescriptionPanel() {
        if (mathDescriptionPanel == null) {
            mathDescriptionPanel = new JPanel();
            mathDescriptionPanel.setLayout(new BorderLayout());
            mathDescriptionPanel.setBorder(BorderFactory.createTitledBorder(null, "\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            mathDescriptionPanel.add(getMathDescriptionWidgetPanel(), BorderLayout.NORTH);
            mathDescriptionPanel.add(getFunctionListPanel(), BorderLayout.CENTER);
        }
        return mathDescriptionPanel;
    }

    protected BaseGenerator getGenerator() {
        Map<String, Object> source = new HashMap<String, Object>();
        Map<String, Object> func = new HashMap<String, Object>();
        Map<String, Object> task = new HashMap<String, Object>();

        task.put("title", taskTitle.getText());
        task.put("description", taskDesc.getText());
        task.put("images", getImagesList());

        DefaultListModel model = (DefaultListModel) functionList.getModel();
        int s = model.getSize();
        StringBuilder funcDesc = new StringBuilder();
        StringBuilder inlineDesc = new StringBuilder();
        for (int i = 0; i < s; i++) {
            ComplexElement pe = (ComplexElement) model.get(i);
            funcDesc.append(pe.generateXml());
            inlineDesc.append(" " + pe.toDescription() + (i < s -1 ? "," : "" ));
        }
        task.put("inlineDesc", inlineDesc.toString());
        func.put("function", funcDesc.toString());

        fillParameters(source, func, task);
        return createGenerator(source, func, task);
    }

    protected void fillParameters(Map<String, Object> source, Map<String, Object> func, Map<String, Object> task) {
        getFunctionPanel().fillMaps(source, func,  task);
        getSetPanel().fillMaps(source, func,  task);
    }

    protected abstract BaseGenerator createGenerator(Map source, Map func, Map task);

    /**
     * This method initializes mathDescriptionWidgetPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getMathDescriptionWidgetPanel() {
        if (mathDescriptionWidgetPanel == null) {
            mathDescriptionWidgetPanel = new JPanel();
            mathDescriptionWidgetPanel.setLayout(new BoxLayout(getMathDescriptionWidgetPanel(), BoxLayout.Y_AXIS));
            mathDescriptionWidgetPanel.add(getSetPanel(), null);
//            mathDescriptionWidgetPanel.add(getJPanel11(), null);
            mathDescriptionWidgetPanel.add(getFunctionPanel(), null);
        }
        return mathDescriptionWidgetPanel;
    }

    /**
     * This method initializes functionListPanel
     *
     * @return javax.swing.JPanel
     */
    protected JPanel getFunctionListPanel() {
        if (functionListPanel == null) {
            functionListPanel = new JPanel();
            functionListPanel.setLayout(new BorderLayout());
            functionListPanel.add(getJScrollPane1(), BorderLayout.CENTER);
            functionListPanel.add(getRemoveAllFunctionButtonPanel(), BorderLayout.SOUTH);
        }
        return functionListPanel;
    }

    /**
     * This method initializes setPanel
     *
     * @return javax.swing.JPanel
     */
    protected ConstraintPanel getSetPanel() {
        if (setPanel == null) {
            setPanel = new ConstraintPanel(this);
        }
        return setPanel;
    }

    /**
     * This method initializes taskTitle
     *
     * @return javax.swing.JTextField
     */
    protected JTextField getTaskTitle() {
        if (taskTitle == null) {
            taskTitle = new JTextField();
        }
        return taskTitle;
    }

    /**
     * This method initializes mainMenuBar
     *
     * @return javax.swing.JMenuBar
     */
    private JMenuBar getMainMenuBar() {
        if (mainMenuBar == null) {
            mainMenuBar = new JMenuBar();
            mainMenuBar.add(getFileMenu());
            mainMenuBar.add(getConstructorMenu());
			mainMenuBar.add(getImagesMenu());
            mainMenuBar.add(getInfoMenu());
        }
        return mainMenuBar;
    }

	private JMenu getImagesMenu() {
		if (imagesMenu == null) {
			imagesMenu = new JMenu();
			imagesMenu.setText("Избражения");
			imagesMenu.add(getScale());
		}
		return imagesMenu;
	}

    /**
     * This method initializes fileMenu
     *
     * @return javax.swing.JMenu
     */
    private JMenu getFileMenu() {
        if (fileMenu == null) {
            fileMenu = new JMenu();
            fileMenu.setText("Файл");
            fileMenu.add(getNewProblemItem());
            fileMenu.add(getSaveToPrev());
            fileMenu.add(getSaveItem());
            fileMenu.addSeparator();
            fileMenu.add(getExitItem());
        }
        return fileMenu;
    }

    /**
     * This method initializes newProblemItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getNewProblemItem() {
        if (newProblemItem == null) {
            newProblemItem = new JMenuItem();
            newProblemItem.setText("Новая задача");
            newProblemItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    clear();
                }
            });
        }
        return newProblemItem;
    }

    protected void clear() {
        taskDesc.setText("");
        taskTitle.setText("");
        functionList.setModel(new DefaultListModel());
        getImageListPanel().removeAll();
        imagesList.clear();
        setActiveSave(null);
    }

    /**
     * This method initializes infoMenu
     *
     * @return javax.swing.JMenu
     */
    private JMenu getInfoMenu() {
        if (infoMenu == null) {
            infoMenu = new JMenu();
            infoMenu.setText("Справка");
            infoMenu.add(getAboutProgramItem());
        }
        return infoMenu;
    }

    /**
     * This method initializes rightPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getRightPanel() {
        if (rightPanel == null) {
            rightPanel = new JPanel();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.setPreferredSize(getRightPanelDimension());
            rightPanel.add(getImages(), BorderLayout.NORTH);
            rightPanel.add(getTaskDescriptionPanel(), BorderLayout.CENTER);
            rightPanel.add(getSave2DefaultButtonPanel(), BorderLayout.SOUTH);
        }
        return rightPanel;
    }

    protected Dimension getRightPanelDimension() {
        return new Dimension(435, 190);
    }

    /**
     * This method initializes generateDescriptionButton
     *
     * @return javax.swing.JButton
     */
    protected JButton getGenerateDescriptionButton() {
        if (generateDescriptionButton == null) {
            generateDescriptionButton = new JButton();
            generateDescriptionButton.setText("Сгенерировать условие");
            generateDescriptionButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    taskDesc.setText(getGenerator().generateDescription());
                }
            });
        }
        return generateDescriptionButton;
    }

    /**
     * This method initializes generateDescriptionButtonPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getGenerateDescriptionButtonPanel() {
        if (generateDescriptionButtonPanel == null) {
            FlowLayout flowLayout3 = new FlowLayout();
            flowLayout3.setAlignment(FlowLayout.RIGHT);
            generateDescriptionButtonPanel = new JPanel();
            generateDescriptionButtonPanel.setLayout(flowLayout3);
            generateDescriptionButtonPanel.add(getGenerateDescriptionButton(), null);
        }
        return generateDescriptionButtonPanel;
    }

    /**
     * This method initializes taskDesc
     *
     * @return javax.swing.JTextArea
     */
    protected JTextArea getTaskDesc() {
        if (taskDesc == null) {
            taskDesc = new JTextArea();
            taskDesc.setRows(4);
            taskDesc.setLineWrap(true);
            taskDesc.setWrapStyleWord(true);
        }
        return taskDesc;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(getTaskDesc());
        }
        return jScrollPane;
    }

    /**
     * This method initializes jScrollPane1
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane1() {
        if (jScrollPane1 == null) {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setViewportView(getFunctionList());
        }
        return jScrollPane1;
    }

    /**
     * This method initializes functionList
     *
     * @return javax.swing.JList
     */
    public JList getFunctionList() {
        if (functionList == null) {
            functionList = new JList();
            functionList.setModel(new DefaultListModel());
            functionList.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3) {
                        int [] obj = getFunctionList().getSelectedIndices();
                        if (obj != null && obj.length != 0) {
                            getConditionPopup().show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
            });
        }
        return functionList;
    }


    protected boolean checkCanSave() {
        if (isEmpty(taskTitle.getText())) {
            JOptionPane.showMessageDialog(BaseGeneratorUI.this, "Не указано название задачи!" , "Не указано название задачи", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (isEmpty(taskDesc.getText())) {
            JOptionPane.showMessageDialog(BaseGeneratorUI.this, "Условие задачи не задано" , "Условие задачи не задано", JOptionPane.WARNING_MESSAGE);
            return false;
        }

//		if (getFunctionList().getModel().getSize() == 0) {
//			JOptionPane.showMessageDialog(CardGenerator.this, "Не выбрано ни одного условия!" , "Не выбрано ни одного условия!", JOptionPane.WARNING_MESSAGE);
//			return false;
//		}

        return true;
    }

    /**
     * This method initializes saveItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getSaveItem() {
        if (saveItem == null) {
            saveItem = new JMenuItem();
            saveItem.setText("Сохранить в сборник...");
            saveItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (checkCanSave()) {

//						String str = getGenerator().generateXml();
//
//						int result = getFileChooser().showSaveDialog(CardGenerator.this);
//			            if (JFileChooser.APPROVE_OPTION == result) {
//			                File file = getFileChooser().getSelectedFile();
//			                if (!file.getName().toLowerCase().endsWith(".xml")) {
//			                	file = new File(file.getAbsolutePath() + ".xml");
//			                }

                        TestChooseDialog dialog = new TestChooseDialog(BaseGeneratorUI.this);
                        dialog.setVisible(true);
                        FileUtil.ListIdEntry res = dialog.getResult();
//                        System.out.println("res" + res );
                        if (res != null) {
                            try {
                                String str = getGenerator().generateXml();
                                String folder = FileUtil.getTestFolder(res) + "/";
                                JFileChooser fc = getFileChooser();
                                fc.setCurrentDirectory(new File(folder));
                                int result = fc.showSaveDialog(BaseGeneratorUI.this);
                                if (JFileChooser.APPROVE_OPTION == result) {
                                    File file = getFileChooser().getSelectedFile();
                                    if (!file.getName().toLowerCase().endsWith(".xml")) {
                                        file = new File(file.getAbsolutePath() + ".xml");
                                    }
                                    saveToFile(str, file);
                                } else {
                                    return;
                                }
//			                    FileUtil.insertString(new File(res.getId()),  "<task title=\"" +  BaseGenerator.wrapString(taskTitle.getText()) + "\" file=\"" + shortName + "\"/>", "</tasks>");
                            } catch (Exception e1) {
                                showError("Не могу сохранить задачу: \n" + e1.getMessage(), "Ошибка при сохранении", e1);
                                return;
                            }
                            JOptionPane.showMessageDialog(BaseGeneratorUI.this, "Задача успешно сохранена.", "Задача успешно сохранена", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
        }
        return saveItem;
    }

    public void saveToFile(String str, File file) throws Exception {
        OutputStreamWriter fw = null;
        try {
            if (!file.getName().toLowerCase().endsWith(".xml")) {
                file = new File(file.getAbsolutePath() + ".xml");
            }
            fw = new OutputStreamWriter(new FileOutputStream(file), "windows-1251");
            fw.write(str);
            fw.close();
            setActiveSave(file);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /**
     * This method initializes exitItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getExitItem() {
        if (exitItem == null) {
            exitItem = new JMenuItem();
            exitItem.setText("Выход");
            exitItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.exit(0);
                }
            });
        }
        return exitItem;
    }

    /**
     * This method initializes removeAllFunctionButtonPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getRemoveAllFunctionButtonPanel() {
        if (removeAllFunctionButtonPanel == null) {
            FlowLayout flowLayout4 = new FlowLayout();
            flowLayout4.setAlignment(FlowLayout.RIGHT);
            removeAllFunctionButtonPanel = new JPanel();
            removeAllFunctionButtonPanel.setLayout(flowLayout4);
            removeAllFunctionButtonPanel.add(getRemoveAllFuntionsButton(), null);
        }
        return removeAllFunctionButtonPanel;
    }

    /**
     * This method initializes removeAllFuntionsButton
     *
     * @return javax.swing.JButton
     */
    private JButton getRemoveAllFuntionsButton() {
        if (removeAllFuntionsButton == null) {
            removeAllFuntionsButton = new JButton();
            removeAllFuntionsButton.setText("Удалить все условия");
            removeAllFuntionsButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    functionList.setModel(new DefaultListModel());
                }
            });
        }
        return removeAllFuntionsButton;
    }

    /**
     * This method initializes aboutProgramItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getAboutProgramItem() {
        if (aboutProgramItem == null) {
            aboutProgramItem = new JMenuItem();
            aboutProgramItem.setText("О программе...");
            aboutProgramItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    JOptionPane.showMessageDialog(BaseGeneratorUI.this, "<html>" + getEditorTitle() + "\nАвторы:\n    (С)   Богданов М.С.     2007-2009 \n    (С)   Поздняков С.Н.   2007-2009 \n"+
                            (getSubAuthor() != null ? getSubAuthor() +"\n" : "")
                            +"e-mail: mikhael.bogdanov@gmail.com", "О программе", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
        return aboutProgramItem;
    }

    /**
     * This method initializes jPanel11
     *
     * @return javax.swing.JPanel
     */
//    private JPanel getJPanel11() {
//        if (jPanel11 == null) {
//            jPanel11 = new JPanel();
//            jPanel11.setLayout(new GridBagLayout());
//        }
//        return jPanel11;
//    }

    /**
     * This method initializes functionPanel
     *
     * @return javax.swing.JPanel
     */
    protected ConstraintPanel getFunctionPanel() {
        if (functionPanel == null) {
            functionPanel = new ConstraintPanel(this);
        }
        return functionPanel;
    }

    /**
     * This method initializes taskDescriptionPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getTaskDescriptionPanel() {
        if (taskDescriptionPanel == null) {
            taskDescriptionPanel = new JPanel();
            taskDescriptionPanel.setLayout(new BorderLayout());
            taskDescriptionPanel.setBorder(BorderFactory.createTitledBorder(null, "\u0421\u0433\u0435\u043d\u0435\u0440\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u043e\u0435 \u0443\u0441\u043b\u043e\u0432\u0438\u0435 \u0437\u0430\u0434\u0430\u0447\u0438 (\u0442\u0435\u043a\u0441\u0442 \u043c\u043e\u0436\u043d\u043e \u043a\u043e\u0440\u0440\u0435\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            taskDescriptionPanel.add(getJScrollPane(), BorderLayout.CENTER);
            taskDescriptionPanel.add(getGenerateDescriptionButtonPanel(), BorderLayout.SOUTH);
        }
        return taskDescriptionPanel;
    }

    /**
     * This method initializes save2DefaultButton
     *
     * @return javax.swing.JButton
     */
    private JButton getSave2DefaultButton() {
        if (save2DefaultButton == null) {
            save2DefaultButton = new JButton();
            save2DefaultButton.setText("Сохранить задачу в стандартный задачник");
            save2DefaultButton.setToolTipText("Сохраняет задачу в задачник  \"Сгенерированные задачи\"");
            save2DefaultButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (checkCanSave()) {
                        JFileChooser fc = getFileChooser();
                        fc.setCurrentDirectory(new File("." + File.separator + "tasks" +File.separator+ "generated"));
                        int result = getFileChooser().showSaveDialog(BaseGeneratorUI.this);
                        if (JFileChooser.APPROVE_OPTION == result) {
                            File file = fc.getSelectedFile();
                            if (!file.getName().toLowerCase().endsWith(".xml")) {
                                file = new File(file.getAbsolutePath() + ".xml");
                            }
                            String str = getGenerator().generateXml();
                            try {
                                saveToFile(str, file);
                            } catch (Exception e1) {
                                showError("Не могу сохранить задачу: \n" + e1.getMessage(), "Ошибка при сохранении", e1);
                                e1.printStackTrace();
                                return;
                            }

                        //System.out.println(str);
                            JOptionPane.showMessageDialog(BaseGeneratorUI.this, "Задача успешно сохранена.", "Задача успешно сохранена в стандартный задачник", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
        }
        return save2DefaultButton;
    }

    /**
     * This method initializes save2DefaultButtonPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getSave2DefaultButtonPanel() {
        if (save2DefaultButtonPanel == null) {
            save2DefaultButtonPanel = new JPanel();
            save2DefaultButtonPanel.setLayout(new BorderLayout());
            save2DefaultButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            save2DefaultButtonPanel.add(getSave2DefaultButton(), BorderLayout.NORTH);
        }
        return save2DefaultButtonPanel;
    }

    /**
     * This method initializes mainPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getMainPanel() {
        if (mainPanel == null) {
            mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(getMainPanel(), BoxLayout.X_AXIS));
            mainPanel.add(getLeftPanel(), null);
            mainPanel.add(getRightPanel(), null);
        }
        return mainPanel;
    }

    /**
     * This method initializes images
     *
     * @return javax.swing.JPanel
     */
    private JPanel getImages() {
        if (images == null) {
            images = new JPanel();
            images.setLayout(new BorderLayout());
            images.setPreferredSize(new Dimension(0, 170));
            images.setBorder(BorderFactory.createTitledBorder(null, "\u041a\u0430\u0440\u0442\u0438\u043d\u043a\u0430 \u043a \u0437\u0430\u0434\u0430\u0447\u0435", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            images.add(getImagePanel(), BorderLayout.CENTER);
            images.add(getImageButtonsPanel(), BorderLayout.SOUTH);
        }
        return images;
    }

    /**
     * This method initializes imageButtonsPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getImageButtonsPanel() {
        if (imageButtonsPanel == null) {
            FlowLayout flowLayout6 = new FlowLayout();
            flowLayout6.setAlignment(FlowLayout.RIGHT);
            imageButtonsPanel = new JPanel();
            imageButtonsPanel.setLayout(flowLayout6);
            imageButtonsPanel.add(getClearImagesButton(), null);
            imageButtonsPanel.add(getAddImageButton(), null);
        }
        return imageButtonsPanel;
    }

    /**
     * This method initializes imagePanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getImagePanel() {
        if (imagePanel == null) {
            imagePanel = new JPanel();
            imagePanel.setLayout(new BorderLayout());
            imagePanel.add(getJScrollPane2(), BorderLayout.CENTER);
        }
        return imagePanel;
    }

    /**
     * This method initializes clearImagesButton
     *
     * @return javax.swing.JButton
     */
    private JButton getClearImagesButton() {
        if (clearImagesButton == null) {
            clearImagesButton = new JButton();
            clearImagesButton.setText("Очистить");
            clearImagesButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    imagesList.clear();
                    getImageListPanel().removeAll();
                    getImageListPanel().repaint();
                }
            });
        }
        return clearImagesButton;
    }

    /**
     * This method initializes addImageButton
     *
     * @return javax.swing.JButton
     */
    private JButton getAddImageButton() {
        if (addImageButton == null) {
            addImageButton = new JButton();
            addImageButton.setText("Добавить картинку");
            addImageButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    int result = getImageChooser().showOpenDialog(BaseGeneratorUI.this);
                    if (JFileChooser.APPROVE_OPTION == result) {
                        File file = getImageChooser().getSelectedFile();
                        Image image;
                        try {
                            image = ImageIO.read(file);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            showError("Не могу загрузить изображение", "Ошибка", e1);
                            return;
                        }

                        ImageIcon icon = new ImageIcon(image);
                        getImageListPanel().add(new JLabel(icon));
                        imagesList.add(file.getName());
                        getImageListPanel().revalidate();
                    }

                }
            });
        }
        return addImageButton;
    }

    /**
     * This method initializes jScrollPane2
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane2() {
        if (jScrollPane2 == null) {
            jScrollPane2 = new JScrollPane();
            jScrollPane2.setViewportView(getImageListPanel());
        }
        return jScrollPane2;
    }

    /**
     * This method initializes imageListPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getImageListPanel() {
        if (imageListPanel == null) {
            FlowLayout flowLayout7 = new FlowLayout();
            flowLayout7.setAlignment(FlowLayout.LEFT);
            imageListPanel = new JPanel();
            imageListPanel.setLayout(flowLayout7);
        }
        return imageListPanel;
    }

    /**
     * This method initializes conditionPopup
     *
     * @return javax.swing.JPopupMenu
     */
    private JPopupMenu getConditionPopup() {
        if (conditionPopup == null) {
            conditionPopup = new JPopupMenu();
            conditionPopup.add(getDeleteConditionButton());
        }
        return conditionPopup;
    }

    /**
     * This method initializes deleteConditionButton
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getDeleteConditionButton() {
        if (deleteConditionButton == null) {
            deleteConditionButton = new JMenuItem();
            deleteConditionButton.setText("Удалить условие");
            deleteConditionButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    int [] obj = getFunctionList().getSelectedIndices();
                    if (obj != null ) {
                        for (int i = obj.length - 1; i >= 0; i--) {
                            ((DefaultListModel) getFunctionList().getModel()).remove(obj[i]);
                        }
                    }
                }
            });
        }
        return deleteConditionButton;
    }

    /**
     * This method initializes constructorMenu
     *
     * @return javax.swing.JMenu
     */
    private JMenu getConstructorMenu() {
        if (constructorMenu == null) {
            constructorMenu = new JMenu();
            constructorMenu.setText("Редакторы");
            for (Object[] constructor : constructors) {
                String name = (String) constructor[0];
                if (!"separator".equals(name)) {
                    final Class clazz = (Class) constructor[1];
                    JMenuItem item = new JMenuItem(name);
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            executeEditor(clazz);
                        }
                    });

                    constructorMenu.add(item);
                    if (BaseGeneratorUI.this.getClass().equals(clazz)) {
                        item.setEnabled(false);
                    }
                } else {
                    constructorMenu.addSeparator();
                }
            }
        }
        return constructorMenu;
    }

    /**
     * This method initializes saveToPrev
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getSaveToPrev() {
        if (saveToPrev == null) {
            saveToPrev = new JMenuItem();
            saveToPrev.setText("Сохранить");
            saveToPrev.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    String str = getGenerator().generateXml();
                    if (prevSave != null) {
                        try {
                            saveToFile(str, prevSave);
                        } catch (Exception e1) {
                            showError("Не могу сохранить задачу: \n" + e1.getMessage(), "Ошибка при сохранении", e1);
                            e1.printStackTrace();
                            return;
                        }
                        JOptionPane.showMessageDialog(BaseGeneratorUI.this, "Задача успешно сохранена.", "Задача успешно сохранена в стандартный задачник", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        //setActiveSave(null);
                        saveToPrev.setEnabled(false);
                    }
                }
            });
        }
        return saveToPrev;
    }

	private ScaleDialog dlg;

	private JMenuItem getScale() {
		if (scale == null) {
			scale = new JMenuItem();
			scale.setText("Масштабирование");
			scale.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dlg = new ScaleDialog(BaseGeneratorUI.this, "Масштабирование", "message", BaseGeneratorUI.this);
				}
			});
		}
		return scale;
	}

	private JPanel getImagePanelBigScale() {
		if (imagePanel == null) {
			imagePanel = new JPanel();
			imagePanel.setLayout(new BorderLayout());
			imagePanel.add(getJScrollPane2(), BorderLayout.CENTER);
		}
		return imagePanel;
	}

    /**
     * This method initializes this
     *
     * @return void
     */
    protected void initialize() {
        this.setSize(getGeneratorSize());
        this.setJMenuBar(getMainMenuBar());
        this.setContentPane(getJContentPane());
        this.setTitle(getEditorTitle());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setActiveSave(null);
        //pack();
    }

    protected Dimension getGeneratorSize() {
        return new Dimension(830, 400);
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
            jContentPane.add(getMainPanel(), BorderLayout.CENTER);
        }
        return jContentPane;
    }

    /**
     * This method initializes leftPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getLeftPanel() {
        if (leftPanel == null) {
            leftPanel = new JPanel();
            leftPanel.setLayout(new BorderLayout());
            leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            leftPanel.add(getTaskTitlePanel(), BorderLayout.NORTH);
            leftPanel.add(getMathDescriptionPanel(), BorderLayout.CENTER);
        }
        return leftPanel;
    }


    public JFileChooser getFileChooser() {
        if (fileChooser == null) {
            fileChooser = new JFileChooser(new File(".").getAbsolutePath());
            fileChooser.setDialogTitle("Выберите файл...");
            fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
            fileChooser.setFileFilter(new FileFilter() {
                public boolean accept(File pathname) {
                    if (pathname.isDirectory()) return true;
                    if (pathname.isFile())
                        if (pathname.getName().toLowerCase().endsWith(".xml")) return true;
                    return false;
                }

                public String getDescription() {
                    return "xml файлы (*.xml)";
                }
            });
        }
        return fileChooser;
    }

    public JFileChooser getImageChooser() {
        if (imageChooser == null) {
            imageChooser = new JFileChooser(new File("." + File.separator + "tasks" + File.separator + "imgs"));
            imageChooser.setDialogTitle("Выберите файл-картинку к задаче...");
            imageChooser.setControlButtonsAreShown(true);
            imageChooser.removeChoosableFileFilter(imageChooser.getChoosableFileFilters()[0]);

//            hide(imageChooser.getComponents(), 0);
            imageChooser.setFileFilter(new FileFilter() {
                public boolean accept(File pathname) {
                    if (pathname.isDirectory()) return true;
                    if (pathname.isFile()) {
                        String name = pathname.getName().toLowerCase();
                        if (name.endsWith(".png") || name.endsWith(".gif") || name.endsWith(".jpg") || name.endsWith(".jpeg")) {
                            return true;
                        }
                    }
                    return false;
                }

                public String getDescription() {
                    return "картинки (*.png, *.gif, *.jpg, *.jpeg)";
                }
            });
			imageChooser.setAccessory(new ImagePreview(imageChooser));
        }
        return imageChooser;
    }

	class ImagePreview extends JLabel {
		public ImagePreview(JFileChooser chooser) {
			setPreferredSize(new Dimension(100, 100));
			setBorder(BorderFactory.createEtchedBorder());
			chooser.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent event) {
					if (event.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
						File f = (File) event.getNewValue();
						if (f == null) {
							setIcon(null);
							return;
						}
						ImageIcon icon = new ImageIcon(f.getPath());
						if (icon.getIconWidth() > getWidth())
							icon = new ImageIcon(icon.getImage()
									.getScaledInstance(getWidth(), -1,
											Image.SCALE_DEFAULT));
						setVerticalAlignment(CENTER);
						setHorizontalAlignment(CENTER);
						setIcon(icon);
					}
				}
			});
		}
	}

    public void setActiveSave(File file) {
        prevSave = file;
        if (prevSave == null) {
            saveToPrev.setEnabled(false);
            setTitle(getEditorTitle() + " - " + "новая задача");
        } else {
            saveToPrev.setEnabled(true);
            setTitle(getEditorTitle() + " - " + file.getName());
        }
    }

    protected boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public ArrayList<String> getImagesList() {
        return imagesList;
    }

    public abstract String getEditorTitle();

    public String getSubAuthor() {
        return null;
    }

    public void executeEditor(Class clazz) {
        if (!BaseGeneratorUI.this.getClass().equals(clazz)) {
            if (!CollectionEditor.class.equals(clazz)) {
                BaseGeneratorUI.this.dispose();
            }
            try {
                clazz.getMethod("main", Class.forName("[Ljava.lang.String;")).invoke(null, new Object[] {null});
            } catch (Exception e) {
                JOptionPane.showMessageDialog(BaseGeneratorUI.this, "Не могу открыть редактор: \n" + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    protected void showError(String message, String title, Exception e) {
        JOptionPane.showMessageDialog(BaseGeneratorUI.this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public ComplexElement [] getConditions() {
		JList jList = getFunctionList();
		ComplexElement [] res = new ComplexElement [jList.getModel().getSize()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (ComplexElement)jList.getModel().getElementAt(i);
		}
		return res;
	}
}
