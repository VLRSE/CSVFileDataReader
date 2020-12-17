package otherClasses;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


/*
*
*  @author Vilrose Daquiado
*
* A class that extends the ImageIcon class, and resizes it
 */

public class TinyImageIcon extends ImageIcon {
    private final int DEFAULT_WIDTH = 16,
            DEFAULT_HEIGHT = 16;
    private String filename;

    public TinyImageIcon(String filename, String description) {
        super(filename, description);
        resizeImage(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }


    public TinyImageIcon(URL location,int width, int height ) {
        super(location);
        resizeImage(width,height);
    }
    public TinyImageIcon(URL location ) {
        super(location);
        resizeImage(DEFAULT_WIDTH,DEFAULT_HEIGHT);

    }

    private void resizeImage( int width, int height){
        //resize the image to make it smaller
        Image img = this.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        super.setImage(img);
    }



}
