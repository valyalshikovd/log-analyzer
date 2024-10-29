package backend.academy.log_analizer.statisticCollector;

import backend.academy.log_analizer.LogString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * класс, для гибкой настройки сборщика статистики.
 * Сделан для того чтобы опционально можно было добавить различное количество различно реализованных сборщиков.
 */
public class StatisticCollectorComposer {

    private final List<StatisticCollector> collectors = new ArrayList<>();

    public StatisticCollectorComposer() {
    }

    public void addCollector(StatisticCollector collector) {
        collectors.add(collector);
    }

    public void collect(LogString logString) {
            for (StatisticCollector collector : collectors) {
                collector.collectStatistics(logString);
            }
    }

    public Map<String, String> getStatistics() {
        final double load_factor = 0.75;
        Map<String, String> statistics = new HashMap<>((int) (collectors.size() * load_factor));
        for (StatisticCollector collector : collectors) {
            statistics.put(collector.toString(), collector.getStatistics());
        }
        return statistics;
    }
}
