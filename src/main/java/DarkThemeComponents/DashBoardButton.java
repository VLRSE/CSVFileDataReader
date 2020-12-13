/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DarkThemeComponents;


import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public final class DashBoardButton extends JButton {

    private Dimension size;
    private final Color  TEXT_COLOR = Color.decode("#FAFAFA");


    public DashBoardButton(String text) {
        
        super (text);
        super.setBackground(Color.decode("#424242"));
        super.setForeground(Color.white);
//        super.setBorder(BorderFactory.createEmptyBorder(10,24,10,24));
            super.setOpaque(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setForeground(Color.white);

    }

    
}
