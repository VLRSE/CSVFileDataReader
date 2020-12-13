package otherClasses;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

/**
 *
 * @author Vilrose Daquiado
 */


public class CopyFileToApp {

    private Path destPath, sourcePath;

    public CopyFileToApp(Path sourcePath) {
        this.sourcePath =  sourcePath;

    }

    CopyFileToApp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void initComponents() throws IOException{

        destPath = Paths.get("src/Files/"+sourcePath.getFileName().toString());

        //create  otherClasses.FilesImported instance and save the imported files


        try{
            Files.copy(sourcePath, destPath);

        }
        catch(FileAlreadyExistsException  e){

            int returnVal = JOptionPane.showOptionDialog(null
                    , " \nMöchten Sie die Datei ersetzen?", "Datei ist bereits verügbar!"
                    , JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE
                    , null, new Object[] {"Ja", "Nein", "Abbrechen"}, JOptionPane.YES_OPTION);

            switch (returnVal) {
                case JOptionPane.OK_OPTION:
                    int answer = JOptionPane.showOptionDialog(null, "Sind Sie sicher?"
                            , "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
                            , null, new Object[] {"Ja", "Abbrechen"}, JOptionPane.YES_OPTION);
                    if(answer ==JOptionPane.OK_OPTION ){
                        Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                    break;

                case JOptionPane.NO_OPTION:
                    Path target = Paths.get("src/Files/"+ sourcePath.getFileName().toString()+"-Copy");

                    Files.copy(sourcePath,target ,StandardCopyOption.COPY_ATTRIBUTES );
                    break;

                default:
                    break;
            }
        }
        catch(IOException e){
            e.printStackTrace(System.err);
        }
    }
}
