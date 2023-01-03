package Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class StockPage {
    public static void main(String[] args) throws Exception {
        StockPage page = new StockPage();
        System.out.println(page.real_time_price("TSLA"));

    }

    /*
        public Elements web_content_div(Document web_content, String class_path) {
            String css = "fin-streamer[" + class_path + "]";
            Element web_content_div = web_content.selectFirst(css);
            return web_content_div.select("span");
        }
    */
    public String real_time_price(String stock_code) throws Exception//get the real time price of stock from its code
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
        return price;


        //return text;
    }

}
