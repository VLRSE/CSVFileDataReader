package otherClasses;

import java.awt.List;
import java.util.*;
/**
 *
 * @author Vilrose Daquiado
 */

public class Artikel extends List {

    private final Map<String, String> rowMap;
    private final String[] HEADERS = {"Hauptartikelnr","ArtikelName", "Hersteller", "Beschreibung", "Materialangaben",
                                "Geschlecht", "Produktart", "Ärmel", "Bein", "Kragen", "Herstellung", "Taschenart",
                                 "Grammatur",  "Material",  "Ursprungsland", "Bildname"   };

    private final String Hauptartikelnr;
    private final String ArtikelName;
    private final String Hersteller;
    private final String Beschreibung;
    private final String Materialangaben;
    private final String Geschlecht;
    private final String Produktart;
    private final String Ärmel;
    private final String Bein;
    private final String Kragen;
    private final String Herstellung;
    private final String Taschenart;
    private final String Grammatur;
    private final String Material;
    private final String Ursprungsland;
    private final String Bildname;

    public Artikel( Map<String, String> rowMap) {

        this.rowMap = rowMap;

        this.Hauptartikelnr = rowMap.get("Hauptartikelnr").replace(".", "");
        this.ArtikelName = rowMap.get("ArtikelName");
        this.Hersteller = rowMap.get("Hersteller");
        this.Beschreibung = rowMap.get("Beschreibung");
        this.Materialangaben = rowMap.get("Materialangaben");
        this.Geschlecht     = rowMap.get("Geschlecht");

        this.Produktart = rowMap.get("Produktart");
        this.Ärmel  = rowMap.get("Ärmel");
        this.Bein   = rowMap.get("Bein");
        this.Kragen = rowMap.get("Kragen");
        this.Herstellung    = rowMap.get("Herstellung");
        this.Taschenart = rowMap.get("Taschenart");
        this.Grammatur  = rowMap.get("Grammatur");
        this.Material   = rowMap.get("Material");
        this.Ursprungsland  = rowMap.get("Ursprungsland");
        this.Bildname   = rowMap.get("Bildname").replace("\\.{0,1}", "");

    }


    public Artikel( Object[] row) {
        this.rowMap = new HashMap<>();

        for (int i = 0; i < row.length; i++) {
            String key = HEADERS[i];
            String value = row[i].toString();
//            if( value.isEmpty() || value == null){
            rowMap.put(key, value );
//            }
//            else{
//
//            }

        }

        this.Hauptartikelnr = rowMap.get("Hauptartikelnr").replace(".", "");
        this.ArtikelName = rowMap.get("ArtikelName");
        this.Hersteller = rowMap.get("Hersteller");
        this.Beschreibung = rowMap.get("Beschreibung");
        this.Materialangaben = rowMap.get("Materialangaben");
        this.Geschlecht     = rowMap.get("Geschlecht");

        this.Produktart = rowMap.get("Produktart");
        this.Ärmel  = rowMap.get("Ärmel");
        this.Bein   = rowMap.get("Bein");
        this.Kragen = rowMap.get("Kragen");
        this.Herstellung    = rowMap.get("Herstellung");
        this.Taschenart = rowMap.get("Taschenart");
        this.Grammatur  = rowMap.get("Grammatur");
        this.Material   = rowMap.get("Material");
        this.Ursprungsland  = rowMap.get("Ursprungsland");
        this.Bildname   = rowMap.get("Bildname").replace("\\.{0,1}", "");

    }

    public void toArtikel(Object[] array){
        for (int i = 0; i < array.length; i++) {
            String key = HEADERS[i];
            String value = array[i].toString();
//            if( value.isEmpty() || value == null){
                rowMap.put(key, null );
//            }
//            else{
//
//            }

        }
    }


    @Override
    public String toString() {
        return "Hauptartikelnr: " + Hauptartikelnr + "ArtikelName: "+ ArtikelName
                + "Hersteller: " + Hersteller+ "Beschreibung: " + Beschreibung + "Materialangaben: " + Materialangaben
                + "Geschlecht: " + Geschlecht+ "Produktart: " + Produktart+ "Ärmel: " + Ärmel
                + "Bein: " + Bein+ "Kragen: " + Kragen + "Herstellung: " + Herstellung + "Taschenart: " + Taschenart+ "Grammatur: " + Grammatur
                + "Material: " + Material+ "Ursprungsland: " + Ursprungsland + "Bildname: " + Bildname.replace("\\.{0,1}", "");
    }

    public String[] toArray(){
        return new String[]{Hauptartikelnr.replace(".{1}", ""),ArtikelName,Hersteller,Beschreibung
                ,Materialangaben, Geschlecht, Produktart, Ärmel,Bein
                , Kragen, Herstellung, Taschenart, Grammatur, Material, Ursprungsland, Bildname.replace("\\.{0,1}", "")};
    }

    @Override
    public String getItem(int index) {
        String item = "";
        switch (index){
            case 0:
                item = getHauptartikelnr();
                break;

            case 1:
                item = getArtikelName();
                break;
            case 2:
                item = getHersteller();
                break;
            case 3:
                item = getBeschreibung();
                break;

            case 4:
                item = getMaterialangaben();
                break;
            case 5:
                item = getGeschlecht();
                break;

            case 6:
                item = getProduktart();
                break;

            case 7:
                item = getÄrmel();
                break;

            case 8:
                item = getBein();
                break;


            case 9:
                item = getKragen();
                break;


            case 10:
                item = getHerstellung();
                break;


            case 11:
                item = getTaschenart();
                break;

            case 12:
                item = getGrammatur();
                break;


            case 13:
                item = getMaterial();
                break;

            case 14:
                item = getUrsprungsland();
                break;

            case 15:
                item = getBildname();
                break;

        }

        return item;
    }

    public Artikel toMap(){
        return this;
    }


    public String getHauptartikelnr() {
        return Hauptartikelnr;
    }

    public String getArtikelName() {
        return ArtikelName;
    }

    public String getHersteller() {
        return Hersteller;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public String getMaterialangaben() {
        return Materialangaben;
    }

    public String getGeschlecht() {
        return Geschlecht;
    }

    public String getProduktart() {
        return Produktart;
    }

    public String getÄrmel() {
        return Ärmel;
    }

    public String getBein() {
        return Bein;
    }

    public String getKragen() {
        return Kragen;
    }

    public String getHerstellung() {
        return Herstellung;
    }

    public String getTaschenart() {
        return Taschenart;
    }

    public String getGrammatur() {
        return Grammatur;
    }

    public String getMaterial() {
        return Material;
    }

    public String getUrsprungsland() {
        return Ursprungsland;
    }

    public String getBildname() {
        return Bildname;
    }

    public int getHauptartikelnrIndex(){
        return 0;
    }
    public int getArtikelNameIndex(){
        return 1;
    }
    public int getHerstellerIndex(){
        return 2;
    }
    public int getBeschreibungIndex(){
        return 3;
    }
    public int getMaterialangabenIndex(){
        return 4;
    }
    public int getGeschlechtIndex(){
        return 5;
    }
    public int getProduktartIndex(){
        return 6;
    }
    public int getÄrmelIndex(){
        return 7;
    }
    public int getBeinIndex(){
        return 8;
    }
    public int getKragenIndex(){
        return 9;
    }
    public int getHerstellungIndex(){
        return 10;
    }
    public int getTaschenartIndex(){
        return 11;
    }
    public int getGrammaturIndex(){
        return 12;
    }
    public int getMaterialIndex(){
        return 13;
    }
    public int getUrsprungslandIndex(){
        return 14;
    }
    public int getBildnameIndex(){
        return 15;
    }

}