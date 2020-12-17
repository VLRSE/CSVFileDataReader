package DarkThemeComponents;

import otherClasses.DarkThemeColor;
import otherClasses.TinyImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DarkMenu extends JPanel  implements ActionListener, MouseListener {

    private DarkMenuItem menuDatei, menuTabelle, menuEinstellungen, menuNeuTabelle;
    private ButtonGroup buttonGroupMenu, btnGroupMenuItems;
    private List<AbstractButton> menuItems;
    private JPanel menuItemsPanel;
    private JLabel iconArrow;
    private DarkMenuItem menu;
    private Icon menuIcon;
    private String title;
    private ButtonGroup btnGroup;
    private int tabIndex = -1;




    public DarkMenu(String title) {
        this.title = title;
        init();
    }

    public DarkMenu(String title, Icon icon) {
        this.title = title;
        this.menuIcon = icon;
        init();
    }



    protected void init() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);


        //create a MenuItem instance that will act as the menu´s button
        menu = new DarkMenuItem(title);
        menu.addActionListener(this);
        if (menuIcon != null){
            menu.setIcon(menuIcon);
        }

        /*create a panel instance to hold a button and a JLabel(for an icon)
         *  this panel will serve as the menu Label
         */
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        URL iconURL;

        //create a button instance to hold the title and respond to ActionListener
        iconURL = getClass().getResource("/images/folder.png");
        menu = new DarkMenuItem(title, new TinyImageIcon(iconURL));

        //create a label that hold the arrow to show/hide the MenuItems
        iconURL = getClass().getResource("/images/arrow_left.png");
        iconArrow = new JLabel(new TinyImageIcon(iconURL, 18,18));
        iconArrow.addMouseListener(this);


        //check if the parameters are null or not
        if(menuIcon != null){
            menuIcon = menuIcon;
            menu.setIcon(menuIcon);

        }
        if(title != null){
            menu.setText(title);
        }
//        if(mnemonic != -1){
//            menu.setMnemonic(mnemonic);
//        }
        //add the menu Label to the Menu panel
        panel.add(menu);

        //add an arrow to the Menu panel to allow users to show and hide MenuItems
        iconArrow.setVisible(false);
        panel.add(iconArrow);
        this.add(panel);


        //create a list that will hold the MenuItems
        this.menuItems = new ArrayList<>();
        btnGroup = new ButtonGroup();

        //craete a panel that will hold the MenuItems
        menuItemsPanel = new JPanel();
        menuItemsPanel.setOpaque(false);
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setForeground(DarkThemeColor.SECONDARY_TEXT_COLOR);

        //only enable the
        menuItemsPanel.addPropertyChangeListener(evt -> {
            if(evt.getPropertyName().equalsIgnoreCase("size")){
                toggleButton(iconArrow);
                iconArrow.setVisible(true);
            }
        });

        this.add(menuItemsPanel);

    }




    public void addItem(AbstractButton menuItem){
        int oldValue = menuItemsPanel.getComponentCount();

        this.menuItems.add(menuItem);
        this.menuItemsPanel.add(menuItem);
        menuItemsPanel.firePropertyChange("size", oldValue, (oldValue+1) );
        Color bg = menuItemsPanel.getBackground();
        menuItemsPanel.getComponent(menuItems.size()-1);
        btnGroup.add(menuItem);
        menuItem.setSelected(true);


    }

    public void addMenuItem(AbstractButton menuItem, Icon icon){
        this.menuItems.add(menuItem);
        this.menuItemsPanel.add(menuItem);
    }

    private void showMenu(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                menuItemsPanel.setVisible(true);
            }
        }).start();
    }
    private void hideMenu(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                menuItemsPanel.setVisible(false);
            }
        }).start();
    }

    private void toggleButton(JLabel iconArrow){
        boolean menuItemsClosed = iconArrow.getIcon().toString().contains("arrow_left" );
        TinyImageIcon iconbutton;

        if(menuItemsClosed ){
            showMenu();

            iconbutton = new TinyImageIcon(getClass().getResource("/images/arrow_down.png"));
            iconArrow.setIcon(iconbutton);
        }
        else{
            hideMenu();
            iconbutton = new TinyImageIcon(getClass().getResource("/images/arrow_left.png"));
            iconArrow.setIcon(iconbutton);
        }

    }


    public AbstractButton getMenuItem(int index){

        return menuItems.get(index);
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public AbstractButton getMenu(){
        return this.menu;
    }





    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if(source.equals(iconArrow)){
            toggleButton(iconArrow);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source.equals(iconArrow)){
            toggleButton(iconArrow);
            System.out.println("arrow clicked" +
                    "");
        }
    }
}
