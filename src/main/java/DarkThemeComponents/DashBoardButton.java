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
    private final Color BG_COLOR, TEXT_COLOR;
    private final Container host;

    public DashBoardButton(String text, Container host) {
        
        super (text);
        this.host = host;
   
        this.BG_COLOR = host.getBackground();        
        this.TEXT_COLOR = Color.decode("#FAFAFA");
      
        initComponents();
        
    }
    public void initComponents(){
        
        setBackground(BG_COLOR);  
        setSize(new Dimension((host.getWidth()), 30));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setForeground(TEXT_COLOR);
        this.setMargin(new Insets(10,16,10,16));
    }
    
    
}
