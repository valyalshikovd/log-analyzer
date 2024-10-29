package backend.academy.log_analizer;

import backend.academy.log_analizer.statisticCollector.StatisticCollectorComposer;
import backend.academy.log_analizer.statisticCollector.collector.AverageResponseSizeCollector;
import backend.academy.log_analizer.statisticCollector.collector.CountCollector;
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
            new CountCollector("Количество запросов: ")
        );

        collector.addCollector(
            new AverageResponseSizeCollector("Средний размер ответа")
        );

        try (Stream<String> lines = Files.lines(path)) {
            lines
                .map(logStringParser::parseLogString)
                .filter(chain::checkFilters)
                .forEach(collector::collect);

         //   System.out.println(collector.getStatistics());
        } catch (IOException e) {
            return;
        }
    }



}
