package Main;

import DarkThemeComponents.*;
import otherClasses.Artikel;
import otherClasses.DefaultArtikelTableModel;
import otherClasses.FileChooser;
import otherClasses.ProgressTask;

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
    private static JButton btnImport, btnStart, btnClose, btnMax, btnMin, btnReduce;
    private static Path path;
    private static String filename, fileType;
    private static float fileSize = 0;
    private static JLabel filenameLabel;
    private static final String CHAR_TO_REPLACE = "[[\\W] &&[^\\s]&& [^\\p{IsLatin}] && [^[%---'/] ]]";
    private static ProgressTask task;
    private static JProgressBar progressBar;
    private static JTextArea progressUpdate, fileSizeText;
    private static java.util.List<Artikel> aList;
    private static FileMenuBar menuBar;
    private static JMenu menu;
    private static JTabbedPane tabbedPane;
    private static List<Map<String, Path>> importedFiles;
    private  static    Map<String, Path> filenameAndPath;
    private static String[] header;
    private static DefaultArtikelTableModel tableModel;
    private  static JTable table;
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
                //get buttons and other components´references from other outer classes
                initComponents();

                btnImport.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnImport.setEnabled(false);
                        //show otherClasses.FileChooser dialogbox to let user choose a file to import
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
        //get the references to the main panels of the DarkFrame class
        centerPanel = frame.getCenterPanel();
        dashboard = frame.getDashboardPanel();

        dashboard.setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));
        //add a menubar and JButton´s instances to the dashboard on the left side of the frame

        addDashboardButtons();

        try {
            //add the center panel component for the start page
            addStartPage(createImportPanel()) ;
        }
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        frame.setVisible(true);
        frame.setResizable(true);
        frame.pack();
    }

    //create a JPanel instance that will hold the importPanel and the progressBarPanel
    public static JPanel createImportPanel() throws FileNotFoundException{
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int width = (int)(centerPanel.getWidth()*0.5);
        int height = (int)(centerPanel.getHeight()*0.5);
        Dimension dm = new Dimension(width, height);

        panel.setBackground(centerPanel.getBackground());

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;

        //get the reference to the Import button on the frame
        importPanel = new ImportPanel(centerPanel);
        importPanel.setPreferredSize(dm);
        panel.add(importPanel, c);

        //create new ProgressBarPanel instance and passing the new Dimension instance with witdth same as of the importPanel
        progressBarPanel = new ProgressBarPanel(dm);
        c.gridy = 5;
        panel.add(progressBarPanel,c);

        return panel;
    }

    //a method that creates DashBoardButton instances to be added to the dashboard(left panel) panel container of the frame
    public static void addDashboardButtons(){
        JMenu btnDatei, btnTable, btnChart;
        JPanel panel;
        GridBagConstraints c;
        JButton newTableButton;

        dashboard.setLayout(new GridBagLayout());

        btnDatei = new JMenu("Datei");
        menuBar = new FileMenuBar(btnDatei);
//        menu = menuBar.getMenu();
        JMenuItem menuItem = new JMenuItem();


        btnTable = new JMenu("Tabelle");
        btnChart = new JMenu("Chart");


//        panel = new JPanel(new GridBagLayout());
//
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0;
        c.gridx = 0;
        dashboard.add(menuBar,c );

        c.ipady = 10;
        c.gridy = 4;
        c.gridx = 0;

        newTableButton = new JButton("NEW");
        newTableButton.setOpaque(false);
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
        dashboard.add(newTableButton,c);



    }

    //a method to get references to the button components of the frame and other outer Classes´ components
    public static void initComponents() throws FileNotFoundException{
        //get the references of the main buttons on the upper-right of the frame
        btnClose = frame.getBTNExit();
        btnReduce = frame.getBTNReduceSize();
        btnMin = frame.getBTNMinimize();
        btnMax = frame.getBTNMaximize();

        //get import button reference from ImportPanel class
        btnImport = importPanel.getBtnImport();

        //get the references to the components on the ProgressBarPanel
        btnStart = progressBarPanel.getBtnStart();
        progressUpdate = progressBarPanel.getProgressUpdate();
        progressBar = progressBarPanel.getProgressBar();
        fileSizeText = progressBarPanel.getProgressSize();
        filenameLabel = progressBarPanel.getFilenameLabel();

        importedFiles = new ArrayList<>();
       filenameAndPath= new HashMap<>();
    }

    //a method that creates an ImportPanel,ProgressBarPanel and MyTabbedPane instances to and adds them to the centerPanel container
    public static void addStartPage(Component com) throws FileNotFoundException, IOException {
        //change the background color of the selected panel on the tabbedPane
        UIManager.put("TabbedPane.selected", PRIMARY_COLOR);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Start Seite" , com);
        tabbedPane.setForegroundAt(0, Color.white);
        tabbedPane.setOpaque(false);
        tabbedPane.getSelectedComponent().setForeground(Color.white);
        tabbedPane.setBackground(Color.darkGray);


        centerPanel.add(tabbedPane);
    }

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
            filenameLabel.setText(filename.toString());
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



        //Checks whether the file already exists in the imported file text file
        task.addPropertyChangeListener((PropertyChangeEvent evt) -> {


            if ("progress".equals(evt.getPropertyName())) {
                int progress = (Integer) evt.getNewValue();          //get the reference value of the progress´new value
                progressBar.setValue(progress);                     //set the progressBar value
                progressUpdate.setText(String.format(" %d%% fertig"  //update the progressUpdate text
                        , task.getProgress()));

//                tabbedPane.addTab(filename, );
                if(task.isDone()){
                  //add a new Tab item with filename as the title and the table for the content
                    addTabAndItem(filename, tableModel);


//                        aList = task.get();

                        System.out.println( tableModel.getDataVector().get(2));

//                        System.out.println(aList.get(1).toMap().get("Hauptartikelnr"));
                        //create and display the table with the csv file records
                        String filename1 = path.getFileName().toString();
                        //reset the components at the start page to prepare for new import
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
        tabbedPane.addTab(filename, createTable( tableModel));


        int position = tabbedPane.getComponentCount() - 1;
        tabbedPane.setSelectedIndex(position);
        tabbedPane.setForegroundAt(position, Color.darkGray);
        //add a menuItem to the menubar with the newly-imported file´s name
        addMenuItem(filename);
    }

    public static void addMenuItem(String title) {
        DarkMenuItem menuItem = new DarkMenuItem(title, tabbedPane);
        JMenuItem add = menuBar.getMenu(0).add(menuItem);
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
