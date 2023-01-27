package Client;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

//API that create a stockGraph object
public class StockGraph extends JPanel {

    //create 2 arrays of size 365 since it will be the maximum amount of points in one year
    Date[] dates = new Date[365];
    double[] closing_prices = new double[365];
    String[] time = new String[365];
    int counter = 0; //to keep track of what line we are
    int period_time = 0;

    double lowest_price = Double.POSITIVE_INFINITY;
    double highest_price;

    public void get_historical_data(String stock_code) throws IOException {
        //using the stock code as a parameter look for historical data
        String url = "https://finance.yahoo.com/quote/" + stock_code + "/history?p=" + stock_code;
        Document page = Jsoup.connect(url).get();

        Element link = page.selectFirst("a[class='Fl(end) Mt(3px) Cur(p)']"); //search for download link
        String downloadLink = link.attr("href");//download link

        //the csv file will have the data in chronological order

        //date formatter
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        try {
//open link and read csv file
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL(downloadLink).openStream()));
            String line = in.readLine();//we dont need the first line
            double actualnumber;
            String[] information;
            //index 0 has the date and index 4 has the closing price
            while ((line = in.readLine()) != null) {

                information = line.split(",");
                time[counter] = information[0];
                dates[counter] = formatter.parse(information[0]); //transform the data string into date object
                actualnumber = (closing_prices[counter] = Double.parseDouble(information[4]));//transform the closing prices nto double
                if (actualnumber <= lowest_price) lowest_price = actualnumber;
                if (actualnumber >= highest_price) highest_price = actualnumber;
                counter++;

            }
        } catch (
                Exception e) {
            // handle exception
        }
    }

    public XYDataset createDataSet(String stock_code) throws IOException {
        TimeSeries dataset = new TimeSeries("Stock Prices");
        get_historical_data(stock_code);
        for (int i = counter - period_time; i < counter; i++) {//will loop until we reach the null cases of the arrays.
            dataset.add(new Day(dates[i]), closing_prices[i]);
        }
        return new TimeSeriesCollection(dataset);


    }


    public StockGraph(String stock_code, String period) throws IOException
    //constructor using the stock_code
    {
        int unit_period = 30;


        if (period.equals("1Y")) {
            period_time = 0; //we will start saving the file from the begining
            unit_period = 30;//start showing every month
        }

        if (period.equals("1W")) {
            period_time = 7;
            unit_period = 1;

        }
        if (period.equals("1M")) {
            period_time = 30;
            unit_period = 3;
        }
        if (period.equals("3M")) {
            period_time = 90;
            unit_period = 9;
        }
        if (period.equals("5M")) {
            period_time = 150;
            unit_period = 15;
        }

        JFreeChart stock_chart = ChartFactory.createTimeSeriesChart("Yearly Performance",
                "Date", "Stock Price", createDataSet(stock_code));


        XYPlot plot = stock_chart.getXYPlot();


        DateAxis domain = (DateAxis) plot.getDomainAxis();
        domain.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, unit_period, new SimpleDateFormat("MMM-dd-yy")));
        domain.setVerticalTickLabels(true);
        domain.setAutoRange(true);

        plot.setDomainCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairLockedOnData(true);


        //NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //rangeAxis.setRange(lowest_price - 10, highest_price + 10);

        // plot.getDomainAxis().setLabelInsets(new RectangleInsets(10, 10, 10, 10));
        ChartPanel chartPanel = new ChartPanel(stock_chart);
        //chartPanel.setRangeZoomable(true);


        chartPanel.setPreferredSize(new Dimension(500, 500));

        this.add(chartPanel);

    }

}
