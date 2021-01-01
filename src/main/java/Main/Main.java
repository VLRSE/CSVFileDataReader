package Main;

import DarkThemeComponents.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import otherClasses.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 *
 * @author Vilrose Daquiado
 */

public class Main {

    private static DarkFrame frame;
    private static JPanel centerPanel;
    private static JButton btnImport, btnStart, btnCancel;
    private static final String CHAR_TO_REPLACE = "[[\\W] &&[^\\s]&& [^\\p{IsLatin}] && [^[%---'/] ]]";

    private static JProgressBar progressBar;
    private static JTextArea progressUpdate, fileSizeText, filenameLabel;
    private static DashBoardMenuBar menuBar;
    private static DarkMenu menuDatei;
    private static DarkMenu menuChart;
    private static DarkMenu menuNeu;
    private static JTabbedPane tabbedPane;

    private static List<Path> importedFiles;
    private  static Map< String, List<Artikel>> importedFilesAndRecordsMap;
    private  static Map<String, DefaultTableModel > importedFilesAndTableModelMap;//imported files and its list of data streamed
    private static String[] header;
    private static DefaultTableModel tableModel;
    private static final Color PRIMARY_COLOR = Color.decode("#42A5F5");
    private static final Font BUTTON_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    private static final String[] columnIdentifiers = new String[]{"Hauptartikelnr", "ArtikelName", "Hersteller"
            , "Beschreibung", "Materialangaben", "Geschlecht", "Produktart", "Ärmel"
            , "Bein", "Kragen", "Herstellung", "Taschenart", "Grammatur", "Material", "Ursprungsland", "Bildname"};

    private static List<DarkTable> tables ;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                //create and show an instance of an extended JFrame class called DarkFrame
                createGUIAndShow();

                //@variable btnImport add Action Listener to execute Filechooser and allow user to import a csv file
                btnImport.addActionListener(e -> {
                    //disable button to avoid duplicate import
                    btnImport.setEnabled(false);

                    //call the executeFileChooser method to  show the FileChooser dialogbox
                    // to let user choose a file to import
                    try {
                        executeFileChooser();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
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
    public static void createGUIAndShow() throws IOException{
        frame = new DarkFrame("CSV File Data Reader");
        frame.setForeground(Color.white);
        frame.setVisible(true);
        frame.pack();

        initComponents();
    }

    //a method to get references to the button components of the frame and other outer Classes´ components
    public static void initComponents() throws FileNotFoundException{

        ProgressBarPanel progressBarPanel;

        //call addDashboardButtons method to add the menuBar to the dashboard panel (left side panel of the frame)
        addDashboardButtons();

        //get import button reference from ImportPanel class int the frame to allow user to import CSV files
        btnImport = frame.getImportPanel().getBtnImport();

        //get the references to the components on the ProgressBarPanel to be used for the progressBar updates
        progressBarPanel = frame.getProgressBarPanel();
        btnStart =  progressBarPanel.getBtnStart();
        btnCancel = progressBarPanel.getBtnCancel();
        progressUpdate = progressBarPanel.getProgressUpdate();
        progressBar = progressBarPanel.getProgressBar();
        fileSizeText = progressBarPanel.getFileSize();
        filenameLabel = progressBarPanel.getFilenameLabel();

        /*get the references to the tabbedPane from the frame to be used to display the
            Tabular representation of the files*/
        tabbedPane = frame.getTabbedPane();

        /*Initialize a list and Map Collections with key as the Path of the imported file
        * and value as the streamed list of Artikel*/
        importedFilesAndTableModelMap = new HashMap<>();
        importedFilesAndRecordsMap =  new HashMap<>();
        //        importedFiles = new ArrayList<>();

        //a list where to save the tables that will be created for every csv file imported
        tables = new ArrayList<>();
    }

    /*a method that creates DashBoardButton instances to be added to the dashboard(left panel)
        panel container of the frame*/
    public static void addDashboardButtons(){

        //get the references to the components on the dashboard to be used as menuBar on the left side of the frame
        menuBar = frame.getDashBoardMenuBar();
        menuBar.setLayout( new BoxLayout(menuBar, BoxLayout.Y_AXIS));
        menuBar.setOpaque(false);

        //get the reference to the Menus on the menuBar
        menuDatei = menuBar.getMenuDatei();

        menuChart = menuBar.getMenuDiagramm();
        menuChart.getMenuButton().addActionListener(l ->{
            if(!importedFilesAndRecordsMap.isEmpty() && importedFilesAndRecordsMap != null){

            /*call the createChartsPanel method to create PieChart3D and a barchart
            * and to create a JPanel instance to hold the created a PieChart3D and a barchart
            */
            JPanel panelCharts;
            panelCharts = createChartsPanel();

//            createChart("Artikel");

            //add the panelCharts to the tabbedPane as selected component view
            TinyImageIcon icon = new TinyImageIcon(Main.class.getResource("/images/chart.png"));
            tabbedPane.add(panelCharts, icon);
            int selectedIndex = tabbedPane.getComponentCount() - 1;
            tabbedPane.setSelectedIndex( selectedIndex);

            }
            else{
                //if no files imported yet, a Message dialog box will be shown
                JOptionPane.showMessageDialog(frame, "Noch keine Datei importiert");
            }
        });

        menuNeu = menuBar.getMenuNeuTabelle();

        menuNeu.getMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel1 = new DefaultTableModel();
                tableModel1.setColumnCount(16);
                tableModel1.setRowCount(30);
                tableModel1.setColumnIdentifiers(columnIdentifiers);

                //add Listener to the table model to be notified for changes fired in the table cells
                tableModel1.addTableModelListener((TableModelEvent l) -> {
                int row = l.getFirstRow();
                int column = l.getColumn();

//                System.out.println( "row " + row + " column "+column +" value " + tableModel1.getValueAt(row, column));

                });

                //call the addTabAndItem to create a table instance with the @parameter tableModel1
                // and Untitled as the tabbedPane title
                addTabAndItem("Untitled",tableModel1 );

            }
        });
    }


    public static JFreeChart createPieChart(String chartTitle){

        String hersteller ;
        double herstellerCount;
        double max = 0.0;

        //get the tabbedPane´s selected title (filename) to be used to get the Artikel list
        String selectedIndex = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        Map<String, Long> herstellersList = importedFilesAndRecordsMap.get(selectedIndex).stream()
                .collect(Collectors.groupingBy(Artikel::getHersteller, Collectors.counting()));

        // @param herstellers collected list of distinct hersteller names or  keys from he herstellerslist
       Object[] herstellers = herstellersList.keySet().toArray();

        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; i < herstellers.length; i++) {
           hersteller = ""+herstellers[i];
           herstellerCount = herstellersList.get(hersteller).doubleValue();

           dataset.setValue(hersteller, herstellerCount);
        }

        //creating an instance of a PieChart3D with modified Dark UI
        DarkPieChart3D pieChart3D = new DarkPieChart3D(chartTitle, dataset);
        int maxValue = Collections.max(herstellersList.values()).intValue();

        return pieChart3D.getChart();
    }

    public static JPanel createChartsPanel(){
        String hersteller ;
        double herstellerCount;
        double max = 0.0;

        //a panel that will hold all of the charts
        JPanel panelCharts = new JPanel();
        panelCharts.setLayout(new GridLayout(1,2));

//        //create a PieChart for the chart representation of the Herstellers from the csv file
        JFreeChart pieChart = createPieChart("Hersteller");
        ChartPanel chartPanel =  new ChartPanel(pieChart);
        chartPanel.setBackground(DarkThemeColor.PRIMARY_BG_COLOR);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.validate();
        chartPanel.repaint();
        chartPanel.setLayout(new GridLayout(1,1));

       // create a BarChart for the chart representation of the Materials of the Articles from the csv file
        ChartPanel barChartPanel = new ChartPanel(createBarChart("Material"));
        barChartPanel.setPreferredSize(new Dimension(panelCharts.getWidth() /4, panelCharts.getHeight() /4));
        barChartPanel.setLayout(new GridLayout(1,1));
        String selectedIndex = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        Map<String, Long> herstellersList = importedFilesAndRecordsMap.get(selectedIndex).stream()
                .collect(Collectors.groupingBy(Artikel::getHersteller, Collectors.counting()));

        // @param herstellers collected list of distinct hersteller names or  keys from he herstellerslist
        Object[] herstellers = herstellersList.keySet().toArray();

        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; i < herstellers.length; i++) {
            hersteller = ""+herstellers[i];
            herstellerCount = herstellersList.get(hersteller).doubleValue();

            dataset.setValue(hersteller, herstellerCount);
        }

        //creating an instance of a PieChart3D with modified Dark UI
        DarkPieChart3D pieChart3D = new DarkPieChart3D("Artikel", dataset);
        int maxValue = Collections.max(herstellersList.values()).intValue();

        //add the charts to the panelCharts to layout them
        panelCharts.add(barChartPanel, BorderLayout.LINE_END);
        panelCharts.add(chartPanel, BorderLayout.LINE_START);

        return panelCharts;

    }
    public static JFreeChart createBarChart(String title){
//
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //get the title (filename) of the selected index on tabbedPane to create its bargraph representation
        String selectedIndexFilename = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());

        Map<String, Long> materialList = importedFilesAndRecordsMap.get(selectedIndexFilename).stream()
                .collect(Collectors.groupingBy(Artikel::getMaterial, Collectors.counting()));

        Map<String, Long> geschlectList = importedFilesAndRecordsMap.get(selectedIndexFilename).stream()
                .collect(Collectors.groupingBy(Artikel::getGeschlecht, Collectors.counting()));

//        dataset.setGroup(new DatasetGroup("material"));
        for (Object material: materialList.keySet()) {
            dataset.setValue(materialList.get(material).doubleValue(), material.toString(), "Material" );
        }

        // @param herstellers collected list of distinct hersteller names or  keys from the herstellerslist
       Object[] materials = materialList.keySet().toArray();

        JFreeChart barchart = ChartFactory.createBarChart(title, "Category", "Zahl",dataset
                , PlotOrientation.VERTICAL, true, true, false);

        return barchart;

    }

    /*
    *a method that will instantiate a FileChooser object, get the path of every file imported,
    * get the filesize and the filesize type(KB,MB,GB). This method also updates the filenameLabel
    * and triggers the btnStart (to start file import)
    */

    public static void executeFileChooser() throws IOException {
        //show fileChooser dialog box
        FileChooser fileChooser = new FileChooser(centerPanel);
        /*
         *get get filename and change the filename label´s text with filename´s string
         *enable start button
         */
        if(fileChooser.getReturnVal() == JFileChooser.APPROVE_OPTION){
            btnStart.setEnabled(true);

            //enable start button to import file
            btnStart.setForeground(PRIMARY_COLOR);
            btnCancel.setEnabled(true);

            /*TODO: Make cancel button work to cancel file import*/
//
//            btnCancel.addActionListener( l -> {
//                task.cancel(true);
//                resetComponents();
//            });

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
        btnStart.setEnabled(false);

        filenameLabel.setText("Keine Datei ausgewählt!");
        fileSizeText.setText("");
        progressUpdate.setText("");
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        progressBar.setValue(0);

    }

    public static void btnStartActionPerformed(FileChooser fileChooser) throws IOException, ExecutionException, InterruptedException {
        Path path;
        String fileType;
        float fileSize = 0;
        ProgressTask task;

        path = fileChooser.getFile().toPath();
        /*TODO: check if file had been already imported*/

//           boolean fileExists =  checkAlreadyImported(path);


        //calculate the file size
        fileSize = fileChooser.getFileSize();
        //get whether the file size type is in KB, MB or GB
        fileType = fileChooser.getSizeType();
        //add the filename and the path of the file to be imported
//            filenameAndPath.put(filename, path);

        /*TODO: Check if map does not necessarily need to be added to a List object*/

        //change the text of filenameLabel
        String filename = path.getFileName().toString();
        filenameLabel.setText(filename);

        //wait cursor enabled
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        if(importedFilesAndRecordsMap.size() != 0 && importedFilesAndRecordsMap.get(importedFilesAndRecordsMap.size()-1).equals(path)){
//            resetComponents();
            return;

        }else{
            //add the imported file´s path to the importedFiles List
//            importedFiles.add(path);
            //show file´s size and the progressBar update
            String stringFormatted = String.format("%.02f",  fileSize );
            fileSizeText.setText(""+stringFormatted  + fileType+ " of " +stringFormatted +" " + fileChooser.getSizeType());

            tableModel = new DefaultTableModel() ;
            task = new ProgressTask(tableModel, path);
            progressBar.setBorderPainted(true);

            //Checks whether the file already exists in the imported file text file
            task.addPropertyChangeListener((PropertyChangeEvent evt) -> {

                if ("progress".equals(evt.getPropertyName())) {
                    int progress = (Integer) evt.getNewValue();          //get the reference value of the progress´new value
                    progressBar.setValue(progress);                     //set the progressBar value
                    progressUpdate.setText(String.format(" %d%% fertig"  //update the progressUpdate text
                            , task.getProgress()));


                    if(task.isDone() && task.isCancelled() == true){

                    }
                    else if(task.isDone() && task.isCancelled() == false){
                        //call addTabAndItem method to add a new Tab item with filename as the title and the table for the content


                        try {
                            //get the returned List by the Swingworker containing the List of Artikel records
                            List<Artikel>streamedRecords = task.get();

                            addTabAndItem(filename, tableModel);


                            importedFilesAndRecordsMap.put(filename, streamedRecords);
                          importedFilesAndTableModelMap.put(filename, tableModel);
                           int lastIndex = importedFilesAndRecordsMap.size()-1;
//                            importedFilesMap.put(path, tableModel);

                        } catch (InterruptedException e) {
                            return;

                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
//                        resetComponents();
                    }



                    if(task.isCancelled() == true){
                        resetComponents();
                    }
                }
            });
            //execute the background task to process the file and update the progressBar value
            task.execute();

            frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            //add the headers String array as column identifiers to ready the table for data entries
            tableModel.setColumnIdentifiers(columnIdentifiers);
            return;
        }

    }
    /*------- Method to add a tabular representation of the file date  or a new tabular data of the same
    ** -------schema to the tabbedPane and is set as the selected index. The filename is set as the tabbed title
    * */
    public static void addTabAndItem(String filename, DefaultTableModel tableModel) {

        int tabCount = tabbedPane.getComponentCount();

        //remove the import panel
       if(tabCount == 1 && !filename.equalsIgnoreCase("Untitled")){
           tabbedPane.remove(tabCount-1);
       }
        tabbedPane.addTab(filename, createTable( tableModel));
        int currentViewTab = tabbedPane.getComponentCount() - 1;
        tabbedPane.setSelectedIndex(currentViewTab);

        //add a menuItem to the menubar with the newly-imported file´s name
        addMenuItem(filename);
    }

    /*------- Method to add the filename of the newly created table to the menu on the dashboard on the left panel
     * */

    public static void addMenuItem(String title) {
        DarkMenuItem menuItem = new DarkMenuItem(title, tabbedPane.getSelectedIndex());

        menuItem.setIcon(new TinyImageIcon(Main.class.getResource("/images/document-coloured.png")));
        menuDatei.addItem(menuItem);
    }

    /*------- Method that creates a JScrollPane and return the its object containing a created table isntance
     * */

    public static JScrollPane createTable(DefaultTableModel tableModel) {

        JScrollPane scrollPane;
        DarkTable table;

        table = new DarkTable(tableModel);

        //add tableModelListener to the table´s tablemodel to get the location of the edited data
        tableModel.addTableModelListener(evt ->{

           int column =  evt.getColumn();
           int row = evt.getFirstRow();
       });


        scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));//
        table.setFillsViewportHeight(true);
        scrollPane.setViewportView(table);

        //add the scrollPane containing the table to the tabbedPane and set as selected index
        tabbedPane.add(scrollPane);
        int position = tabbedPane.getComponentCount() - 1;
        tabbedPane.setSelectedIndex(position);

        return scrollPane;
    }

    /*
    * Method to create JFreeChart instance and return a panel that contains it
    * */


}
