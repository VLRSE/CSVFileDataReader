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
public class MyTabbedPane extends JPanel{

    private static final long serialVersionUID = 1L;
    private final JTabbedPane tabbedPane;
    private JComponent panel;
    private final Color BG_COLOR = Color.decode("#424242");
    private final Dimension size;
    private final String title;

    public MyTabbedPane(Dimension size, String title) throws FileNotFoundException {
        super(new GridLayout(1,1));
        this.size  = size;
        super.setSize(size);
        super.setBackground(BG_COLOR);

        this.title = title;

        tabbedPane = new JTabbedPane();

        this.add(tabbedPane);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }


}
