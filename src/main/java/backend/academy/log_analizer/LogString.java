package backend.academy.log_analizer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class LogString {

    /**
     * DTO LogString
     */
    private static final String DEFAULT_VALUE = "-";
    private String remoteAddr;
    private String remoteHost = DEFAULT_VALUE;
    private String httpXForwardedFor;
    private ZonedDateTime timeLocal;
    private String request;
    private Integer status;
    private Integer bodyBytesSent;
    private String httpReferer = DEFAULT_VALUE;
    private String httpUserAgent;
}
