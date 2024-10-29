package log_analizer;

import backend.academy.log_analizer.FilterChain;
import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.LogStringParserImpl;
import backend.academy.log_analizer.juice.ObjectFabric;
import org.junit.jupiter.api.Test;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterChainTest {

    private String log = "//54.91.91.173 - - [02/Jun/2015:17:06:59 +0000] \"GET /downloads/product_2 HTTP/1.1\" 200 1768" +
        " \"-\" \"Chef Client/11.16.2 (ruby-1.9.3-p547; ohai-7.4.0; x86_64-linux; +http://opscode.com)\"";
    private LogString logString = ObjectFabric.getObject(LogStringParserImpl.class).parseLogString(log);


    @Test
    public void filterChainTest1(){
        FilterChain filterChain = new FilterChain();
        filterChain.addAfterFilter(ZonedDateTime.now());
        assertFalse(filterChain.checkFilters(logString));
    }

    @Test
    public void filterChainTest2(){
        FilterChain filterChain = new FilterChain();
        filterChain.addBeforeFilter(ZonedDateTime.now());
        assertTrue(filterChain.checkFilters(logString));
    }

    @Test
    public void filterChainTest3(){
        FilterChain filterChain = new FilterChain();
        filterChain.addBeforeFilter(
                ZonedDateTime.of(2015,6,2,
                8, 5,25, 0, ZoneId.of("+0000")));
        assertFalse(filterChain.checkFilters(logString));
    }
}
