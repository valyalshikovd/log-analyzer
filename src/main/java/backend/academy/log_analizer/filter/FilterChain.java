package backend.academy.log_analizer.filter;

import backend.academy.log_analizer.LogString;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class FilterChain {

    private final ArrayList<LogFilter> filters = new ArrayList<>();

    /**
     * Класс цепочки фильтров. В него можно добавить свой фильтр соответствующий интерфейсу LogFilter
     */
    public FilterChain() {
    }

    public void addTimeFilter(LogFilter timeFilter) {
        filters.add(timeFilter);
    }

    public boolean checkFilters(LogString logString) {
        for (LogFilter timeFilter : filters) {
            if (!timeFilter.filter(logString)) {
                return false;
            }
        }
        return true;
    }

    public void addBeforeFilter(ZonedDateTime zonedDateTime) {
        addTimeFilter(
            (LogString logString) -> logString.timeLocal().isBefore(zonedDateTime)
        );
    }

    public void addAfterFilter(ZonedDateTime zonedDateTime) {
        addTimeFilter(
            (LogString logString) -> logString.timeLocal().isAfter(zonedDateTime)
        );
    }
}
