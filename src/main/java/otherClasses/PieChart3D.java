package otherClasses;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

public class PieChart3D extends JFreeChart{
    private String title;
    private PiePlot3D plot;



    public PieChart3D(String title,Plot plot) {
        super(title, plot);

        setBackgroundPaint(DarkThemeColor.SECONDARY_BG_COLOR);
        getTitle().setPaint(DarkThemeColor.PRIMARY_TEXT_COLOR);
        getTitle().setFont(new Font("Sans-serif", Font.BOLD, 14));
        setBorderVisible(false);

        this.plot = (PiePlot3D)plot;
        this.title = title;



        init();
    }


    private  void init(){

        final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");

        //set the chart´s plot background color same as of the chart´s background color
        plot.setBackgroundPaint(this.getBackgroundPaint());
        plot.setLabelBackgroundPaint(Color.decode("#E0E0E0"));


        //shows the label plus the percentage value in the chart
        plot.setLabelGenerator(labelGenerator);

        LegendTitle legend =  getLegend();
        legend.setBackgroundPaint(DarkThemeColor.SECONDARY_BG_COLOR);
        legend.setItemPaint(DarkThemeColor.SECONDARY_TEXT_COLOR);
        legend.setBackgroundPaint(getBackgroundPaint());
        legend.setItemPaint(DarkThemeColor.PRIMARY_TEXT_COLOR);
        legend.setItemLabelPadding(new RectangleInsets(16,5,16,5));
        legend.setLegendItemGraphicPadding(new RectangleInsets(16,24,16,24));
        legend.setLegendItemGraphicAnchor(RectangleAnchor.LEFT);





    }


}
