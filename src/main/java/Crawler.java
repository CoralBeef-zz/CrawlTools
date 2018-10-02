import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawler {

    private Proxy proxy;

    public synchronized void setUpProxy(String ipAddress, int port) {
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipAddress, port));
    }

    public synchronized Document getDocument(String url) {
        boolean recon = true;
        Document d = null;
        boolean alreadyPrinted = false;
        while(recon) {
            try {
                d = Jsoup.connect(url).timeout(0)
                        .proxy(this.proxy == null ? Proxy.NO_PROXY : this.proxy)
                        .userAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; it; rv:2.0b4) Gecko/20100818")
                        .get();
                recon=false;
            } catch(IOException se) {
                if(!alreadyPrinted) {
                    System.out.println("Socket Error Detected! Retrying..");
                    alreadyPrinted = true;
                }

            }
        }
        return d;
    }

    public synchronized String workingDirectory () {
        String workingDirectory;
        String OS = (System.getProperty("os.name")).toUpperCase();
        if (OS.contains("WIN")) workingDirectory = System.getenv("AppData");
        else {
            workingDirectory = System.getProperty("user.home");
            workingDirectory += "/Library/Application Support";
        }
        return workingDirectory+"/Crawlerling/";
    }
}
