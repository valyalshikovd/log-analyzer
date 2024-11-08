package backend.academy.log_analizer.statisticCollector;

import backend.academy.log_analizer.LogString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * класс, для гибкой настройки сборщика статистики.
 * Сделан для того чтобы опционально можно было добавить различное количество различно реализованных сборщиков.
 * Использую интерфейс Collector
 */
public class StatisticCollectorComposer implements Collector<LogString, Map<String, Object>, Map<String, String>> {

    private final List<Collector<LogString, Object, String>> collectors = new ArrayList<>();

    public StatisticCollectorComposer() {
    }

    public void addCollector(Collector<LogString, Object, String> collector) {
        collectors.add(collector);
    }

    @Override
    public Supplier<Map<String, Object>> supplier() {
        return () -> {
            HashMap<String, Object> map = new HashMap<>();
            collectors.forEach((collector) -> {
                map.put(collector.toString(),  collector.supplier().get());
            });
            return map;
        };
    }

    @Override
    public BiConsumer<Map<String, Object>, LogString> accumulator() {
        return (map, logString) -> {
            collectors.forEach((collector) -> {
                collector.accumulator().accept(map.get(collector.toString()), logString);
            });
        };
    }

    @Override
    public BinaryOperator<Map<String, Object>> combiner() {
        return null;
    }

    @Override
    public Function<Map<String, Object>, Map<String, String>> finisher() {
        return (map) -> {
            Map<String, String> resMap = new HashMap<>();
            collectors.forEach(collector -> {
                resMap.put(collector.toString(), collector.finisher().apply(map.get(collector.toString())));
            });
            return resMap;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
