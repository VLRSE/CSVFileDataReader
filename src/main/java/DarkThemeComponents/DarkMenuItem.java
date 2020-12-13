/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DarkThemeComponents;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Vilrose Daquiado
 * 
 */
public class DarkMenuItem extends JMenuItem {
    private final Color DEFAULT_BG_COLOR = Color.decode("#212121");
    private final Color DEFAULT_TEXT_COLOR = Color.decode("#EEEEEE");
    private static final long serialVersionUID = 1L;
    private final String title;
    private final JTabbedPane tabbedPane;
    
    public DarkMenuItem(String title, JTabbedPane tabbedPane) {
        super(title,new ImageIcon("\\src\\main\\java\\Images\\dashboardIcon.png"));
        super.setForeground(DEFAULT_TEXT_COLOR);
        super.setBackground(DEFAULT_BG_COLOR);
        super.setAlignmentX(Component.RIGHT_ALIGNMENT);
        super.setEnabled(true);
        this.title = title;
        this.tabbedPane = tabbedPane;
    }
    
    
}
