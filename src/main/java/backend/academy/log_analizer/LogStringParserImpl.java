package backend.academy.log_analizer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogStringParserImpl implements LogStringParser {

    private static final int REMOTE_ADDRESS_INDEX = 0;
    private static final int REMOTE_HOST_INDEX = 1;
    private static final int HTTP_X_FORWARDED_FOR_INDEX = 2;
    private static final int TIME_LOCAL_1_INDEX = 3;
    private static final int TIME_LOCAL_2_INDEX = 4;
    private static final int REQUEST_1_INDEX = 5;
    private static final int REQUEST_2_INDEX = 6;
    private static final int REQUEST_3_INDEX = 7;
    private static final int STATUS_INDEX = 8;
    private static final int BODY_BYTES_SENT_INDEX = 9;
    private static final int HTTP_REFERER_INDEX = 10;
    private static final int HTTP_USER_AGENT_1_INDEX = 11;
    private static final int HTTP_USER_AGENT_2_INDEX = 12;
    private static final int HTTP_USER_AGENT_3_INDEX = 13;
    private static final int STRING_LENGTH = 14;


    @Override
    public LogString parseLogString(String logString) {
        try {
            String[] logStringEntities = logString.split(" ");
            if (logStringEntities.length != STRING_LENGTH) {
                throw new IllegalArgumentException("Wrong size of log string");
            }

            return LogString
                .builder()
                .remoteAddr(logStringEntities[REMOTE_ADDRESS_INDEX])
                .remoteHost(logStringEntities[REMOTE_HOST_INDEX])
                .httpXForwardedFor(logStringEntities[HTTP_X_FORWARDED_FOR_INDEX])
                .timeLocal(zonedDateTimeParse(
                    logStringEntities[TIME_LOCAL_1_INDEX]
                        + " "
                        + logStringEntities[TIME_LOCAL_2_INDEX])
                )
                .request(
                    logStringEntities[REQUEST_1_INDEX]
                        + " "
                        + logStringEntities[REQUEST_2_INDEX]
                        + " "
                        + logStringEntities[REQUEST_3_INDEX]
                )
                .status(Integer.valueOf(logStringEntities[STATUS_INDEX]))
                .bodyBytesSent(Integer.valueOf(logStringEntities[BODY_BYTES_SENT_INDEX]))
                .httpReferer(logStringEntities[HTTP_REFERER_INDEX])
                .httpUserAgent(
                    logStringEntities[HTTP_USER_AGENT_1_INDEX]
                        + " "
                        + logStringEntities[HTTP_USER_AGENT_2_INDEX]
                        + " "
                        + logStringEntities[HTTP_USER_AGENT_3_INDEX]
                )
                .build();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private ZonedDateTime zonedDateTimeParse(String logString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        return ZonedDateTime.parse(logString.substring(1, logString.length() - 1), formatter);
    }
}
