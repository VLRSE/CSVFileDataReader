/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DarkThemeComponents;


import otherClasses.DarkThemeColor;

import javax.swing.*;
import java.awt.*;

import static otherClasses.DarkThemeColor.SECONDARY_TEXT_COLOR;

@SuppressWarnings("serial")
public final class DashBoardButton extends JButton {

    public DashBoardButton(String text) {
        
        super (text);
        setBackground(Color.decode("#424242"));
        setForeground(Color.white);
        setBorder(BorderFactory.createEmptyBorder(16,24,16,24));
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(SECONDARY_TEXT_COLOR);


    }

    public DashBoardButton(String text, Icon icon) {
        super(text, icon);
    }
}
