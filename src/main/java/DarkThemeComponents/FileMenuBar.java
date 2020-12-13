/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DarkThemeComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author Vilrose Daquiado
 */
public class FileMenuBar extends JMenuBar implements ActionListener, ItemListener{
    private JMenu menu;
    private JMenuItem menuItem;
    private JButton button;
    //Create the menu bar.

    public FileMenuBar() {
//        super.setMargin(new Insets(10,16,10,16));
//        super.setMinimumSize(new Dimension(50,100));
        this.setOpaque(false);

        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        //create the  menu for the files imported.

    }


    public FileMenuBar(JMenu menu) {
        this.setOpaque(false);


        this.menu = menu;
        this.menu.setBorder(BorderFactory.createEmptyBorder(10,24,10,24));
        this.add(menu);
    }




    public JMenu getMenu() {
        return menu;
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
    
}
