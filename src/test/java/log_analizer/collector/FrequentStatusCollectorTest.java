package log_analizer.collector;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.LogStringParser;
import backend.academy.log_analizer.LogStringParserImpl;
import backend.academy.log_analizer.guice.ObjectFabric;
import backend.academy.log_analizer.statisticCollector.collector.FrequentStatusCollector;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrequentStatusCollectorTest {

    @Test
    public void frequentStatusCollectorTest() {

        LogStringParser logStringParser = ObjectFabric.getObject(LogStringParserImpl.class);
        List<LogString> logs = new ArrayList<>();
        FrequentStatusCollector collector =
            new  FrequentStatusCollector("avgRespCollector", 3);

        logs.add(logStringParser.parseLogString(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""));
        logs.add(logStringParser.parseLogString(
            "31.22.86.126 - - [17/May/2015:08:05:24 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\""));
        logs.add(logStringParser.parseLogString(
            "50.57.209.92 - - [17/May/2015:08:05:59 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\""));
        logs.add(logStringParser.parseLogString(
            "2.75.167.106 - - [17/May/2015:09:05:56 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\""));
        logs.add(logStringParser.parseLogString(
            "83.161.14.106 - - [17/May/2015:09:05:45 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\""));
        logs.add(logStringParser.parseLogString(
            "5.83.131.103 - - [17/May/2015:09:05:49 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 337 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\""));
        logs.add(logStringParser.parseLogString(
            "80.70.214.71 - - [17/May/2015:10:05:13 +0000] \"GET /downloads/product_2 HTTP/1.1\" 404 327 \"-\" \"Wget/1.13.4 (linux-gnu)\""));
        logs.add(logStringParser.parseLogString(
            "37.187.238.39 - - [17/May/2015:10:05:05 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\""));
        logs.add(logStringParser.parseLogString(
            "62.75.167.106 - - [17/May/2015:10:05:31 +0000] \"GET /downloads/product_2 HTTP/1.1\" 404 337 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\""));
        logs.add(logStringParser.parseLogString(
            "93.190.71.150 - - [17/May/2015:10:05:07 +0000] \"GET /downloads/product_2 HTTP/1.1\" 404 336 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\""));
        logs.add(logStringParser.parseLogString(
            "144.92.16.161 - - [17/May/2015:11:05:39 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""));
        logs.add(logStringParser.parseLogString(
            "31.22.86.126 - - [17/May/2015:11:05:19 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 334 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\""));
        logs.add(logStringParser.parseLogString(
            "80.91.33.133 - - [17/May/2015:11:05:42 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 339 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\""));
        logs.add(logStringParser.parseLogString(
            "83.161.14.106 - - [17/May/2015:12:05:48 +0000] \"GET /downloads/product_2 HTTP/1.1\" 404 339 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\""));
        logs.add(logStringParser.parseLogString(
            "198.61.216.151 - - [17/May/2015:12:05:02 +0000] \"GET /downloads/product_2 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\""));
        logs.add(logStringParser.parseLogString(
            "83.161.14.106 - - [17/May/2015:12:05:19 +0000] \"GET /downloads/product_2 HTTP/1.1\" 404 336 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\""));
        logs.add(logStringParser.parseLogString(
            "80.91.33.133 - - [17/May/2015:12:05:38 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 338 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\""));

        logs.forEach(collector::collectStatistics);
        assertEquals("404: 9\n304: 8\n", collector.getStatistics());
    }
}
