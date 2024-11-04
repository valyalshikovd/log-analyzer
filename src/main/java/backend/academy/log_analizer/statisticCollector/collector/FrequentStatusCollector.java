package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.statisticCollector.StatisticCollector;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequentStatusCollector implements StatisticCollector {


    private final HashMap<Integer, Integer> frequentResources = new HashMap<>();

    private final String id;
    private final int limit;

    public FrequentStatusCollector(String id, int limit) {
        this.id = id;
        this.limit = limit;
    }

    @Override
    public void collectStatistics(LogString logString) {

        if (!frequentResources.containsKey(logString.status())) {
            frequentResources.put(logString.status(), 1);
            return;
        }

        frequentResources.put(logString.status(), frequentResources.get(logString.status()) + 1);
    }

    @Override
    public String getStatistics() {

        List<Integer> sortedKeys = frequentResources.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .limit(limit)
            .toList();

        StringBuilder statistics = new StringBuilder();
        for (Integer key : sortedKeys) {
            statistics.append(key).append(':').append(frequentResources.get(key)).append('\n');
        }
        return statistics.toString();
    }

    @Override public String toString() {
        return id;
    }
}
