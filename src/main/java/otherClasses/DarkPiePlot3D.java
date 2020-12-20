package otherClasses;

import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.PieDataset;

public class DarkPiePlot3D extends PiePlot3D {

    public DarkPiePlot3D() {
        init();
    }

    public DarkPiePlot3D(PieDataset dataset) {
        super(dataset);
        init();
    }

    private void init(){
        setStartAngle(270);
        setForegroundAlpha((float) 0.75);
        setBackgroundPaint(DarkThemeColor.SECONDARY_BG_COLOR);

    }


}
