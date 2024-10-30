package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.statisticCollector.StatisticCollector;
import java.util.ArrayList;
import java.util.List;

public class PercentileCollector implements StatisticCollector {
    private int count = 0;
    private final String id;

    /**
     * хранить все ответы не эффективно
     */
    private List<Integer> list = new ArrayList<>();

    public PercentileCollector(String id) {
        this.id = id;
    }

    @Override
    public void collectStatistics(LogString logString) {
        count += 1;
        list.add(logString.status());
    }

    @Override
    public String getStatistics() {
        return list.get((int) Math.round((count) * 0.95)) + "";
    }

    @Override public String toString() {
        return id;
    }
}
