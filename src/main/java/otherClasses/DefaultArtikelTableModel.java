package otherClasses;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class DefaultArtikelTableModel extends DefaultTableModel {

    private final String[] columnIdentifiers = {"Hauptartikelnr", "ArtikelName", "Hersteller"
            , "Beschreibung", "Materialangaben", "Geschlecht", "Produktart", "Ärmel"
            , "Bein", "Kragen", "Herstellung", "Taschenart", "Grammatur", "Material", "Ursprungsland", "Bildname"};

    private final List<Artikel> artikelList;


    public DefaultArtikelTableModel(ArrayList<Artikel> artikelList) {

        this.artikelList = artikelList;
    }

    public DefaultArtikelTableModel(){
        artikelList = new ArrayList<Artikel>();

    }

    public List<Artikel> getTableData(){
        return artikelList;
    }



    @Override
    public void addRow(Object[] rowData) {
        artikelList.add(new Artikel(rowData));
    }

    @Override
    public int getColumnCount() {
        return 16;
    }

    @Override
    public int getRowCount() {
        return artikelList.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnIdentifiers[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Artikel artikel = artikelList.get(row);
        return artikel.getItem(column);
    }


}
