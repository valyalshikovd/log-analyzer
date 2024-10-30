package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import backend.academy.log_analizer.statisticCollector.StatisticCollector;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FrequentResourcesCollector implements StatisticCollector {

    private final HashMap<String, Integer> frequentResources = new HashMap<>();

    private final String id;
    private final int limit;

    public FrequentResourcesCollector(String id, int limit) {
        this.id = id;
        this.limit = limit;
    }

    @Override
    public void collectStatistics(LogString logString) {

        if (!frequentResources.containsKey(logString.request())) {
            frequentResources.put(logString.request(), 1);
            return;
        }

        frequentResources.put(logString.request(), frequentResources.get(logString.request()) + 1);
    }

    @Override
    public String getStatistics() {

        List<String> sortedKeys = frequentResources.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .limit(limit)
            .toList();

        StringBuilder statistics = new StringBuilder();
        for (String key : sortedKeys) {
            statistics.append(key).append(": ").append(frequentResources.get(key)).append("\n");
        }
        return statistics.toString();
    }

    @Override public String toString() {
        return id;
    }
}
