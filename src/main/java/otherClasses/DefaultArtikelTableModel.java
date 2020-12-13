package otherClasses;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DefaultArtikelTableModel extends AbstractTableModel {

    private final String[] columnIdentifiers = {"Hauptartikelnr", "ArtikelName", "Hersteller"
            , "Beschreibung", "Materialangaben", "Geschlecht", "Produktart", "Ärmel"
            , "Bein", "Kragen", "Herstellung", "Taschenart", "Grammatur", "Material", "Ursprungsland", "Bildname"};

    private ArrayList<Artikel> artikelList;


    public DefaultArtikelTableModel(ArrayList<Artikel> artikelList) {
            this.artikelList = artikelList;
    }

    public DefaultArtikelTableModel(){
        artikelList = new ArrayList<Artikel>();

    }

    @Override
    public int getRowCount() {
        return artikelList.size();
    }

    @Override
    public int getColumnCount() {
        return 16;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //get the Artikel entry at the rowIndex requested from the ArrayList
        Artikel artikel =  artikelList.get(rowIndex);
        //get an array of the artikel values
        Object entryElement = null;
//                entryElement = artikel.toArray()[columnIndex];

        switch (columnIndex){
            case 0:
                entryElement = artikel.getHauptartikelnr();
                break;
            case 1:
                entryElement = artikel.getArtikelName();
                break;
            case 2:
                entryElement = artikel.getHersteller();
                break;

            case 3:
                entryElement = artikel.getBeschreibung();
                break;
            case 4:
                entryElement = artikel.getMaterialangaben();
                break;
            case 5:
                entryElement = artikel.getGeschlecht();
                break;
            case 6:
                entryElement = artikel.getProduktart();
                break;
            case 7:
                entryElement = artikel.getÄrmel();
                break;
            case 8:
                entryElement = artikel.getBein();
                break;
            case 9:
                entryElement = artikel.getKragen();
                break;
            case 10:
                entryElement = artikel.getHerstellung();
                break;
            case 11:
                entryElement = artikel.getTaschenart();
                break;
            case 12:
                entryElement = artikel.getGrammatur();
                break;
            case 13:
                entryElement = artikel.getMaterial();
                break;
            case 14:
                entryElement = artikel.getUrsprungsland();
                break;
            case 15:
                entryElement = artikel.getBildname();
                break;
        }

        return entryElement;
    }
}
