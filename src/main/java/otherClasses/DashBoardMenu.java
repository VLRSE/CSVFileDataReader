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
        super(new BorderLayout());
        init();
    }

    public void init(){
        menuBar = new FileMenuBar();


        btnDatei = new DashBoardButton("Datei");
        btnTable = new DashBoardButton("Tabelle");
        btnChart = new DashBoardButton("Chart");
        btnEinstellungen = new DashBoardButton("Einstellungen");

        menuBar.add(btnDatei, BorderLayout.CENTER);
        menuBar.add(btnTable);
        menuBar.add(btnChart);
        menuBar.add(btnEinstellungen);

//
//        c = new GridBagConstraints();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.anchor = GridBagConstraints.CENTER;
//        c.gridy = 0;
//        c.gridx = 1;
//        this.add(menuBar, c);
//
//        c.ipady = 0;
//        c.gridy = 1;
//        c.gridx = 0;
//        this.add(btnTable, c);
//
//        c.gridy = 2;
//        c.gridx = 0;
//        this.add(btnChart, c);



    }




}
