
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import static org.apache.commons.codec.binary.StringUtils.getBytesUtf8;


public class HTMLParser {

    static Calendar cal = Calendar.getInstance();
    static int day = cal.get(Calendar.DAY_OF_MONTH);
    static int month = cal.get(Calendar.MONTH);
    static int year = cal.get(Calendar.YEAR);
    public static Elements getClues() {
        String url = "https://www.nytimes.com/crosswords/game/mini";

        try {
            Document doc = Jsoup.parse(new URL(url), 10000);
            Elements newelm = doc.getElementsByClass("Clue-text--3lZl7");
            PrintWriter pw = new PrintWriter("clues"+ day + month+year+".txt");
            for(int i = 0; i < newelm.size(); i++){
                pw.println(newelm.get(i).text());
                System.out.println(""+newelm.get(i).text());
            }
            pw.close();
            return newelm;
            /*if (doc.getElementById("poDiv").ownText().equals("no course")) {
                return null;
            } else {
                Element table = doc.getElementById("poTable");
                Elements rows = table.getElementsByTag("tr");
                return rows;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            return new Elements();
        }
    }
    public static void getBoxes(){
        String url = "https://www.nytimes.com/crosswords/game/mini";

        try {
            Document doc = Jsoup.parse(new URL(url), 10000);
            Elements newelm = doc.getElementsByAttribute("fill");


            PrintWriter pw = new PrintWriter("boxes" + day + month+year+" .txt");
            for (int i = 0; i < newelm.size()-2; i++) {
                pw.println(newelm.get(i).attr("fill"));
                System.out.println("" + newelm.get(i).attr("fill"));
            }
            pw.close();
            System.out.println(month);
        }catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        getClues();
        getBoxes();

    }
}
