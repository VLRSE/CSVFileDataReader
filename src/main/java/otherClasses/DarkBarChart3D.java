package otherClasses;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;

import java.awt.*;

public class DarkBarChart3D extends JFreeChart {
    public DarkBarChart3D(Plot plot) {
        super(plot);
    }

    public DarkBarChart3D(String title, Plot plot) {
        super(title, plot);
    }

    public DarkBarChart3D(String title, Font titleFont, Plot plot, boolean createLegend) {
        super(title, titleFont, plot, createLegend);
    }
}
