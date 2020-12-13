package otherClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameDragListener extends MouseAdapter {

    private final JFrame frame;
    private Point mouseDownCoords = null;

    // class constructor that requires a JFrame instance as parameter
    public FrameDragListener (JFrame frame){
        this.frame = frame;
    }

    public void mousePressed(MouseEvent e){
        mouseDownCoords = e.getPoint();
    }

    public void mouseDragged(MouseEvent e){
        Point currentCoords = e.getLocationOnScreen();
        frame.setLocation(currentCoords.x - mouseDownCoords.x, currentCoords.y- mouseDownCoords.y);
    }

    public void mouseReleased(MouseEvent e){
        mouseDownCoords = null;
    }
}
