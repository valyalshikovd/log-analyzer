package backend.academy.log_analizer.parser;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.exception.InvalidLogString;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.inject.Inject;
import java.time.format.DateTimeParseException;

@SuppressFBWarnings("LEST_LOST_EXCEPTION_STACK_TRACE")
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

    private static final int STRING_LENGTH = 12;

    private final ZoneDateTimeParser zoneDateTimeParser;

    @Inject
    public LogStringParserImpl(ZoneDateTimeParser zoneDateTimeParser) {
        this.zoneDateTimeParser = zoneDateTimeParser;
    }

    @Override
    public LogString parseLogString(String logString) {
        try {
            String[] logStringEntities = logString.split(" ");
            if (logStringEntities.length < STRING_LENGTH) {
                throw new IllegalArgumentException("Wrong size of log string");
            }

            return LogString
                .builder()
                .remoteAddr(logStringEntities[REMOTE_ADDRESS_INDEX])
                .remoteHost(logStringEntities[REMOTE_HOST_INDEX])
                .httpXForwardedFor(logStringEntities[HTTP_X_FORWARDED_FOR_INDEX])
                .timeLocal(zoneDateTimeParser.zonedDateTimeParse(
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
                .httpUserAgent(createHttpUserAgentString(logStringEntities))
                .build();
        } catch (IllegalArgumentException e) {
            throw new InvalidLogString("Invalid log string");
        } catch (DateTimeParseException e) {
            throw new InvalidLogString("Invalid date");
        }
    }

    private String createHttpUserAgentString(String[] strings) {
        StringBuilder httpUserAgentBuilder = new StringBuilder();
        for (int i = HTTP_USER_AGENT_1_INDEX; i < strings.length; i++) {
            httpUserAgentBuilder.append(strings[i]);
            httpUserAgentBuilder.append(' ');
        }
        httpUserAgentBuilder.deleteCharAt(httpUserAgentBuilder.length() - 1);
        return httpUserAgentBuilder.toString();
    }
}
