package DarkThemeComponents;

import otherClasses.DarkThemeColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static otherClasses.DarkThemeColor.SECONDARY_TEXT_COLOR;

public class DarkMenuItem extends JButton implements ActionListener {
    private Icon menuIcon;
    private String title;

    public DarkMenuItem() {

        //add buttons to the JPanel
        init(null, null,-1);
    }

    public DarkMenuItem(String title, Icon icon ){
        super(title, icon);
        init(title, icon, 0 );
    }

    public DarkMenuItem(String title, int mnemonic ){
        super(title);
        setMnemonic(mnemonic);

        init(title, null,mnemonic );
    }

    public DarkMenuItem(Icon icon, int mnemonic ){
        super(icon);
        setMnemonic(mnemonic);

        init(title, null,mnemonic );
    }

    public DarkMenuItem(String title, Icon icon, int mnemonic ){
        init(title, icon, mnemonic );
    }

    public DarkMenuItem(String title) {
        super(title);
        init(title, null, -1 );
    }

    protected void init(String title, Icon icon, int mnemonic){

        setBackground(DarkThemeColor.SECONDARY_BG_COLOR);
        setForeground(SECONDARY_TEXT_COLOR);
//        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addActionListener(this);

         if(icon != null){

            setIcon(icon);

        }
        if(title != null){
            setText(title);
        }
        if(mnemonic != -1){
            setMnemonic(mnemonic);
        }



    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
        setBackground(DarkThemeColor.PRIMARY_BG_COLOR);
    }
}
