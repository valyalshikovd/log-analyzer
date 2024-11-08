package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Дополнительная статистика
 */
public class FrequentIPCollector extends AbstractCollector {

    private final String id;
    private final int limit;

    public FrequentIPCollector(String id, int limit) {
        this.id = id;
        this.limit = limit;
    }

    @Override public String toString() {
        return id;
    }

    @Override
    public Supplier<Object> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Object, LogString> accumulator() {
        return (hashMap, logString) -> {
            Map<String, Integer> frequentResources = (Map<String, Integer>) hashMap;

            if (!frequentResources.containsKey(logString.remoteAddr())) {
                frequentResources.put(logString.remoteAddr(), 1);
                return;
            }

            frequentResources.put(logString.remoteAddr(), frequentResources.get(logString.remoteAddr()) + 1);
        };
    }

    @Override
    public Function<Object, String> finisher() {
        return (frequentResources) -> {
            Map<String, Integer> frequentResourcesDownCasted = (Map<String, Integer>) frequentResources;
            List<String> sortedKeys = frequentResourcesDownCasted.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(limit)
                .toList();

            StringBuilder statistics = new StringBuilder();
            for (String key : sortedKeys) {
                statistics.append(key).append(" : ").append(frequentResourcesDownCasted.get(key)).append('\n');
            }
            return statistics.toString();
        };
    }
}
