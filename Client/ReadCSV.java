package Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadCSV {
    public static void main(String[] args) {
        ReadCSV readCSV = new ReadCSV();
        try {
            readCSV.get_historical_data("TSLA");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void get_historical_data(String stock_code) throws IOException {
        String url = "https://finance.yahoo.com/quote/" + stock_code + "/history?p=" + stock_code;
        Document page = Jsoup.connect(url).get();

        Element link = page.selectFirst("a[class='Fl(end) Mt(3px) Cur(p)']");
        String fileLink = link.attr("href");
        System.out.println(fileLink);

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(new URL(fileLink).openStream()));
            String i;
            while ((i = in.readLine()) != null) {
                System.out.println(i);
            }
        } catch (
                IOException e) {
            // handle exception
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
