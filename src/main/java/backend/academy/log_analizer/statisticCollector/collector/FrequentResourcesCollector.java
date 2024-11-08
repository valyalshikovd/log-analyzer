package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FrequentResourcesCollector extends AbstractCollector {

    private final String id;
    private final int limit;

    public FrequentResourcesCollector(String id, int limit) {
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

            if (!frequentResources.containsKey(logString.request())) {
                frequentResources.put(logString.request(), 1);
                return;
            }

            frequentResources.put(logString.request(), frequentResources.get(logString.request()) + 1);
        };
    }

    @Override
    public Function<Object, String> finisher() {
        return (hashMap) -> {
            Map<String, Integer> frequentResourcesDownCasted = (Map<String, Integer>) hashMap;
            List<String> keys = frequentResourcesDownCasted.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(limit)
                .toList();

            StringBuilder statistics = new StringBuilder();
            for (String key : keys) {
                statistics.append(key).append(": ").append(frequentResourcesDownCasted.get(key)).append('\n');
            }
            return statistics.toString();
        };
    }
}
