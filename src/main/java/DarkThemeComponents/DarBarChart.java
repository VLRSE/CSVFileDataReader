package DarkThemeComponents;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import otherClasses.DarkThemeColor;

import java.awt.*;

public class DarBarChart {

    private String title;
    private String categoryAxisLabel;
    private String valueAxisLabel;
    private CategoryDataset dataset;
    private Plot plot;
    private final JFreeChart chart;
    private final Color PRIMARY_BG_COLOR = DarkThemeColor.SECONDARY_BG_COLOR;

    public DarBarChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset ) {
        this.title = title;
        this.dataset = dataset;
        this.categoryAxisLabel = categoryAxisLabel;
        this.valueAxisLabel = valueAxisLabel;

        //create a BarChart
        chart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, dataset);
        this.plot = chart.getPlot();
    }

    private void init(){
        chart.setBackgroundPaint(PRIMARY_BG_COLOR);
        chart.setBorderVisible(false);

        TextTitle textTitle = chart.getTitle();
        textTitle.setPaint(DarkThemeColor.PRIMARY_TEXT_COLOR);
        textTitle.setFont(new Font("Sans-serif", Font.BOLD, 14));

        LegendTitle legend =  chart.getLegend();
        legend.setBackgroundPaint(DarkThemeColor.SECONDARY_BG_COLOR);
        legend.setItemPaint(DarkThemeColor.SECONDARY_TEXT_COLOR);
        legend.setBackgroundPaint(PRIMARY_BG_COLOR);
        legend.setItemPaint(DarkThemeColor.PRIMARY_TEXT_COLOR);
        legend.setItemLabelPadding(new RectangleInsets(5,5,5,5));
        legend.setLegendItemGraphicAnchor(RectangleAnchor.LEFT);

        //set the chart´s plot background color same as of the chart´s background color
        plot.setBackgroundPaint(PRIMARY_BG_COLOR);

        plot.addChangeListener(l ->{

        });






    }
}
