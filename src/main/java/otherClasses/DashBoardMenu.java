package otherClasses;

import DarkThemeComponents.DashBoardButton;
import DarkThemeComponents.FileMenuBar;

import javax.swing.*;
import java.awt.*;

public class DashBoardMenu extends JPanel {
    private DashBoardButton btnDatei, btnTable, btnChart, btnEinstellungen;
    private GridBagConstraints c;
    private FileMenuBar menuBar;
    private JMenu menu;

    public DashBoardMenu( ) {
        super(new GridBagLayout());
        init();
    }

    public void init(){
        menuBar = new FileMenuBar(this);
        menu = menuBar.getMenu();

        btnDatei = new DashBoardButton("Datei", this);
        btnTable = new DashBoardButton("Tabelle", this);
        btnChart = new DashBoardButton("Chart", this);
        btnEinstellungen = new DashBoardButton("Einstellungen", this);


        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0;
        c.gridx = 1;
        this.add(menuBar, c);

        c.ipady = 0;
        c.gridy = 1;
        c.gridx = 0;
        this.add(btnTable, c);

        c.gridy = 2;
        c.gridx = 0;
        this.add(btnChart, c);



    }




}
