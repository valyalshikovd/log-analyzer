package log_analizer;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.LogStringParserImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogStringParserTest {

    @Test
    public void parseStringTest(){

        String log = "80.91.33.133 - - [17/May/2015:08:05:50 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"";

        LogString logString = new LogStringParserImpl().parseLogString(log);

        assertEquals(log, logString.toString());

    }

}
