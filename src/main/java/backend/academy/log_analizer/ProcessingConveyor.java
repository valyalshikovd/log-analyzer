package backend.academy.log_analizer;

import backend.academy.log_analizer.statisticCollector.Renderer;
import backend.academy.log_analizer.statisticCollector.RendererImpl;
import backend.academy.log_analizer.statisticCollector.StatisticCollectorComposer;
import backend.academy.log_analizer.statisticCollector.collector.AverageResponseSizeCollector;
import backend.academy.log_analizer.statisticCollector.collector.CountCollector;
import backend.academy.log_analizer.statisticCollector.collector.FrequentResourcesCollector;
import backend.academy.log_analizer.statisticCollector.collector.FrequentStatusCollector;
import backend.academy.log_analizer.statisticCollector.collector.PercentileCollector;
import backend.academy.log_analizer.statisticCollector.rendereSegment.AverageResponseSizeRenderer;
import backend.academy.log_analizer.statisticCollector.rendereSegment.CountRendererSegment;
import backend.academy.log_analizer.statisticCollector.rendereSegment.FrequentResourcesRendererSegment;
import backend.academy.log_analizer.statisticCollector.rendereSegment.FrequentStatusRendererSegment;
import backend.academy.log_analizer.statisticCollector.rendereSegment.PercentileRenderer;
import jakarta.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

public class ProcessingConveyor {


    private final LogStringParser logStringParser;

    @Inject
    public ProcessingConveyor(LogStringParser logStringParser) {
        this.logStringParser = logStringParser;
    }

    public void process(String pathString) {

        Path path = Paths.get(pathString);
        FilterChain chain = new FilterChain();

        ZonedDateTime now = ZonedDateTime.now();

        chain.addTimeFilter(
            (LogString logString) -> logString.timeLocal().isBefore(now)
        );

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

        Renderer renderer = new RendererImpl();
        renderer.addRenderSegment(
            new CountRendererSegment("Количество запросов")
        );
        renderer.addRenderSegment(
            new AverageResponseSizeRenderer("Средний размер ответа")
        );
        renderer.addRenderSegment(
            new FrequentResourcesRendererSegment("Наиболее запрашиваемые ресурсы")
        );

        renderer.addRenderSegment(
            new FrequentStatusRendererSegment("Наиболее частые статусы ответов")
        );

        renderer.addRenderSegment(
            new PercentileRenderer("Перцентиль")
        );

        try (Stream<String> lines = Files.lines(path)) {
            lines
                .map(logStringParser::parseLogString)
                .filter(chain::checkFilters)
                .forEach(collector::collect);
        } catch (IOException e) {
            return;
        }

        System.out.println(renderer.render(collector.getStatistics()));
    }



}
