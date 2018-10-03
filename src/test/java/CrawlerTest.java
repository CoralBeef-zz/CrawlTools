import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrawlerTest {


    @Test
    void getThis() {

        Crawler c = new Crawler();
        c.setUpProxy("108.59.14.200", 13152);
        assertTrue(c.getDocument("http://boards.4chan.org/a/").html() != null);
    }
}