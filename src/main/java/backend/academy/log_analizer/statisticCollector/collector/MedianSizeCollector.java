package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.statisticCollector.StatisticCollector;
import java.util.ArrayList;
import java.util.Collections;

public class MedianSizeCollector implements StatisticCollector {
    private ArrayList<Integer> list = new ArrayList<>();
    private final String id;

    public MedianSizeCollector(String id) {
        this.id = id;
    }

    @Override
    public void collectStatistics(LogString logString) {
        list.add(logString.bodyBytesSent());
        Collections.sort(list);
    }

    @Override
    public String getStatistics() {
        try {
            return list.get(list.size() / 2) + "";
        } catch (Exception e) {
            return "-";
        }
    }

    @Override public String toString() {
        return id;
    }
}
