/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DarkThemeComponents;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 *
 * @author Admin
 */
@SuppressWarnings("serial")
public class ImportPanel extends JPanel  {
    private final Color DEFAULT_BG_COLOR = Color.decode("#424242"),PRIMARY_COLOR = Color.decode("#42A5F5"), PRIMARY_DARKER = Color.decode("#0D47A1")
            , PRIMARY_LIGHTER = Color.decode("#64B5F6")
            ,TRANSPARENT_COLOR= new Color(PRIMARY_DARKER.getRed(), PRIMARY_DARKER.getGreen(), PRIMARY_DARKER.getBlue(),20),
            TEXT_COLOR = Color.decode("#EEEEEE");
    private final Font TEXT_FONT;
    private JButton btnImport;
    private  JPanel importPanel;
    private Dimension dm;
    private final Component host;
    
    
    public ImportPanel(Component host) throws FileNotFoundException {
        
        super(new GridBagLayout());            
        super.setBackground(DEFAULT_BG_COLOR);
        
        this.host = host;        
        TEXT_FONT = new Font("Nunito Sans",Font.BOLD,11); 
        
        initComponents();      
    }
  
    
    public void initComponents() throws FileNotFoundException{
       
        this.add(createImportPanel());     
    }

    //create import panel with transparent blue background, and create its components
    public JPanel createImportPanel(){
        
        Border border;    
        
        border = BorderFactory.createDashedBorder(PRIMARY_COLOR, 1, 5, 5, true);  
        
        int width = (int)(host.getWidth()*0.5);
        int height = (int)(host.getHeight()*0.5);        
        dm = new Dimension(width,height);
        
        importPanel = new JPanel();
        importPanel.setLayout(new GridBagLayout());
        importPanel.setPreferredSize(dm);
        importPanel.setBackground(TRANSPARENT_COLOR);
        importPanel.setBorder(border);
        
        //add the panel that holds the components
        importPanel.add(createContentPanel());
        importPanel.setAlignmentX(CENTER_ALIGNMENT);
        
        return importPanel;
    }
    
    public JPanel getImportPanel() {
        return importPanel;
    } 
      //load file image file in the image directory
//    public Image getImage(final String pathAndFileName) {
//        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
//        return Toolkit.getDefaultToolkit().getImage(url);
//    }
    //create a panel to hold the components of the importPanel, and add the components
    public JPanel createContentPanel(){
        JPanel contentPanel ;
        JLabel icon,maxSizeLabel,instructLabel, fileType;
        int textAlignment;
        Color textColor;
        ImageIcon imageIcon;

        contentPanel = new JPanel();       
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        
        textAlignment = SwingConstants.CENTER;        
        textColor = Color.decode("#BDBDBD");
        //add import Icon
//        imageIcon =  new ImageIcon(getImage("resources/images/baseline_cloud_upload_white_36dp.png"));
        icon = new JLabel();
        icon.setBackground(DEFAULT_BG_COLOR);
        icon.setAlignmentX(CENTER_ALIGNMENT);
        icon.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        
        //create instruction label for the import button
        instructLabel = addLabel("Klicken den Button zu importieren",dm
                , BorderFactory.createEmptyBorder(2, 0, 10, 0), PRIMARY_LIGHTER
                ,CENTER_ALIGNMENT, textAlignment);
        //create a button to Import
        btnImport = addButton("Import", TEXT_FONT, null, PRIMARY_COLOR
                , CENTER_ALIGNMENT, TEXT_COLOR);
        btnImport.setBorder(BorderFactory.createEmptyBorder(10, 48, 12, 48));
        btnImport.revalidate();
        btnImport.repaint();
        //create label for Maximum size accepted to import
        Color newColor = new Color(PRIMARY_LIGHTER.getRed(), PRIMARY_LIGHTER.getGreen(), PRIMARY_LIGHTER.getBlue(), 90);
        maxSizeLabel = addLabel("(Bis zu 10MB)",dm,null,newColor, CENTER_ALIGNMENT
                , textAlignment);  
      
        //label for file types accepted for the import
        Border border = BorderFactory.createEmptyBorder(20, 0, 0, 0);
        fileType = addLabel("CSV,  TXT",dm,border,textColor,CENTER_ALIGNMENT,  textAlignment);
        fileType.setForeground(Color.decode("#616161"));
        
        //add components
        contentPanel.add(icon);
        contentPanel.add(instructLabel);
        contentPanel.add(btnImport);
        contentPanel.add(maxSizeLabel);
        contentPanel.add(fileType);
        contentPanel.revalidate();
        contentPanel.repaint();
        
        contentPanel.setVisible(true);
        return contentPanel;
    }
    
    public JButton addButton(String text,Font font,  Dimension size, Color bgColor, float componentAlignment, Color foregroundColor){
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setPreferredSize(size);
        button.setAlignmentX(componentAlignment);
        button.setFont(font);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.revalidate();
        button.repaint();
        return button;
    
    }
    public JLabel addLabel(String text,Dimension dm, Border border, Color textColor,float componentAlignment, int textAlignment){
        JLabel label = new JLabel(text);
        label.setFont(TEXT_FONT);
        label.setPreferredSize(dm);
        label.setBorder(border);
        label.setOpaque(false);
        label.setAlignmentX(componentAlignment);    
        label.setHorizontalAlignment(textAlignment);
        label.setForeground(textColor);
        label.setVisible(true);
        
        return label;
    }
  

    public JButton getBtnImport() {
        return btnImport;
    }
    
}
