package DarkThemeComponents;

import otherClasses.DarkThemeColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomButtonTabPane extends JPanel  {

    private final JTabbedPane tabbedPane;

    public CustomButtonTabPane(JTabbedPane tabbedPane) {
        super.setOpaque(false);
        this.tabbedPane = tabbedPane;

        //get the String titles from the tabbedPane
        JLabel label = new JLabel(){
            public String getText(){
                int index = tabbedPane.indexOfTabComponent(this);
                if(index != -1){
                    return tabbedPane.getTitleAt(index);
                }
                return null;
            }
        };

        this.add(label);

        //create a JButton object for the close button
        JButton closeButton = new TabButton();


    }


    public class TabButton extends JButton implements ActionListener {

        public TabButton() {
            int size = 16;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("Tab schließen");

            //Make button transparent
            setContentAreaFilled(false);

            //Disable focusable
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            setBackground(DarkThemeColor.SECONDARY_BG_COLOR);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = tabbedPane.indexOfTabComponent(this);
            if (index != -1) {
                tabbedPane.remove(index);
            }
        }

        //paint the close(x) icon
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            //shift the image for pressed buttons
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.decode("#a0a0a0"));
            if (getModel().isRollover()) {
                g2.setColor(Color.red);

            }
            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }

    private final static MouseListener tabButtonMouseListener = new MouseAdapter() {

        @Override
        public void mouseEntered(MouseEvent e) {
            //check if the clicked component is an Abstract button instance
            Component component = e.getComponent();
            if(component instanceof AbstractButton){
                AbstractButton button = (AbstractButton) component;
                button.setBackground(button.getBackground().darker());
                button.setBorderPainted(true);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //check if the clicked component is an Abstract button instance
            Component component = e.getComponent();
            if(component instanceof AbstractButton){
                AbstractButton button = (AbstractButton) component;
                button.setBackground(DarkThemeColor.SECONDARY_BG_COLOR);
                button.setBorderPainted(false);
            }
        }
    };




}


