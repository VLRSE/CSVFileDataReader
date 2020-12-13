/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DarkThemeComponents;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class ProgressBarPanel extends JPanel implements ActionListener {

    private JLabel  filenameLabel;
    private JPanel  filenamePanel;
    private JButton btnStartCancel, btnCancel;   
    private JProgressBar progressBar;
    private JTextArea progressUpdate,progressSize;

    
    private Dimension dm;
    private final Border border;
    private final Color DEFAULT_BG_COLOR,SECONDARY_COLOR, SHADOW_COLOR, TEXT_COLOR;
    private final int DEFAULT_WIDTH;
    private final Font LABEL_FONT, TEXT_FONT;

    public ProgressBarPanel(Dimension size) {
        this.dm = size;
        
        this.DEFAULT_WIDTH =  (int)(size.getWidth());
       
        this.DEFAULT_BG_COLOR = Color.decode("#212121");
        this.SECONDARY_COLOR = Color.decode("#42A5F5");
        this.SHADOW_COLOR = new Color((int)SECONDARY_COLOR.getRed(), (int)SECONDARY_COLOR.getGreen(),(int) SECONDARY_COLOR.getBlue(),20);
        this.TEXT_COLOR = Color.decode("#EEEEEE");
     
        this.LABEL_FONT = new Font("Nunito Sans",Font.BOLD,9);
        this.TEXT_FONT = new Font("Montesarrat",Font.BOLD,10); 
        this.border = BorderFactory.createBevelBorder(BevelBorder.LOWERED, SHADOW_COLOR.brighter(), SHADOW_COLOR.brighter(), SHADOW_COLOR, SHADOW_COLOR.brighter());        
        
        initComponents();

    }
    
    public void initComponents(){
        super.setBackground(DEFAULT_BG_COLOR);
        super.setBorder(border);   
        
        //create icon label for the left side of the ProgressBarPanel 
        JLabel iconLabel = new JLabel();        
        ImageIcon icon = new ImageIcon("src\\main\\java\\Images\\baseline_description_black_18dp.png");
        iconLabel.setIcon(icon);
        
        this.add(iconLabel);
        this.add(createProgressBarPanel());
    
    }
    
    public JPanel createProgressBarPanel(){
        JPanel progressPanel, updatePanel ; 
        progressPanel =  new JPanel();
        
        
        progressPanel.setLayout(new BoxLayout(progressPanel,BoxLayout.Y_AXIS));
        progressPanel.setOpaque(false);
     
        //add components
        
        filenamePanel = createFilenamePanel();
        progressBar  = createProgressBar();
        updatePanel = createProgressUpdate();
        
        progressPanel.add(filenamePanel);
        progressPanel.add(progressBar);
        progressPanel.add(updatePanel);

        return progressPanel;
    }
    
    public JPanel createFilenamePanel(){
        JPanel fileNamePanel;
        
        //create a JPanel instance to hold the filename label and start button components
        fileNamePanel = new JPanel(new FlowLayout(FlowLayout.LEADING,0,0));        
        fileNamePanel.setPreferredSize(new Dimension(DEFAULT_WIDTH,25));
        fileNamePanel.setOpaque(false);

        
        //create a JLabel instance for filename
        filenameLabel = new JLabel("Keine Datei ausgewählt!", SwingConstants.LEADING);
        filenameLabel.setPreferredSize(new Dimension((int)(DEFAULT_WIDTH*0.85), 10));
        filenameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        filenameLabel.setFont(TEXT_FONT);
        filenameLabel.setForeground(TEXT_COLOR);
        filenameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 20));

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
        
        JProgressBar  progressBar1 = new JProgressBar(0,100);
        progressBar1.setForeground(SECONDARY_COLOR);
        progressBar1.setPreferredSize(new Dimension((int)(DEFAULT_WIDTH*0.8),6 ));
        
        
        return progressBar1;
    }
    
    //create a JLabel instance to hold the progressBar updates
    public JPanel createProgressUpdate(){
         // create panel to hold the two JTextArea instances for progress updates
        JPanel panelUpdate = new JPanel(new FlowLayout(FlowLayout.LEADING,2,0));
        
        panelUpdate.setOpaque(false);
        
        progressSize = createTextArea();  

        
        panelUpdate.add(progressSize);
        
        progressUpdate = createTextArea();
       
        panelUpdate.add(progressUpdate);
        return panelUpdate;
    }
    
    public JTextArea createTextArea(){
        JTextArea progressTextArea;
        
        progressTextArea = new JTextArea(1,20);  
        
        progressTextArea.setPreferredSize(new Dimension((int)(DEFAULT_WIDTH*0.15), 10));
        progressTextArea.setForeground(TEXT_COLOR.darker());
        progressTextArea.setFont(TEXT_FONT.deriveFont(Font.BOLD,10));
        progressTextArea.setOpaque(false);
        progressTextArea.setText("");
        progressTextArea.setEditable(false);
        return progressTextArea;
    }
     
    public JLabel addLabel(String text,Dimension dm, Color textColor,float componentAlignment, int textAlignment){
        JLabel label = new JLabel(text);
        
        label.setFont(LABEL_FONT);
        label.setPreferredSize(dm);
        label.setOpaque(false);
        label.setAlignmentX(componentAlignment);    
        label.setHorizontalAlignment(textAlignment);
        label.setForeground(textColor);
        
        return label;
    }
    
    public JButton addJButton(String text, Color foreground){
        JButton button;
    
        button = new JButton(text);   
        button.setBackground(DEFAULT_BG_COLOR.darker());
        button.setOpaque(false);
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.RIGHT);
        button.setFont(LABEL_FONT);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 3, 10));

        return button;
    
    }
    
    public JLabel getFilenameLabel() {
        return filenameLabel;
    }

    public JButton getBtnStart() {
        return btnStartCancel;
    }
   

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JTextArea getProgressUpdate() {
        return progressUpdate;
    }
    
    public JTextArea getProgressSize() {
        return progressSize;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source == btnStartCancel){
            btnStartCancel.setEnabled(false);
        }
        
    }
    
}
