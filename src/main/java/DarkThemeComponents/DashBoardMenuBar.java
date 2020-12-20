package DarkThemeComponents;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashBoardMenuBar extends JMenuBar {
    private DarkMenu menuDatei, menuTabelle, menuEinstellungen, menuNeuTabelle;
    private ArrayList<DarkMenu> menuList ;
    private GridBagConstraints c;
    private ButtonGroup buttonGroup;

    public DashBoardMenuBar( ) {

        setOpaque(false);
        setForeground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBorder(null);
        init();
    }

    public void init(){


        menuList = new ArrayList<>();

        menuDatei = new DarkMenu("Datei");
        menuTabelle = new DarkMenu("Diagramm");
        menuEinstellungen = new DarkMenu("Einstellungen");
        menuNeuTabelle = new DarkMenu("Neu Tabelle");


        menuList.add(menuDatei);
        menuList.add(menuTabelle);
        menuList.add(menuEinstellungen);
        menuList.add(menuNeuTabelle);



        this.add(menuDatei);
        this.add(menuTabelle);
        this.add(menuEinstellungen);
        this.add(menuNeuTabelle);

////        this.add( Box.createRigidArea(new Dimension(0,150)));



    }

    public DarkMenu getMenuDatei() {
        return menuDatei;
    }

    public DarkMenu getMenuTabelle() {
        return menuTabelle;
    }

    public DarkMenu getMenuEinstellungen() {
        return menuEinstellungen;
    }

    public DarkMenu getMenuNeuTabelle() {
        return menuNeuTabelle;
    }

    public ArrayList<DarkMenu> getMenuList() {
        return menuList;
    }
}
