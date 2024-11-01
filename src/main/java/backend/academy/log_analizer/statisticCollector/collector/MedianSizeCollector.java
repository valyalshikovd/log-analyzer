package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.statisticCollector.StatisticCollector;
import org.apache.commons.collections.list.TreeList;
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
        return list.get(list.size() / 2) + "";
    }

    @Override public String toString() {
        return id;
    }
}
