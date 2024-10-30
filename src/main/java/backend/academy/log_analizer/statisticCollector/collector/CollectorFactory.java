package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.statisticCollector.StatisticCollectorComposer;

public class CollectorFactory {


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
            new FrequentResourcesCollector("Наиболее запрашиваемые ресурсы", 5)
        );
        collector.addCollector(
            new FrequentStatusCollector("Наиболее частые статусы ответов", 5)
        );
        return collector;
    }
}
