package Main;

import DarkThemeComponents.*;

import otherClasses.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Vilrose Daquiado
 */

public class Main {

    private static DarkFrame frame;
    private static JPanel dashboard, centerPanel;
    private static ImportPanel importPanel;
    private static ProgressBarPanel progressBarPanel;
    private static JButton btnImport, btnStart;
    private static Path path;
    private static String filename, fileType;
    private static float fileSize = 0;
    private static final String CHAR_TO_REPLACE = "[[\\W] &&[^\\s]&& [^\\p{IsLatin}] && [^[%---'/] ]]";
    private static ProgressTask task;
    private static JProgressBar progressBar;
    private static JTextArea progressUpdate, fileSizeText, filenameLabel;
    private static List<Artikel> aList;
    private static DashBoardMenuBar menuBar;
    private static DashBoardButton menuDatei, menuTabelle, menuEinstellungen, menuNeu;
    private static JTabbedPane tabbedPane;
    private static List<Map<String, Path>> importedFiles;
    private  static Map<String, Path> filenameAndPath;
    private static String[] header;
    private static DefaultArtikelTableModel tableModel;
    private static final Color PRIMARY_COLOR = Color.decode("#42A5F5");
    private static final Font BUTTON_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    private static final String[] columnIdentifiers = new String[]{"Hauptartikelnr", "ArtikelName", "Hersteller"
            , "Beschreibung", "Materialangaben", "Geschlecht", "Produktart", "Ärmel"
            , "Bein", "Kragen", "Herstellung", "Taschenart", "Grammatur", "Material", "Ursprungsland", "Bildname"};



    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                //create and show an instance of an extended JFrame class called DarkFrame
                createGUIAndShow();

                btnImport.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //shows the FileChooser dialogbox to let user choose a file to import
                        executeFileChooser();
                    }
                });
            }catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    //create the GUI and show
    public static void createGUIAndShow() throws FileNotFoundException,  IOException{
        frame = new DarkFrame("CSV File Data Reader");
        frame.setForeground(Color.white);

        frame.setVisible(true);
        frame.pack();

        initComponents();
    }

    //a method to get references to the button components of the frame and other outer Classes´ components
    public static void initComponents() throws FileNotFoundException{

        ProgressBarPanel progressBarPanel;

        dashboard = frame.getDashboard();
        menuBar = frame.getDashBoardMenuBar();

        //get the reference to the Menus on the menuBar
        menuDatei = menuBar.getMenuDatei();
        menuTabelle = menuBar.getMenuTable();
        menuEinstellungen = menuBar.getMenuEinstellungen();
        menuNeu = menuBar.getMenuNewTable();


        //get import button reference from ImportPanel class int the frame to allow user to import CSV files
        btnImport = frame.getImportPanel().getBtnImport();

        //get the references to the components on the ProgressBarPanel to be used for the progressBar updates
        progressBarPanel = frame.getProgressBarPanel();

        btnStart =  progressBarPanel.getBtnStart();
        progressUpdate = progressBarPanel.getProgressUpdate();
        progressBar = progressBarPanel.getProgressBar();
        fileSizeText = progressBarPanel.getFileSize();
        filenameLabel = progressBarPanel.getFilenameLabel();

        /*get the references to the tabbedPane from the frame to be used to display the
            Tabular representation of the files*/
        tabbedPane = frame.getTabbedPane();

        /**/
        importedFiles = new ArrayList<>();
        filenameAndPath= new HashMap<>();
    }

    /*a method that creates DashBoardButton instances to be added to the dashboard(left panel)
        panel container of the frame*/
    public static void addDashboardButtons(){

        DashBoardButton newTableButton;


        newTableButton = new DashBoardButton("NEW");

        newTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tb = new DefaultTableModel();
                tb.setColumnCount(16);
                tb.setRowCount(30);
                tb.setColumnIdentifiers(columnIdentifiers);
                JTable table = new JTable(tb);

                tabbedPane.addTab("Untitled", null, new JScrollPane(table));
                int currentViewTab = tabbedPane.getComponentCount() - 1;
                tabbedPane.setSelectedIndex(currentViewTab);

            }
        });
        dashboard.add(newTableButton);

    }

    /*
    *a method that will instantiate a FileChooser object, get the path of every file imported,
    * get the filesize and the filesize type(KB,MB,GB). This method also updates the filenameLabel
    * and triggers the btnStart (to start file import)
    */

    public static void executeFileChooser(){
        //show fileChooser dialog box
        FileChooser fileChooser = new FileChooser(centerPanel);
        /*
         *get get filename and change the filename label´s text with filename´s string
         *enable start button
         */
        if(fileChooser.getReturnVal() == JFileChooser.APPROVE_OPTION){
            btnStart.setEnabled(true);

            path = fileChooser.getFile().toPath();
            filename = path.getFileName().toString();
            //calculate the file size
            fileSize = fileChooser.getFileSize();
            //get whether the file size type is in KB, MB or GB
            fileType = fileChooser.getSizeType();
            //add the filename and the path of the file to be imported
            filenameAndPath.put(filename, path);

            /*TODO: Check if map does not necessarily need to be added to a List object*/
            //add the map object with filename and path to the importedFiles List
            importedFiles.add(filenameAndPath);

            //change the text of filenameLabel
            filenameLabel.setText(filename);
            //enable start button to import file
            btnStart.setForeground(PRIMARY_COLOR);
            btnStart.setEnabled(true);
            btnStart.addActionListener((ActionEvent e) -> {
                try {

                        //call method for this action
                        btnStartActionPerformed(fileChooser);

                }
                catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (ExecutionException executionException) {
                    executionException.printStackTrace();
                }
            });
        }
        else if(fileChooser.getReturnVal() == JFileChooser.CANCEL_OPTION){
            resetComponents();
        }

    }


    public static void resetComponents(){
        btnImport.setEnabled(true);
        btnStart.setText("Start");
        btnStart.setEnabled(false);

        filenameLabel.setText("Keine Datei ausgewählt!");
        fileSizeText.setText("");
        progressUpdate.setText("");
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        progressBar.setValue(0);

    }

    public static void btnStartActionPerformed(FileChooser fileChooser) throws IOException, ExecutionException, InterruptedException {
        //wait cursor activated
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        //show file´s size and the progressBar update
        String stringFormatted = String.format("%.02f",  fileSize );
        fileSizeText.setText(""+stringFormatted  + fileType+ " of " +stringFormatted +" " +fileType);

        tableModel = new DefaultArtikelTableModel() ;
        JTable table = new JTable(tableModel);
        task = new ProgressTask(tableModel, path);

        tableModel.setColumnIdentifiers(header);

        JScrollPane scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

       JPanel panel  = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(500, 70));

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));//
        table.setFillsViewportHeight(true);
        scrollPane.setViewportView(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        progressBar.setBorderPainted(true);


        //Checks whether the file already exists in the imported file text file
        task.addPropertyChangeListener((PropertyChangeEvent evt) -> {


            if ("progress".equals(evt.getPropertyName())) {
                int progress = (Integer) evt.getNewValue();          //get the reference value of the progress´new value
                progressBar.setValue(progress);                     //set the progressBar value
                progressUpdate.setText(String.format(" %d%% fertig"  //update the progressUpdate text
                        , task.getProgress()));

//                tabbedPane.addTab(filename, );
                if(task.isDone()){
                  //call addTabAndItem method to add a new Tab item with filename as the title and the table for the content
                    addTabAndItem(filename, tableModel);

                    try {
                        //get the returned List by the Swingworker containing the Artikel arrays
                        aList = task.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    resetComponents();

                }

            }

        });
        //execute the background task to process the file and update the progressBar value
        task.execute();

        //call the calculateFileSize method to convert the fileSizeText to either KB or MB and display

//        createTable(tableModel, table);
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        //add the headers String array as column identifiers to ready the table for data entries
        tableModel.setColumnIdentifiers(columnIdentifiers);


    }

    public static void addTabAndItem(String filename, DefaultTableModel tableModel) {

        int tabCount = tabbedPane.getComponentCount();

       if(tabCount == 1){

           tabbedPane.remove(tabCount-1);

       }
        tabbedPane.addTab(filename, createTable( tableModel));
//        int position =  - 1;
//        tabbedPane.setSelectedIndex(position);
//        tabbedPane.setForegroundAt(position, Color.darkGray);
        //add a menuItem to the menubar with the newly-imported file´s name
        addMenuItem(filename);
    }

    public static void addMenuItem(String title) {
        DarkMenuItem menuItem = new DarkMenuItem(title, tabbedPane.getSelectedIndex());
        System.out.println(tabbedPane.getSelectedIndex());


    }

    public static JScrollPane createTable(DefaultTableModel tableModel) {

        JScrollPane scrollPane;
        JTable table;

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        table.setPreferredScrollableViewportSize(new Dimension(500, 70));//
        table.setFillsViewportHeight(true);
        scrollPane.setViewportView(table);

        tabbedPane.add(scrollPane);
        int position = tabbedPane.getComponentCount() - 1;
        tabbedPane.setSelectedIndex(position);
//        tabbedPane.setForegroundAt(position, Color.darkGray);
        //add a menuItem to the menubar with the newly-imported file´s name


        return scrollPane;
    }
}
