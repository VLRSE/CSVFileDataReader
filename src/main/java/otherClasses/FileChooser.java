package otherClasses;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Vilrose Daquiado
 */

public class FileChooser extends JFileChooser {

    private int returnVal;
    //    private final JFileChooser jFileChooser;
    private File file;
    private final Container parent;
    private float fileSize;
    private String sizeType;
    private List<File> importedFiles;


    public FileChooser(Container parent){
        // set the directory path to the current directory
        super(new java.io.File("../"));
        super.setDialogType(JFileChooser.CUSTOM_DIALOG);
        super.setFileFilter(new CustomFileFilter());
        super.setApproveButtonText("Importieren");
        super.setDragEnabled(true);

        this.parent = parent;
        returnVal = showOpenDialog(parent);


        init();


    }

    public final void init(){

        importedFiles = new ArrayList<>();
        fileSize = 0;

            if(returnVal == JFileChooser.APPROVE_OPTION){

            /*save the file selected when Upload button was clicked*/
            file = this.getSelectedFile();
            fileSize = file.length();

            importedFiles.add(file);

            if(fileSize >= 1024 && fileSize <(Math.pow(1024, 2))){
                fileSize /= 1024;
                sizeType = "KB";
            }
            if(fileSize >= (Math.pow(1024, 2)) && fileSize <(Math.pow(1024, 3))){
                fileSize /= Math.pow(1024, 2);
                sizeType = "MB";
            }
        }
    }

    public List<File> getImportedFiles() {
        return importedFiles;
    }

    public File getFile() {
        return file;
    }

    public int getReturnVal() {
        return returnVal;
    }


    public float getFileSize() {
        return fileSize;
    }

    public String getSizeType() {
        return sizeType;
    }
}
