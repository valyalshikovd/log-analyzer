package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.statisticCollector.StatisticCollectorComposer;
import lombok.experimental.UtilityClass;

@SuppressWarnings("MultipleStringLiterals")
@UtilityClass
public class CollectorFactory {

    private static final int DEFAULT_LIMIT = 5;

    public static StatisticCollectorComposer getDefaultStatisticCollector() {
        StatisticCollectorComposer collector = new StatisticCollectorComposer();
        collector.addCollector(
            new CountCollector("Количество запросов")
        );
        collector.addCollector(
            new AverageResponseSizeCollector("Средний размер ответа")
        );
        collector.addCollector(
            new PercentileCollector("Перцентиль")
        );
        collector.addCollector(
            new FrequentResourcesCollector("Наиболее запрашиваемые ресурсы", DEFAULT_LIMIT)
        );
        collector.addCollector(
            new FrequentStatusCollector("Наиболее частые статусы ответов", DEFAULT_LIMIT)
        );
        collector.addCollector(
            new FrequentIPCollector("Наиболее частые IP", DEFAULT_LIMIT)
        );
        collector.addCollector(
            new MedianSizeCollector("Медиана размера ответа")
        );
        return collector;
    }
}
