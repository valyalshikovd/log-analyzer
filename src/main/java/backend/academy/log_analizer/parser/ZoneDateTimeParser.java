package backend.academy.log_analizer.parser;

import java.time.ZonedDateTime;

public interface ZoneDateTimeParser {

    ZonedDateTime zonedDateTimeParse(String logString);

}
