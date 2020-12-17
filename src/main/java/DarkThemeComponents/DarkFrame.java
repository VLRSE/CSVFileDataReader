package DarkThemeComponents;

import otherClasses.DarkThemeColor;
import otherClasses.TinyImageIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.Locale;

import static otherClasses.DarkThemeColor.PRIMARY_BG_COLOR;
import static otherClasses.DarkThemeColor.SECONDARY_BG_COLOR;

public class DarkFrame extends JFrame {

    private JPanel dashboard, centerPanel, topPanel, bottomPanel, rightPanel;
    private CustomizedTabbedPane tabbedPane;
    private  ImportPanel importPanel;
    private ProgressBarPanel progressBarPanel;
    private DashBoardMenuBar menuBar;

    public DarkFrame(String title) throws FileNotFoundException {

        super(title);

        super.setMinimumSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2
        ),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)) );

        super.setResizable(true);
        super.getContentPane().setBackground(DarkThemeColor.PRIMARY_BG_COLOR);
        super.setLocale(Locale.GERMANY);

        //position the frame in the middle of the screen.

        super.setLocationRelativeTo(null);

        init();
        super.pack();

    }


    private void init() throws FileNotFoundException {
        this.setLayout(new BorderLayout(0, 1));

        topPanel = createDarkPanel("toppanel");
        dashboard = createDarkPanel("dashboard");
        centerPanel = createDarkPanel("centerpanel");
        rightPanel = createDarkPanel("rightPanel");
        bottomPanel = createDarkPanel("bottomPanel");

        Component[] com = {topPanel,dashboard, centerPanel,rightPanel, bottomPanel  };
        String[] positions = {BorderLayout.PAGE_START, BorderLayout.LINE_START, BorderLayout.CENTER, BorderLayout.LINE_END, BorderLayout.SOUTH };

        addTopPanelComponents(topPanel);

        //add a Menubar and the its menus to the dashboard of the frame
        addDashboardComponents(dashboard);
        addCenterPanelComponents(centerPanel);

        //call the method to add all components to the frame
        addMainPanels(com, positions);


    }

    private JPanel createDarkPanel(String name){
        JPanel panel;
        int frameWidth, frameHeight;

        panel = new JPanel();
        panel.setBackground(SECONDARY_BG_COLOR);

        frameWidth = this.getWidth();
        frameHeight = this.getHeight();

        switch (name.toLowerCase()){

            case "toppanel":
                panel.setPreferredSize(new Dimension(frameWidth, (int)frameHeight/18));
                break;

            case "dashboard":
                //set the width of dashboard Panel 1/4 of the total frame´s width, and the full height
                panel.setMinimumSize(new Dimension((int)(frameWidth/8),frameHeight));
                break;
        }

        return panel;
    }


    private void addMainPanels(Component[] components, String[] positions){
        for (int i = 0; i <components.length; i++){
            this.getContentPane().add(components[i], positions[i]);
        }

    }
    private void addDashboardComponents(JPanel dashboard){

        menuBar = new DashBoardMenuBar();
        dashboard.add(menuBar);

    }

    private void addTopPanelComponents(JPanel topPanel){
        //add Menubar on topPanel
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(new DashBoardButton("File"));
        topPanel.add(new DashBoardButton("Edit"));
        
    }

    private void addCenterPanelComponents(JPanel centerPanel) throws FileNotFoundException {

        JPanel panel;


        panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        centerPanel.setLayout(new GridLayout(1, 1));

    //change the background color of the selected panel on the tabbedPane
        UIManager.put("TabbedPane.selected", SECONDARY_BG_COLOR);
        tabbedPane = new CustomizedTabbedPane();

        panel.setLayout(new GridBagLayout());
        panel.setBackground(PRIMARY_BG_COLOR);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;


        //get the reference to the Import button on the frame
        importPanel = new ImportPanel();

        panel.add(importPanel, c);

        c.gridx = 0;
        c.gridy = 1;

        progressBarPanel = new ProgressBarPanel(importPanel.getWidth());
        panel.add(progressBarPanel, c);

        TinyImageIcon icon = new TinyImageIcon(getClass().getResource("/images/document-coloured.png"));

        tabbedPane.addTab("Start Seite", panel);
        tabbedPane.setIconAt(0,icon );
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_S);

        centerPanel.add(tabbedPane);

    }

    public JPanel getDashboard() {
        return dashboard;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public CustomizedTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public ImportPanel getImportPanel() {
        return importPanel;
    }

    public ProgressBarPanel getProgressBarPanel() {
        return progressBarPanel;
    }


    public DashBoardMenuBar
    getDashBoardMenuBar() {
        return menuBar;
    }

}


