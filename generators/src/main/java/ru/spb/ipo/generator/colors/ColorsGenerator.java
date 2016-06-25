/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.spb.ipo.generator.colors;

import java.util.Map;
import java.awt.*;

import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.ui.*;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class ColorsGenerator extends BaseGeneratorUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BaseGeneratorUI frame = new ColorsGenerator();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public ColorsGenerator () {
        initialize();
    }


    
    @Override
    protected ConstraintPanel getSetPanel() {
        if (setPanel == null) {
            setPanel = new ColorsSetPanel(this);
	}
	return setPanel;
    }

    @Override
    protected BaseGenerator createGenerator(Map source, Map func, Map task) {
        Integer tab = (Integer)source.get("tabNum");
        switch(tab.intValue()) {
            case 1:
                return new BeadsXmlGenerator(source,func,task);
            case 2:
                return new PolygonXmlGenerator(source,func,task);
            case 3:
                return new PolyhedronXmlGenerator(source,func,task);
            default:
                return new BeadsXmlGenerator(source,func,task);
        }
    }

    protected Dimension getGeneratorSize() {
        return new Dimension(900, 550);
    }

    public String getEditorTitle() {
        return "Задачи о раскрасках (лемма Бернсайда)";
    }

    public String getSubAuthor() {
        return "    (С)   Пименов В.Д.       2009 ";
    }

    protected JButton getGenerateDescriptionButton() {
        if (generateDescriptionButton == null) {
            generateDescriptionButton = new JButton();
            generateDescriptionButton.setText("Сгенерировать условие");
            generateDescriptionButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    makeDescription();
                }
            });
        }
        return generateDescriptionButton;
    }
    
    public void makeDescription() {
        String ret = ((ColorsSetPanel)this.setPanel).isRightColors();
        if ( ret == null)
            taskDesc.setText(getGenerator().generateDescription());
        else {
            JOptionPane.showMessageDialog(this, ret);
        }
    }
}
