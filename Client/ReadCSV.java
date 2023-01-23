package Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadCSV {
    //csv has data in chronological order

    public static void main(String[] args) {
        ReadCSV readCSV = new ReadCSV();
        try {
            readCSV.get_historical_data("TSLA");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void get_historical_data(String stock_code) throws IOException {
        //using the stock code as a parameter look for historical data
        String url = "https://finance.yahoo.com/quote/" + stock_code + "/history?p=" + stock_code;
        Document page = Jsoup.connect(url).get();

        Element link = page.selectFirst("a[class='Fl(end) Mt(3px) Cur(p)']"); //search for download link
        String downloadLink = link.attr("href");//download link

        //the csv file will have the data in chronological order

        //date formatter
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //create 2 arrays of size 365 since it will be the maximum amount of points in one year
        Date[] dates = new Date[365];
        double[] closing_prices = new double[365];

        try {
//open link and read csv file
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL(downloadLink).openStream()));
            String line = in.readLine();//we dont need the first line
            int counter = 0;//to keep track of what line we are
            String[] information;
            //index 0 has the date and index 4 has the closing price
            while ((line = in.readLine()) != null) {

                information = line.split(",");
                dates[counter] = formatter.parse(information[0]); //transform the data string into date object
                closing_prices[counter] = Double.parseDouble(information[4]);//transform the closing prices nto double
                counter++;

            }
        } catch (
                Exception e) {
            // handle exception
        }

        for (Date i : dates) {
            System.out.println(i);
        }

        //String texts = page.html();
/*
        Elements day = page.select("td[class='Py(10px) Ta(start) Pend(10px)']");
        Elements date = day.select("span");
        System.out.println(date);

        Elements prices = page.select("td[ class='Py(10px) Pstart(10px)']");
        String[] test = new String[prices.size()];
        for (int i = 0; i < prices.size(); i++) {
            test[i] = prices.get(i).text();
        }
        int size = test.length / 6;
        double[] closing_prices = new double[size];


        for (int i = 3; i < test.length; i += 6) {
            System.out.println(test[i]);
        }

        String texts = page.html();




        int start = texts.indexOf("<a class=\"Fl(end) Mt(3px) Cur(p)\">", 0);//used to find price by looking a specific point of html parsed
        int from = texts.indexOf("href=\"", start);
        int stop = texts.indexOf("download=", from);

        String price = texts.substring(from + 1, stop);
        System.out.println(price);

 */
    }
}
