package Client;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

//API that create a stockGraph object
public class StockGraph extends JPanel {
    public StockGraph(String stock_code)
    //constructor using the stock_code
    {
        public DefaultCategoryDataset createDataSet (String stock_code)
        {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        }

        JFreeChart stock_chart = ChartFactory.createLineChart("Yearly Performance",
                "Date", "Stock Price", createDataSet(stock_code), PlotOrientation.VERTICAL, true, true, false);


    }

}
