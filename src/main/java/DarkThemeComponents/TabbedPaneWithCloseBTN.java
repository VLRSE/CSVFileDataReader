package DarkThemeComponents;

import otherClasses.TinyImageIcon;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TabbedPaneWithCloseBTN  extends JTabbedPane {
    private final Icon icon = new TinyImageIcon(getClass()
                            .getResource("/images/document-coloured.png") );
    private final Color disabledBGColor = Color.decode("#565656");
    private final Color disabledTextColor = Color.red;

    public TabbedPaneWithCloseBTN() {

        super();
        super.setBackground(disabledBGColor);


    }

    public TabbedPaneWithCloseBTN(String title, Component component, Icon icon) {
        super.addTab(title, icon, component);

        int selectedIndex = this.getTabCount()-1;
        setTabComponentAt(selectedIndex, new TabCloseButton(component, title, icon));
        super.setForegroundAt(getSelectedIndex(), Color.white);
    }

    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {

         int count = this.getTabCount()-1;

         setTabComponentAt(count, new TabCloseButton(component, title, icon));
         setComponentAt(count, component);
         setForegroundAt(getSelectedIndex(), Color.white);

    }

    @Override
    public void addTab(String title, Icon icon, Component component) {
        super.addTab(title,icon, component);

        int count = this.getTabCount()-1;

        setTabComponentAt(count, new TabCloseButton(component, title, icon));
        setComponentAt(count, component);
        setSelectedIndex(getTabCount()-1);
        setForegroundAt(getSelectedIndex(), Color.white);
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);

        int lastTabIndex = this.getTabCount()-1;
        setTabComponentAt(lastTabIndex, new TabCloseButton(component, title, icon));
        setSelectedIndex(getTabCount()-1);
        setForegroundAt(getSelectedIndex(), Color.white);
    }

    public void addTabNoCloseBTn(String title, Component component, Icon icon){
        super.addTab(title, icon, component);
        setSelectedIndex(getTabCount()-1);
        setForegroundAt(getSelectedIndex(), Color.white);

    }

    /*Tab exit button*/
    public class TabCloseButton extends JPanel{
        private final Component tabToClose;

        public TabCloseButton(Component tab, String title, Icon icon) {
            this.tabToClose = tab;

            FlowLayout layout = new FlowLayout(FlowLayout.CENTER,3,1);
            setLayout(layout);
            setOpaque(false);

            //Tab title
            JLabel label = new JLabel(title);
            label.setIcon(icon);
            label.setForeground(Color.white);
            add(label);

            JButton tabCloseBTN = new JButton(MetalIconFactory.getInternalFrameCloseIcon(16));
            tabCloseBTN.setMargin(new Insets(0,0,0,0));
            tabCloseBTN.setBackground(this.getBackground());
            tabCloseBTN.addMouseListener(new CloseTabListener(tab));

            add(tabCloseBTN);


        }
    }

    public class CloseTabListener implements MouseListener{

        private final Component tabToClose;

        public CloseTabListener(Component tab) {
            this.tabToClose = tab;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Component source = (Component) e.getSource();
            int indexClicked = TabbedPaneWithCloseBTN.this.indexOfTabComponent(source);

            //TabbedPaneWithCloseBTN.this.remove(indexClicked);
            TabbedPaneWithCloseBTN.this.remove(tabToClose);

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Object source = e.getSource();

            if (source instanceof  JButton){
                ((JButton) source).setBorder(BorderFactory.createLineBorder(Color.blue));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
