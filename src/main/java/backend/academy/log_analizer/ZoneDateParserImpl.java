package backend.academy.log_analizer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ZoneDateParserImpl implements ZoneDateTimeParser {

    private final static String PATTERN = "dd/MMM/yyyy:HH:mm:ss Z";

    /**
     * Строка в параметр должна быть в таком виде, с квадратными скобками [17/May/2015:08:05:25 +0000]
     */
    @Override
    public ZonedDateTime zonedDateTimeParse(String logString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN, Locale.ENGLISH);
        return ZonedDateTime.parse(logString.substring(1, logString.length() - 1), formatter);
    }

}
