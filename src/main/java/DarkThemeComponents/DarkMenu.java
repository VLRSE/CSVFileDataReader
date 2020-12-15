package DarkThemeComponents;

import otherClasses.DarkThemeColor;

import javax.swing.*;
import java.awt.*;

public class DarkMenu extends JMenu {

    public DarkMenu() throws HeadlessException {
        setBackground(DarkThemeColor.PRIMARY_BG_COLOR);
        setForeground(DarkThemeColor.SECONDARY_TEXT_COLOR);
        setBorder(BorderFactory.createEmptyBorder(16,32,16,32));
        setBorderPainted(false);
        setAlignmentX(SwingConstants.CENTER);
        init();

    }

    private void init(){
        if(this.getComponentCount() > 0){
            JPanel panel = new JPanel();
            panel.add(this.getItem(0));
            panel.setBackground(Color.blue);
            this.add(panel);
        }
    }

    public DarkMenu(String s) {
        super(s);
    }

    public DarkMenu(Action a) {
        super(a);
    }

    public DarkMenu(String s, boolean b) {
        super(s, b);
    }
}
