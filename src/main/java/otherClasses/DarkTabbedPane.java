package otherClasses;

import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/**
 *
 * @author Vilrose Daquiado
 */
public class DarkTabbedPane extends JTabbedPane {

    private Color DEFAULT_BG_COLOR  = Color.decode("#212121")
            ,DEFAULT_TEXT_COLOR     = Color.decode("#EEEEEE")
            ,PRIMARY_COLOR          =  Color.decode("#42A5F5");;
    private Component component;

    public DarkTabbedPane(Component parent, Component component) {

        this.component = component;
        //change the background color of the selected panel on the tabbedPane
        UIManager.put("TabbedPane.selected", PRIMARY_COLOR);

        int selectedPosition = super.getTabCount()-1;
        super.setSelectedIndex(selectedPosition);
        super.setBackgroundAt(selectedPosition, PRIMARY_COLOR.darker());
        super.setForeground(DEFAULT_TEXT_COLOR);
        super.setBackground(DEFAULT_BG_COLOR);
        initComponents(component);

    }

    public void initComponents(Component component){
        addTab("Start Seite", component);
    }

    public DarkTabbedPane(int tabPlacement) {
        super(tabPlacement);
    }

    public DarkTabbedPane(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
    }

    @Override
    public void add(Component component, Object constraints, int index) {
        super.add(component, constraints, index); //To change body of generated methods, choose Tools | Templates.
    }
}
