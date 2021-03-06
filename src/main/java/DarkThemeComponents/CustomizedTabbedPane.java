/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DarkThemeComponents;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 *
 * @author Admin
 */
public class CustomizedTabbedPane extends JTabbedPane{

    private static final long serialVersionUID = 1L;
    private TabbedPaneWithCloseBTN tabbedPaneWithCloseBTN;




    public CustomizedTabbedPane() throws FileNotFoundException {
        super.setPreferredSize(new Dimension(10,10));
        super.setForeground(Color.white);

        //remove the borders of the tabbedpane
        Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
        insets.set(-1,-1,-1,-1);
        UIManager.put("TabbedPane.contentBorderInsets", insets);




    }



}
