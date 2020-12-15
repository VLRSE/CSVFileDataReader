package DarkThemeComponents;

import javax.swing.*;
import java.awt.*;

public class DashBoardMenuBar extends JMenuBar {
    private DashBoardButton btnDatei, btnTable, btnChart, btnEinstellungen, btnNewTable;
    private GridBagConstraints c;

    public DashBoardMenuBar( ) {

        setOpaque(false);
        setForeground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBorder(null);
        init();
    }

    public void init(){

        btnDatei = new DashBoardButton("Datei");


        btnTable = new DashBoardButton("Tabelle");
        btnChart = new DashBoardButton("Chart");
        btnEinstellungen = new DashBoardButton("Einstellungen");
        btnNewTable = new DashBoardButton("Neu Tabelle");


        this.add( btnDatei);
        this.add(btnTable);
        this.add(btnChart);
        this.add(btnEinstellungen);



//        this.add( Box.createRigidArea(new Dimension(0,150)));



    }

    public DashBoardButton getMenuDatei() {
        return btnDatei;
    }

    public DashBoardButton getMenuTable() {
        return btnTable;
    }

    public DashBoardButton getMenuChart() {
        return btnChart;
    }

    public DashBoardButton getMenuEinstellungen() {
        return btnEinstellungen;
    }


    public DashBoardButton getMenuNewTable() {
        return btnNewTable;
    }

    @Override
    public JMenu getMenu(int index) {
        return super.getMenu(index);
    }
}
