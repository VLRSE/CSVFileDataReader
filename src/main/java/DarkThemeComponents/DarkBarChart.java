package DarkThemeComponents;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;
import otherClasses.DarkThemeColor;

import java.awt.*;

public class DarkBarChart {

    private final String title;
    private final Plot plot;
    private final JFreeChart barchart;
    private final DefaultCategoryDataset dataset;
    private final Color PRIMARY_BG_COLOR = DarkThemeColor.SECONDARY_BG_COLOR;


    public DarkBarChart(String title, DefaultCategoryDataset dataset) {

        barchart = ChartFactory.createBarChart(title, "Kategorie", "Zahl",dataset
                , PlotOrientation.VERTICAL, true, true, false);

        this.title = title;
        this.plot = barchart.getPlot();
        this.dataset = dataset;

        init();

    }


    private  void init(){

        barchart.setBackgroundPaint(PRIMARY_BG_COLOR);
        barchart.setBorderVisible(true);
        barchart.setBorderPaint(DarkThemeColor.SECONDARY_BG_COLOR);

        TextTitle textTitle = barchart.getTitle();
        textTitle.setPaint(DarkThemeColor.PRIMARY_TEXT_COLOR);
        textTitle.setFont(new Font("Sans-serif", Font.BOLD, 18));



        //set the chart´s plot background color same as of the chart´s background color
        plot.setBackgroundPaint(PRIMARY_BG_COLOR);
        plot.setOutlineVisible(true);
        plot.setOutlineStroke(Plot.DEFAULT_OUTLINE_STROKE);
//        plot.setDomainGridlinesVisible(false);
//        plot.setRangeGridlinesVisible(false);




        Paint[] colors = {Color.decode("#003f5c"),Color.decode("#2f4b7c"),
                            Color.decode("#665191"),Color.decode("#a05195"),
                            Color.decode("#d45087"),   Color.decode("#f95d6a"),
                            Color.decode("#ff7c43"), Color.decode("#ffa600")};



        // change the default colors
//        for (int i = 0; i < 8; i++) {
//            plot.getRenderer().setSeriesPaint(i, colors[i]);
//        }



        LegendTitle legend =  barchart.getLegend();
        legend.setBackgroundPaint(DarkThemeColor.SECONDARY_BG_COLOR);
        legend.setItemPaint(DarkThemeColor.SECONDARY_TEXT_COLOR);
        legend.setBackgroundPaint(PRIMARY_BG_COLOR);
        legend.setItemPaint(DarkThemeColor.PRIMARY_TEXT_COLOR);
        legend.setItemLabelPadding(new RectangleInsets(5,5,5,5));
        legend.setLegendItemGraphicAnchor(RectangleAnchor.LEFT);
        System.out.println( legend.getItemLabelPadding());


    }

    public String getTitle() {
        return title;
    }

    public Plot getPlot() {
        return plot;
    }

    public JFreeChart getChart() {
        return barchart;
    }

    public Color getPRIMARY_BG_COLOR() {
        return PRIMARY_BG_COLOR;
    }
}
