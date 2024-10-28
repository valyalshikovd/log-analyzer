package backend.academy.log_analizer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ZoneDateParserImpl implements ZoneDateTimeParser{

    private final String pattern = "dd/MMM/yyyy:HH:mm:ss Z";

    @Override
    public ZonedDateTime zonedDateTimeParse(String logString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        return ZonedDateTime.parse(logString.substring(1, logString.length() - 1), formatter);
    }

}
