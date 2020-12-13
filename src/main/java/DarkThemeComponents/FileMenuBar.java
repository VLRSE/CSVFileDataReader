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
    JMenu menu;
    JMenuItem menuItem;
    //Create the menu bar.

    public FileMenuBar(Container parent ) {
       super.setPreferredSize(new Dimension(parent.getWidth(),30));
        setBackground(parent.getBackground());
        setMargin(new Insets(10,16,10,16));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        //create the  menu for the files imported.
        menu = new JMenu();
        menu.setBackground(Color.red);
        menu.setMargin(new Insets(10,16,10,16));
        menu.setForeground(Color.decode("#EEEEEE"));
        add(menu);
        
        
    }
    //a method that has JMenuItem and JTabbedPane parameters
    public void addItem(JMenuItem menuItem){
//        menuItem = new JMenuItem(new ImageIcon("..\\CSVReader\\src\\main\\java\\Images\\baseline_description_black_18dp.png"));
        this.menuItem = menuItem;
        
        menu.add(menuItem);
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
