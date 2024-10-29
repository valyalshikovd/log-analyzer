package backend.academy.log_analizer;

import java.util.ArrayList;

public class FilterChain {

    private final ArrayList<LogFilter> timeFilters = new ArrayList<>();

    public FilterChain() {
    }

    public void addTimeFilter(LogFilter timeFilter) {
        timeFilters.add(timeFilter);
    }

    public boolean checkFilters(LogString logString) {
        for (LogFilter timeFilter : timeFilters) {
            if (!timeFilter.filter(logString)) {
                return false;
            }
        }
        return true;
    }
}
