package backend.academy.log_analizer;

import backend.academy.log_analizer.exception.FailToReadException;
import backend.academy.log_analizer.fileWriter.FileWriter;
import backend.academy.log_analizer.fileWriter.FileWriterDefault;
import backend.academy.log_analizer.filter.FilterChain;
import backend.academy.log_analizer.parser.LogStringParser;
import backend.academy.log_analizer.rendereSegment.Renderer;
import backend.academy.log_analizer.statisticCollector.StatisticCollectorComposer;
import backend.academy.log_analizer.statisticCollector.collector.CollectorFactory;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import lombok.Setter;

@Setter
@SuppressFBWarnings(
    {"LEST_LOST_EXCEPTION_STACK_TRACE",
    "PATH_TRAVERSAL_IN",          //требования к безопасности не указаны
    "URLCONNECTION_SSRF_FD"})
public class ProcessingConveyor {

    private  FilterChain filterChain;
    private Renderer renderer;
    private StatisticCollectorComposer collectorComposer;
    private LogStringParser logStringParser;
    private FileWriter fileWriter = new FileWriterDefault();

    /**
     * Класс сделан максимально гибко, при желании можно его пополнить различными вариациями
     * фильтров, сборщиков статистики, рендереров, парсеров и пр.
     * Те классы в своб очередь тоже являются  гибко настраевыми.
     * По умолчанию класс настраивается рендерером и сборщиками статистики по умолчанию.
     * Также в это классе реализована функция чтения как из файла так и c URL.
     */
    @Inject
    public ProcessingConveyor(
        LogStringParser logStringParser
    ) {
        this.filterChain =  new FilterChain();
        this.collectorComposer = CollectorFactory.getDefaultStatisticCollector();
        this.logStringParser = logStringParser;
    }

    public void process(String pathString, String resPath) {

        Map<String, String> res = null;
        try {
            if (pathString.startsWith("http://") || pathString.startsWith("https://")) {
                URL url = new URL(pathString);
                try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)
                )) {
                    res = in.lines()
                        .map(logStringParser::parseLogString)
                        .filter(filterChain::checkFilters)
                        .collect(collectorComposer);
                }
            } else {
                Path filePath = Paths.get(pathString);
                res = Files.lines(filePath).map(logStringParser::parseLogString)
                    .filter(filterChain::checkFilters)
                    .collect(collectorComposer);
            }
        } catch (IOException e) {
            throw new FailToReadException(e.getMessage());
        }

        fileWriter.writeFile(resPath, renderer.render(res));
    }

}
