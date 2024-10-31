package backend.academy.log_analizer;

import backend.academy.log_analizer.exception.FailToReadException;
import backend.academy.log_analizer.fileWriter.FileWriterDefault;
import backend.academy.log_analizer.filter.FilterChain;
import backend.academy.log_analizer.parser.LogStringParser;
import backend.academy.log_analizer.rendereSegment.Renderer;
import backend.academy.log_analizer.statisticCollector.StatisticCollectorComposer;
import backend.academy.log_analizer.statisticCollector.collector.CollectorFactory;
import backend.academy.log_analizer.rendereSegment.RendererFactory;
import jakarta.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;

public class ProcessingConveyor {

    private final FilterChain filterChain;
    private final Renderer renderer;
    private final StatisticCollectorComposer collectorComposer;
    private final LogStringParser logStringParser;

    @Inject
    public ProcessingConveyor(
        LogStringParser logStringParser
    ) {
        this.filterChain =  new FilterChain();

        ZonedDateTime now = ZonedDateTime.now();

        this.filterChain.addTimeFilter(
            (LogString logString) -> logString.timeLocal().isBefore(now)
        );

        this.renderer = RendererFactory.getDefaultMarkdownRenderer();
        this.collectorComposer = CollectorFactory.getDefaultStatisticCollector();
        this.logStringParser = logStringParser;
    }

    public void process(String pathString) {

        try {
            if (pathString.startsWith("http://") || pathString.startsWith("https://")) {
                URL url = new URL(pathString);
                try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    in.lines()
                        .map(logStringParser::parseLogString)
                        .filter(filterChain::checkFilters)
                        .forEach(collectorComposer::collect);
                }
            } else {
                Path filePath = Paths.get(pathString);
                Files.lines(filePath).map(logStringParser::parseLogString)
                    .filter(filterChain::checkFilters)
                    .forEach(collectorComposer::collect);
            }
        } catch (IOException e) {
            throw new FailToReadException(e.getMessage());
        }

        new FileWriterDefault().writeFile("res.md", renderer.render(collectorComposer.getStatistics()));
    }

}
