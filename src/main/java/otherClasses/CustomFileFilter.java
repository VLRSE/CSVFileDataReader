package otherClasses;

import java.io.File;

/**
 *
 * @author Vilrose DAquiado
 */

public class CustomFileFilter extends javax.swing.filechooser.FileFilter{

        @Override
        public boolean accept(File f) {
            //Allow only directories, or files with ".csv" extension
            return f.isDirectory() || f.getAbsolutePath().endsWith(".csv");
        }

        @Override
        public String getDescription() {
            return "CSV Datei (*.csv)";
        }
}
