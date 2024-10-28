package backend.academy.log_analizer;

import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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

    @Override public String toString() {
        return  remoteAddr +
            " " + remoteHost +
            " " + httpXForwardedFor +
            " " + timeLocal +
            " " + request +
            " " + status +
            " " + bodyBytesSent +
            " " + httpReferer +
            " " + httpUserAgent;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogString logString = (LogString) o;
        return Objects.equals(remoteAddr, logString.remoteAddr) && Objects.equals(remoteHost, logString.remoteHost) &&
            Objects.equals(httpXForwardedFor, logString.httpXForwardedFor) &&
            Objects.equals(timeLocal, logString.timeLocal) && Objects.equals(request, logString.request) &&
            Objects.equals(status, logString.status) && Objects.equals(bodyBytesSent, logString.bodyBytesSent) &&
            Objects.equals(httpReferer, logString.httpReferer) &&
            Objects.equals(httpUserAgent, logString.httpUserAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteAddr, remoteHost, httpXForwardedFor, timeLocal, request, status, bodyBytesSent,
            httpReferer, httpUserAgent);
    }
}
