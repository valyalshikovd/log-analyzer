package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FrequentStatusCollector extends AbstractCollector {


    private final String id;
    private final int limit;

    public FrequentStatusCollector(String id, int limit) {
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
            Map<Integer, Integer> hashMapDownCasted = (Map<Integer, Integer>) hashMap;
            if (!hashMapDownCasted.containsKey(logString.status())) {
                hashMapDownCasted.put(logString.status(), 1);
                return;
            }

            hashMapDownCasted.put(logString.status(), hashMapDownCasted.get(logString.status()) + 1);
        };
    }

    @Override
    public Function<Object, String> finisher() {
        return (hashMap) -> {
            Map<Integer, Integer> hashMapDownCasted = (Map<Integer, Integer>) hashMap;
            List<Integer> sortedKeys = hashMapDownCasted.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(limit)
                .toList();

            StringBuilder statistics = new StringBuilder();
            for (Integer key : sortedKeys) {
                statistics.append(key).append(':').append(hashMapDownCasted.get(key)).append('\n');
            }
            return statistics.toString();
        };
    }
}
