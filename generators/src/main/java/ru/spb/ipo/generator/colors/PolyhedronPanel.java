package ru.spb.ipo.generator.colors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.colors.figures.Cube;
import ru.spb.ipo.generator.colors.figures.Dodecahedron;
import ru.spb.ipo.generator.colors.figures.Figure;
import ru.spb.ipo.generator.colors.figures.Icosaedr;
import ru.spb.ipo.generator.colors.figures.Octahedron;
import ru.spb.ipo.generator.colors.figures.Parallelepiped;
import ru.spb.ipo.generator.colors.figures.Piramida;
import ru.spb.ipo.generator.colors.figures.Prizma;
import ru.spb.ipo.generator.colors.figures.Tetrahedron;


/**
 *
 * @author Admin
 */
public class PolyhedronPanel extends ConstraintPanel {
    private JComboBox color;
    private JComboBox elementsCount;
    private JButton addFigureElement;
    
    private JComboBox forColoring;
    private JComboBox figureType;
    private JComboBox corners;
    private JRadioButton goodPoly;
    private JRadioButton piramida;
    private JRadioButton prizm;
    private JRadioButton parallel;
    private ButtonGroup bg;
    
    private JPanel figurePanel;
    private JPanel colorPanel;
    
    ColorsSetPanel parent;
    
    private int taskType = 1;
    private JRadioButton type1Btn;
    private JSpinner colorCount;
    private JRadioButton type2Btn;
    private ButtonGroup bgTask;
    
    private String radioCommand = "Правильный многогранник";
    private Hashtable selectedColors = new Hashtable();

    public PolyhedronPanel(BaseGeneratorUI gen, final ColorsSetPanel parent) {
        super(gen);
        this.parent = parent;
        initComponents();
    }
    
    private void initComponents() {

        figurePanel = new javax.swing.JPanel();
        parallel = new javax.swing.JRadioButton();
        prizm = new javax.swing.JRadioButton();
        piramida = new javax.swing.JRadioButton();
        goodPoly = new javax.swing.JRadioButton();
        figureType = new javax.swing.JComboBox();
        forColoring = new javax.swing.JComboBox();
        JLabel jLabel1 = new javax.swing.JLabel();
        JLabel jLabel2 = new javax.swing.JLabel();
        corners = new javax.swing.JComboBox();
        colorPanel = new javax.swing.JPanel();
        type1Btn = new javax.swing.JRadioButton();
        type2Btn = new javax.swing.JRadioButton();
        JLabel jLabel3 = new javax.swing.JLabel();
        color = new javax.swing.JComboBox();
        JLabel jLabel4 = new javax.swing.JLabel();
        elementsCount = new javax.swing.JComboBox();
        addFigureElement = new javax.swing.JButton();

        figurePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Фигура"));

        jLabel1.setText("Выберите что красить:");
        forColoring.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "грани", "вершины", "рёбра" }));
        
        FigureListener listener = new FigureListener();
        bg = new ButtonGroup();
        prizm.setText("Призма");
        prizm.setActionCommand("Призма");
        prizm.addActionListener(listener);
        bg.add(prizm);

        piramida.setText("Пирамида");
        piramida.setActionCommand("Пирамида");
        piramida.addActionListener(listener);
        bg.add(piramida);

        parallel.setText("Прямоугольный параллелепипед");
        parallel.setActionCommand("Прямоугольный параллелепипед");
        parallel.addActionListener(listener);
        bg.add(parallel);
        
        goodPoly.setText("Правильный многогранник");
        goodPoly.setSelected(true);
        goodPoly.setActionCommand("Правильный многогранник");
        goodPoly.addActionListener(listener);
        bg.add(goodPoly);

        figureType.addItem(new String("тетраэдр"));
        figureType.addItem(new String("куб"));
        figureType.addItem(new String("октаэдр"));
        figureType.addItem(new String("додекаэдр"));
        figureType.addItem(new String("икосаэдр"));

        jLabel2.setText("Вершин в основании:");

        for (int i = 3; i < 10; i++)
            corners.addItem(String.valueOf(i));
        corners.setEnabled(false);

        javax.swing.GroupLayout figurePanelLayout = new javax.swing.GroupLayout(figurePanel);
        figurePanel.setLayout(figurePanelLayout);
        figurePanelLayout.setHorizontalGroup(
            figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(figurePanelLayout.createSequentialGroup()
                .addGroup(figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(figurePanelLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(forColoring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(figurePanelLayout.createSequentialGroup()
                        .addComponent(goodPoly)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(figureType, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(parallel)
                    .addGroup(figurePanelLayout.createSequentialGroup()
                        .addGroup(figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prizm)
                            .addComponent(piramida))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(corners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        figurePanelLayout.setVerticalGroup(
            figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(figurePanelLayout.createSequentialGroup()
                .addGroup(figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(forColoring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goodPoly)
                    .addComponent(figureType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(figurePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(piramida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prizm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(corners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)))
                .addComponent(parallel)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        colorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Краски"));

        ButtonGroup colorBG = new ButtonGroup();
        type1Btn.setText("По количеству красок");
        type2Btn.setText("По элементам фигуры");
        colorBG.add(type1Btn);
        type1Btn.setActionCommand("type1");
        type1Btn.addActionListener(new ColorListener());
        type1Btn.setSelected(true);
        colorBG.add(type2Btn);
        type2Btn.setActionCommand("type2");
        type2Btn.addActionListener(new ColorListener());
        
        colorCount = new JSpinner(new SpinnerNumberModel(2,2,10,1));

        jLabel3.setText("Цвет:");

        color.addItem(new String ("красный"));
        color.addItem(new String ("желтый"));
        color.addItem(new String ("синий"));
        color.addItem(new String ("зеленый"));
        color.addItem(new String ("белый"));
        color.addItem(new String ("черный"));
        color.addItem(new String ("оранжевый"));
        color.addItem(new String ("коричневый"));
        color.addItem(new String ("фиолетовый"));
        color.setEnabled(false);

        jLabel4.setText("Количество:");
        for (int i = 1; i < 10; i++) {
            elementsCount.addItem(String.valueOf(i));
        }
        elementsCount.setEnabled(false);

        addFigureElement.setText("Добавить");
        addFigureElement.setEnabled(false);
        addFigureElement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createColorCond();
            }
        });
        
        javax.swing.GroupLayout colorPanelLayout = new javax.swing.GroupLayout(colorPanel);
        colorPanel.setLayout(colorPanelLayout);
        colorPanelLayout.setHorizontalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(colorPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elementsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(addFigureElement))
                    .addGroup(colorPanelLayout.createSequentialGroup()
                        .addComponent(type1Btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(colorCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(type2Btn))
                .addGap(26, 26, 26))
        );
        colorPanelLayout.setVerticalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type1Btn)
                    .addComponent(colorCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(type2Btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(elementsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addFigureElement)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(figurePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(figurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }
    
    private void createColorCond() {
        ComplexElement [] conds = parent.generator.getConditions();
        if (conds.length == 0)
            selectedColors = new Hashtable();
        String curColor = (String)color.getSelectedItem();
        String countColor = (String) selectedColors.get(curColor);
        if (countColor != null) {
            JOptionPane.showMessageDialog(this, "Этот цвет уже выбран");
        }
        else {
            String count = (String)elementsCount.getSelectedItem();
            String whatBrush = (String)forColoring.getSelectedItem();
            addCondition(new ColorCondition(curColor,count,whatBrush));
            selectedColors.put(curColor, count);
        }
    }
    
    class FigureListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            radioCommand = e.getActionCommand();
            if (radioCommand.equals("Правильный многогранник")) {
                corners.setEnabled(false);
                figureType.setEnabled(true);
            }
            else if (radioCommand.equals("Пирамида") || 
                    radioCommand.equals("Призма")) {
                corners.setEnabled(true);
                figureType.setEnabled(false);
            }
            else {
                corners.setEnabled(false);
                figureType.setEnabled(false);
            }
        }
    }
    
    class ColorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("type1")) {
                colorCount.setEnabled(true);
                color.setEnabled(false);
                elementsCount.setEnabled(false);
                addFigureElement.setEnabled(false);
                taskType = 1;
            }
            else {
                colorCount.setEnabled(false);
                color.setEnabled(true);
                elementsCount.setEnabled(true);
                addFigureElement.setEnabled(true);
                taskType = 2;
            }
        }
    }
    
    public void fillMaps(Map source, Map func, Map task) {
        PolyhedronCondition cond;
        if (corners.isEnabled()) {
            cond = new PolyhedronCondition((String)forColoring.getSelectedItem(),
                radioCommand, null,(String)corners.getSelectedItem());
            source.put("corners",(String)corners.getSelectedItem());
        }
        else if(figureType.isEnabled()) {
            cond = new PolyhedronCondition((String)forColoring.getSelectedItem(),
                radioCommand, (String)figureType.getSelectedItem(),null);
            source.put("polyType",(String)figureType.getSelectedItem());
        }
        else
            cond = new PolyhedronCondition((String)forColoring.getSelectedItem(),
                radioCommand, null,null);
        source.put("polyhedronText", cond.toDescription());
        if (taskType == 1)
            source.put("colors",(Integer)colorCount.getValue());
        else {
            source.put("colors", selectedColors.size());
            ArrayList<Integer> cList = new ArrayList<Integer>();
            Enumeration en = selectedColors.keys();
            while(en.hasMoreElements()) {
                String key = (String)en.nextElement();
                cList.add(Integer.valueOf((String)selectedColors.get(key)));
            }
            source.put("cList", cList);
        }
        source.put("whatToBrush", (String)forColoring.getSelectedItem());
        source.put("polyhedron", radioCommand);
        source.put("taskType",Integer.valueOf(taskType));
        source.put("tabNum", Integer.valueOf(parent.tabs.getSelectedIndex()+1));
    }
    
    public String isRightColors() {
        String msg=null;
        int colCount = 0;
        if (taskType == 2) {
            Enumeration en = selectedColors.keys();
            while(en.hasMoreElements()) {
                String key = (String)en.nextElement();
                colCount = colCount + Integer.valueOf((String)selectedColors.get(key));
            }
            Figure fig;
            if (figureType.isEnabled()) {
                String type = (String)figureType.getSelectedItem();
                if (type.equals("тетраэдр"))
                    fig = new Tetrahedron((String)forColoring.getSelectedItem());
                else if (type.equals("куб"))
                    fig = new Cube((String)forColoring.getSelectedItem());
                else if (type.equals("октаэдр"))
                    fig = new Octahedron((String)forColoring.getSelectedItem());
                else if (type.equals("додекаэдр"))
                    fig = new Dodecahedron((String)forColoring.getSelectedItem());
                else
                    fig = new Icosaedr((String)forColoring.getSelectedItem());
            }
            else if (corners.isEnabled()) {
                if (radioCommand.equals("Призма"))
                    fig = new Prizma((String)forColoring.getSelectedItem(),
                        Integer.valueOf((String)corners.getSelectedItem()).intValue());
                else
                    fig = new Piramida((String)forColoring.getSelectedItem(),
                        Integer.valueOf((String)corners.getSelectedItem()).intValue());
            }
            else {
                fig = new Parallelepiped((String)forColoring.getSelectedItem());
            } 
            if (fig.getDim() != colCount)
                if (fig.getDim() < 5)
                    msg = new String("Выберите "+fig.getDim()+" цвета.");
                else    
                    msg = new String("Выберите "+fig.getDim()+" цветов.");
        }
        return msg;
    }
    
    public void unlockForColoring () {
        forColoring.setEnabled(true);
    }
}
