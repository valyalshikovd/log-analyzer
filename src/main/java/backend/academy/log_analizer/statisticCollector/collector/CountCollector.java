package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.statisticCollector.StatisticCollector;

public class CountCollector implements StatisticCollector {

    private int count = 0;
    private final String id;

    public CountCollector(String id) {
        this.id = id;
    }

    @Override
    public void collectStatistics(LogString logString) {
        count += 1;
    }

    @Override
    public String getStatistics() {
        return count + "";
    }

    @Override public String toString() {
        return id;
    }
}
