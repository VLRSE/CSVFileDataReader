package otherClasses;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Vilrose Daquiado
 */


public class ProgressTask extends SwingWorker<List<Artikel>, Artikel> {

    private final Path sourcePath;
    public DefaultTableModel tableModel;
    private int progress = 0;
    private List<Artikel> aList;

    private String result = "";
    private JTextArea textArea;

    private final String[] HEADER = new String[]{"Hauptartikelnr", "ArtikelName", "Hersteller"
            , "Beschreibung", "Materialangaben", "Geschlecht", "Produktart", "Ärmel"
            , "Bein", "Kragen", "Herstellung", "Taschenart", "Grammatur", "Material"
            , "Ursprungsland", "Bildname"};


    public ProgressTask(DefaultTableModel tableModel, Path sourcePath) {

        this.tableModel = tableModel;
        this.sourcePath = sourcePath;
        this.textArea = textArea;

    }

    @Override
    protected List<Artikel> doInBackground() throws Exception {


        /* CSVParser from the com.apache.commons library
         *get a CSV parser for a given file with a CSV Format Excel with delimiter(;)
         * and passing HEADER as the parameter for the default header to the parser to be used for CSV parsing
         * */
        CSVParser parser;
        CSVFormat fmt = CSVFormat.EXCEL.withDelimiter(';');
        parser = CSVParser.parse(sourcePath.toFile(), StandardCharsets.UTF_8
                , fmt.withHeader(HEADER).withIgnoreEmptyLines());

        //stream the CSV parsed records
        Stream<CSVRecord> csvRecordStream = StreamSupport.stream(parser.spliterator(), false);
        if (Thread.interrupted()) {
            System.out.println("We've been interrupted: no more crunching.");

        }
        //collect the Artikel records into a list Collection
        List<Artikel> artikelArray = csvRecordStream.skip(1)
                .map(CSVRecord::toMap)                    //map the csvRecord entries
                .map(csvRecord -> new Artikel(csvRecord))     //create a new otherClasses.Artikel for every entry
                .collect(Collectors.toList());


        long recordsToFind = artikelArray.size();
        for (Artikel input : artikelArray) {

            Thread.sleep(100);
            //publish the lines to be able to process as chunks (allow entry to be added to the table)
            publish(input);
            progress++;

            //updates the progressbar. Showing how much of the total records was processed
            setProgress((int) (100 * progress / recordsToFind));

        }
        //return the Artikel record list to be able to retrieve when needed after the progress is done
        return artikelArray;
    }

    @Override
    protected void process(List<Artikel> chunks) {
        //get the record that the doBackground method was published
        Artikel record = chunks.get(chunks.size() - 1);

        result = record.toString() + "\n";

        //update tableModel by adding records
        tableModel.addRow(record.toArray());

    }

    @Override
    protected void done() {
        if (Thread.interrupted()){
            System.out.println("thread has been interrupted");
        }


    }

}
