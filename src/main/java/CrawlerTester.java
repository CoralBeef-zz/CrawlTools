import org.jsoup.nodes.Document;

public class CrawlerTester {
    public static void main(String[] args) {
        Crawler c = new Crawler();
        Document d = c.getDocument("http://boards.4chan.org/a/");
        if(d == null) System.out.println("Nothing!");
        else System.out.println(d.html());
    }
}
