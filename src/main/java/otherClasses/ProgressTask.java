package otherClasses;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Vilrose Daquiado
 */


public class ProgressTask extends SwingWorker< List<Artikel>, Artikel> {

    private final Path sourcePath;
    public DefaultTableModel tableModel;
    private int progress = 0;
    private List<Artikel> aList;

    private String result = "";
    private JTextArea textArea;

    private String[] header;


    public ProgressTask(DefaultTableModel tableModel, Path sourcePath) {

        this.tableModel = tableModel;
        this.sourcePath =sourcePath;
        this.textArea = textArea;
         header = new String[]{"Hauptartikelnr", "ArtikelName", "Hersteller"
                 , "Beschreibung", "Materialangaben", "Geschlecht", "Produktart", "Ärmel"
                 , "Bein", "Kragen", "Herstellung", "Taschenart", "Grammatur", "Material", "Ursprungsland", "Bildname"};

    }

    @Override
    protected  List<Artikel> doInBackground() throws Exception {


        CSVParser parser;
        CSVFormat fmt = CSVFormat.EXCEL.withDelimiter(';');
        parser = CSVParser.parse(sourcePath.toFile(), Charset.forName("UTF-8")
                , fmt.withHeader(header).withIgnoreEmptyLines());





        //get a list Collection of the CSVRecords
        Stream<CSVRecord> csvRecordStream = StreamSupport.stream(parser.spliterator(), false);


        List<Artikel> artikelArray =  csvRecordStream.skip(1)
                .map(CSVRecord::toMap)                    //map the csvRecord entries
             .map(csvRecord -> new Artikel(csvRecord))     //create a new otherClasses.Artikel for every entry
         .collect(Collectors.toList());
//collect the artikel entries into a list Collection




    long recordsToFind = artikelArray.size();
        for(Artikel input : artikelArray){
//            System.out.println(input.toString());
            Thread.sleep(100);


            publish(input);
            progress++;

//            System.out.println(recordsToFind+ " progress " +progress+" percent "+  100 * progress/recordsToFind);

            setProgress((int) (100 * progress/recordsToFind));





        }

        return artikelArray;
    }

    @Override
    protected void process(List<Artikel> chunks) {
        Artikel record = chunks.get(chunks.size()-1);


        result  = record.toString() + "\n";
//        System.out.println(result.toString());

        //update tableModel by adding entries
        tableModel.addRow(record.toArray());



    }

    @Override
    protected void done() {

        JOptionPane.showMessageDialog(null, "Datei wurde importiert", "Import erfolgreich", JOptionPane.INFORMATION_MESSAGE);



    }
}
