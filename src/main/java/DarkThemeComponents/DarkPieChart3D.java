package DarkThemeComponents;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import otherClasses.DarkThemeColor;

import java.awt.*;



public class DarkPieChart3D {

    private final String title;
    private final PiePlot3D plot;
    private final DefaultPieDataset dataset;
    private final JFreeChart chart;
    private final Color PRIMARY_BG_COLOR = DarkThemeColor.SECONDARY_BG_COLOR;



    public DarkPieChart3D(String title, DefaultPieDataset dataset) {

        chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);

        this.plot = (PiePlot3D)chart.getPlot();
        this.dataset = dataset;
        this.title = title;


        init();
    }


    private  void init(){

        chart.setBackgroundPaint(PRIMARY_BG_COLOR);
        chart.setBorderVisible(false);

        TextTitle textTitle = chart.getTitle();
        textTitle.setPaint(DarkThemeColor.PRIMARY_TEXT_COLOR);
        textTitle.setFont(new Font("Sans-serif", Font.BOLD, 18));


        final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");

        //set the chart´s plot background color same as of the chart´s background color
        plot.setBackgroundPaint(PRIMARY_BG_COLOR);
        plot.setLabelBackgroundPaint(Color.decode("#9E9E9E"));


        //shows the label plus the percentage value in the chart
        plot.setLabelGenerator(labelGenerator);
        plot.setLabelBackgroundPaint(Color.decode("#BBDEFB"));

        LegendTitle legend =  chart.getLegend();
        legend.setVisible(false);
        legend.setHeight(90.00);
//        legend.setBackgroundPaint(DarkThemeColor.SECONDARY_BG_COLOR);
//        legend.setItemPaint(DarkThemeColor.SECONDARY_TEXT_COLOR);
//        legend.setBackgroundPaint(PRIMARY_BG_COLOR);
//        legend.setItemPaint(DarkThemeColor.PRIMARY_TEXT_COLOR);
//        legend.setItemLabelPadding(new RectangleInsets(5,5,5,5));
//        legend.setLegendItemGraphicAnchor(RectangleAnchor.LEFT);





    }

    public String getTitle() {
        return title;
    }

    public PiePlot3D getPlot() {
        return plot;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public Color getPRIMARY_BG_COLOR() {
        return PRIMARY_BG_COLOR;
    }
}
