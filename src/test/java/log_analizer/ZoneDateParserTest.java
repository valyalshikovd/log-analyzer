package log_analizer;

import backend.academy.log_analizer.ZoneDateParserImpl;
import backend.academy.log_analizer.guice.ObjectFabric;
import org.junit.jupiter.api.Test;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ZoneDateParserTest {

    @Test
    public void zoneDateParseTest(){
        ZoneDateParserImpl zoneDateParser = ObjectFabric.getObject(ZoneDateParserImpl.class);
        ZonedDateTime zonedDateTime = zoneDateParser.zonedDateTimeParse("[17/May/2015:08:05:25 +0000]");
        ZonedDateTime expected = ZonedDateTime.of(2015,5,17,
            8, 5,25, 0, ZoneId.of("+0000"));
        assertEquals(expected, zonedDateTime);
    }

    @Test
    public void zoneDateParseInvalidData1Test(){
        ZoneDateParserImpl zoneDateParser = ObjectFabric.getObject(ZoneDateParserImpl.class);
        assertThrows(DateTimeParseException.class, () -> zoneDateParser.zonedDateTimeParse("17/May/2015:08:05:25 +0000"));
    }

    @Test
    public void zoneDateParseInvalidData2Test(){
        ZoneDateParserImpl zoneDateParser = ObjectFabric.getObject(ZoneDateParserImpl.class);
        assertThrows(DateTimeParseException.class, () -> zoneDateParser.zonedDateTimeParse("[17/May/2015:08:05:25]"));
    }
}
