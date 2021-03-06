package DarkThemeComponents;

import Main.Main;
import otherClasses.TinyImageIcon;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class DashBoardMenuBar extends JMenuBar {
    private DarkMenu menuDatei, menuDiagramm, menuEinstellungen, menuNeuTabelle;
    private ArrayList<DarkMenu> menuList ;
    private GridBagConstraints c;
    private ButtonGroup buttonGroup;

    public DashBoardMenuBar() {

        setOpaque(false);
        setForeground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        init();
    }

    private void init(){

        URL iconURL;

        TinyImageIcon icon;


        menuList = new ArrayList<>();

        menuDatei = new DarkMenu("Datei");
        menuDiagramm = new DarkMenu("Diagramm");
        menuEinstellungen = new DarkMenu("Einstellungen");
        menuNeuTabelle = new DarkMenu("Neu Tabelle");


        iconURL = getClass().getResource("/images/folder.png");
        icon = new TinyImageIcon(iconURL);
        menuDatei.setMenuIcon(icon);

        iconURL = getClass().getResource("/images/chart.png");
        icon = new TinyImageIcon(iconURL);
        menuDiagramm.setMenuIcon(icon);

        iconURL = getClass().getResource("/images/settings.png");
        icon = new TinyImageIcon(iconURL);
        menuEinstellungen.setMenuIcon(icon);

        iconURL = getClass().getResource("/images/insert-table.png");
        icon = new TinyImageIcon(iconURL);
        menuNeuTabelle.setMenuIcon(icon);

        //add each MenuItem to the menuList Collection
        menuList.add(menuDatei);
        menuList.add(menuDiagramm);
        menuList.add(menuEinstellungen);
        menuList.add(menuNeuTabelle);



        this.add(menuDatei);


        this.add(menuDiagramm);


        this.add(menuEinstellungen);


        this.add(menuNeuTabelle);

////        this.add( Box.createRigidArea(new Dimension(0,150)));



    }

    public DarkMenu getMenuDatei() {
        return menuDatei;
    }

    public DarkMenu getMenuDiagramm() {
        return menuDiagramm;
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
