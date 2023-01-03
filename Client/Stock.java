package Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Stock {
    private double stock_price;
    private String stock_name;
    private String stock_code;

    public double getStock_price() throws Exception {
        this.stock_price = real_time_price(this.stock_code);
        return stock_price;
    }

    public void refresh_stock_price() throws Exception {
        this.stock_price = real_time_price(this.stock_code);
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getStock_code() {
        return stock_code;
    }

    public double real_time_price(String stock_code) throws Exception//get the real time price of stock from its code
    {
        String url = "https://www.google.com/finance/quote/" + stock_code + ":NASDAQ?hl=en";
        String texts;


        Document page = (Jsoup.connect(url)).get();
        //Element price = page.selectFirst("div.YMlKec fxKbKc");


        texts = page.html();
        int start = texts.indexOf("<div class=\"YMlKec fxKbKc\">", 0);
        int from = texts.indexOf(">", start);
        int stop = texts.indexOf("</div", from);

        String price = texts.substring(from + 1, stop);
        //price = texts.first();
        return (Double.parseDouble(price)); //transform the string price into double and return it


        //return text;
    }

}
