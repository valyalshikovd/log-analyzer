package backend.academy.log_analizer;

import java.time.ZonedDateTime;

public interface ZoneDateTimeParser {

    ZonedDateTime zonedDateTimeParse(String logString);

}
