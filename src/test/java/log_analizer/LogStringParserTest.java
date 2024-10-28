package log_analizer;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.LogStringParserImpl;
import backend.academy.log_analizer.exception.InvalidLogString;
import backend.academy.log_analizer.juice.ObjectFabric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogStringParserTest {

    @Test
    public void parseStringTest1(){

        String log = "80.91.33.133 - - [17/May/2015:08:05:50 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"";

        LogString logString = ObjectFabric.getObject(LogStringParserImpl.class).parseLogString(log);

        assertEquals(log, logString.toString());
    }
    @Test
    public void parseStringTest2(){

        String log = "54.172.175.237 - - [02/Jun/2015:12:06:42 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 85619205 \"-\" \"Chef Client/11.6.2 (ruby-1.9.3-p448; ohai-6.18.0; x86_64-linux; +http://opscode.com)\"";

        LogString logString = ObjectFabric.getObject(LogStringParserImpl.class).parseLogString(log);

        assertEquals(log, logString.toString());
    }

    @Test
    public void parseStringTest3(){

        String log = "90.26.32.91 - - [02/Jun/2015:13:06:28 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 2592 \"-\" \"dnf/0.5.4\"";

        LogString logString = ObjectFabric.getObject(LogStringParserImpl.class).parseLogString(log);

        assertEquals(log, logString.toString());
    }

    @Test
    public void parseStringTest4(){

        String log = "//54.91.91.173 - - [02/Jun/2015:17:06:59 +0000] \"GET /downloads/product_2 HTTP/1.1\" 200 1768 \"-\" \"Chef Client/11.16.2 (ruby-1.9.3-p547; ohai-7.4.0; x86_64-linux; +http://opscode.com)\"";

        LogString logString = ObjectFabric.getObject(LogStringParserImpl.class).parseLogString(log);

        assertEquals(log, logString.toString());
    }

    @Test
    public void parseInvalidStringTest(){

        String log = "90.26.32.91 - - [02/Jun/2015:13:06:28 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 2592 \"-\"";

        Assertions.assertThrows(InvalidLogString.class, () ->ObjectFabric.getObject(LogStringParserImpl.class).parseLogString(log));

    }

    @Test
    public void parseInvalidDateStringTest(){
        String log = "90.26.32.91 - - [02/Jun/2015:13:06:28] \"GET /downloads/product_1 HTTP/1.1\" 200 2592 \"-\" \"dnf/0.5.4\"";

        Assertions.assertThrows(InvalidLogString.class, () ->ObjectFabric.getObject(LogStringParserImpl.class).parseLogString(log));
    }
}
