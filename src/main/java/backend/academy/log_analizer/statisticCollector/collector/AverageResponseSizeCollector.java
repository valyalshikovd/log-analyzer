package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.statisticCollector.StatisticCollector;

public class AverageResponseSizeCollector implements StatisticCollector {

    private int count = 0;
    private long amount = 0;
    private final String id;

    public AverageResponseSizeCollector(String id) {
        this.id = id;
    }

    @Override
    public void collectStatistics(LogString logString) {
        count += 1;
        amount += logString.bodyBytesSent();
    }

    @Override
    public String getStatistics() {
        return amount / count + "";
    }

    @Override public String toString() {
        return id;
    }
}