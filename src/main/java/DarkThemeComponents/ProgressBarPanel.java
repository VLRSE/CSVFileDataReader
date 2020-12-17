/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DarkThemeComponents;

import otherClasses.DarkThemeColor;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Vilrose Daquiado
 *
 *
 */
public class ProgressBarPanel extends JPanel implements ActionListener {

    private JPanel  filenamePanel;
    private JButton btnStartCancel, btnCancel;   
    private JProgressBar progressBarPanel;
    private JProgressBar  progressBar ;
    private JTextArea progressUpdate, fileSize, filenameLabel;



    private Dimension dm;
    private final Border border;
    private final Color DEFAULT_BG_COLOR,SECONDARY_COLOR, SHADOW_COLOR, TEXT_COLOR;
    private final int DEFAULT_WIDTH;
    private final Font LABEL_FONT, TEXT_FONT;

    public ProgressBarPanel(int width) {

        this.DEFAULT_WIDTH =  width;

        this.DEFAULT_BG_COLOR = Color.decode("#212121");
        this.SECONDARY_COLOR = Color.decode("#42A5F5");
        this.SHADOW_COLOR = new Color((int)SECONDARY_COLOR.getRed(), (int)SECONDARY_COLOR.getGreen(),(int) SECONDARY_COLOR.getBlue(),20);
        this.TEXT_COLOR = Color.decode("#EEEEEE");

        this.LABEL_FONT = new Font("Nunito Sans",Font.BOLD,9);
        this.TEXT_FONT = new Font("Montesarrat",Font.BOLD,10);
        this.border = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, SHADOW_COLOR.brighter(), SHADOW_COLOR.brighter()
                , SHADOW_COLOR, SHADOW_COLOR.brighter()), BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(DEFAULT_BG_COLOR);
        setBorder(border);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));


        initComponents();

    }
    
    public void initComponents(){

        //call the createFilenamePanel method and assign its JPanel  returned instance to filenamePanel variable
        filenamePanel = createFilenamePanel();
        this.add(filenamePanel);

        //call the createProgressBar method and assign its JProgressBar  returned instance to progressBar variable
        progressBarPanel = createProgressBar();
        this.add(progressBarPanel);

        //call the createProgressUpdatePanel method and assign its JProgressBar  returned instance to updatePanel variable
        JPanel updatePanel = createProgressUpdatePanel();
        this.add(updatePanel);

    }


    public JPanel createFilenamePanel(){
        JPanel fileNamePanel;
        
        //create a JPanel instance to hold the filename label and start button components
        fileNamePanel = new JPanel();
        fileNamePanel.setLayout(new BoxLayout(fileNamePanel, BoxLayout.X_AXIS));
        fileNamePanel.setOpaque(false);
        fileNamePanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        //create a JLabel instance for filename
        filenameLabel = createTextArea("Keine Datei ausgewählt!");
        filenameLabel.setFont(TEXT_FONT);
        filenameLabel.setForeground(Color.white);

        //add filenameLabel to the top panel
        fileNamePanel.add(filenameLabel);

         //create JButton instance for start import action
        btnStartCancel = addJButton("STARTEN",SECONDARY_COLOR);
        btnStartCancel.setEnabled(false);
        btnStartCancel.addActionListener(this);

        //add start button to the top panel
        fileNamePanel.add(btnStartCancel);
        
        return fileNamePanel;
    }
     
    public JProgressBar createProgressBar(){
        
       progressBar = new JProgressBar(0,100);
        progressBar.setForeground(SECONDARY_COLOR);
        progressBar.setPreferredSize(new Dimension((int)(DEFAULT_WIDTH*0.8),6 ));
        progressBar.setValue(0);
        return progressBar;
    }
    
    //create a JLabel instance to hold the progressBar updates
    public JPanel createProgressUpdatePanel(){

         // create panel to hold the two JTextArea instances for progress updates
        JPanel panelUpdate = new JPanel();
        panelUpdate.setLayout(new BoxLayout(panelUpdate,BoxLayout.X_AXIS));
        panelUpdate.setOpaque(false);
        panelUpdate.setForeground(Color.white);
        panelUpdate.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));

        //create a JTextArea instance for the size of the file
        fileSize = createTextArea("0MB");
        panelUpdate.add(fileSize);

        //create a JTextArea instance for the ProgressBar Value update
        progressUpdate =  createTextArea("0% Fertig ");
        panelUpdate.add(progressUpdate);

        return panelUpdate;
    }

    //a method that creates a JTextArea instance and customize its attributes
    public JTextArea createTextArea(String text){
        JTextArea progressTextArea;
        
        progressTextArea = new JTextArea(1,15);
        progressTextArea.setText(text);
        progressTextArea.setForeground(TEXT_COLOR.darker());
        progressTextArea.setFont(TEXT_FONT.deriveFont(Font.BOLD,10));
        progressTextArea.setOpaque(false);

        return progressTextArea;
    }

    //a method that creates a JTextArea instance and customize its attributes
    public JButton addJButton(String text, Color foreground){
        JButton button;
    
        button = new JButton(text);   
        button.setBackground(DEFAULT_BG_COLOR.darker());
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.RIGHT);
        button.setFont(LABEL_FONT);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        return button;
    
    }
    
    public JTextArea getFilenameLabel() {
        return filenameLabel;
    }

    public JButton getBtnStart() {
        return btnStartCancel;
    }
   

    public JProgressBar getProgressBarPanel() {
        return progressBarPanel;
    }

    public JTextArea getProgressUpdate() {
        return progressUpdate;
    }
    
    public JTextArea getFileSize() {
        return fileSize;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == btnStartCancel){
            btnStartCancel.setEnabled(false);

            //toggle Start to Cancel button and vice versa
//            if(btnStartCancel.getText().equalsIgnoreCase("Starten")){
//                btnStartCancel.setText("Cancel");
//                btnStartCancel.setOpaque(true);
//                btnStartCancel.setBackground(Color.red.darker());
//
//            }
//            else{
//                btnStartCancel.setText("Starten");
//                btnStartCancel.setOpaque(false);
//            }
        }
        
    }
    
}
