package backend.academy.log_analizer.cmdReader;

import backend.academy.log_analizer.ProcessingConveyor;
import backend.academy.log_analizer.filter.FilterChain;
import backend.academy.log_analizer.parser.LogStringParserImpl;
import backend.academy.log_analizer.parser.ZoneDateParserImpl;
import backend.academy.log_analizer.rendereSegment.RendererFactory;
import backend.academy.log_analizer.statisticCollector.collector.CollectorFactory;
import com.beust.jcommander.JCommander;
import java.time.DateTimeException;
import java.time.OffsetDateTime;

public class Reader {

    private Args args;

    public Reader(String[] args) {

        Args analyzerArgs = new Args();
        JCommander jCommander = JCommander.newBuilder()
            .addObject(analyzerArgs)
            .build();
        try {
            jCommander.parse(args);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            jCommander.usage();
        }

        this.args = analyzerArgs;

    }

    /**
     * Метод, собирающий конвеер в зависимости от параметров. Программа принимает следующие параметры:
     * --path (обязательный) - путь к файлу-источнику
     * --save-path (обязательный) - путь, где нужно сохранить отчет
     * --format (обязательный) - формат отчета "md" или "adoc"
     * --from - время в формате ISO8601 (к примеру 2023-11-04T12:34:56+02:00) с которого начинать учитывать логи
     * --to - время в формате ISO8601 (к примеру 2023-11-04T12:34:56+02:00) до которого  учитывать логи
     * --filter-field - название параметра, по которому производить фильтрацию
     * --filter-value - значение этого параметра
     */
    public void start() {
        try {
            ProcessingConveyor p = new ProcessingConveyor(new LogStringParserImpl(new ZoneDateParserImpl()));

            p.collectorComposer(CollectorFactory.getDefaultStatisticCollector());

            String suffix;

            if ("adoc".equals(args.format())) {
                p.renderer(RendererFactory.getDefaultAdocRenderer(args.path(), args.from(), args.to()));
                suffix = ".adoc";
            } else {
                p.renderer(RendererFactory.getDefaultMarkdownRenderer(args.path(), args.from(), args.to()));
                suffix = ".md";
            }

            FilterChain filterChain = new FilterChain();

            if (args.from() != null) {
                filterChain.addAfterFilter(OffsetDateTime.parse(args.from()).toZonedDateTime());
            }
            if (args.to() != null) {
                filterChain.addBeforeFilter(OffsetDateTime.parse(args.to()).toZonedDateTime());
            }
            if (args.filterField() != null && args.filterValue() != null) {
                String value = args.filterValue();
                filterChain.addTimeFilter(
                    //название полей по которым можно делать фильтрацию
                    (logString) -> switch (args.filterField()) {
                        case "remoteAddr" -> logString.remoteAddr().contains(value);
                        case "remoteHost" -> logString.remoteHost().contains(value);
                        case "httpXForwardedFor" -> logString.httpXForwardedFor().contains(value);
                        case "request" -> logString.request().contains(value);
                        case "status" -> logString.status() == Integer.parseInt(value);
                        case "bodyBytesSent" -> logString.bodyBytesSent() == Integer.parseInt(value);
                        case "httpReferer" -> logString.httpReferer().contains(value);
                        case "httpUserAgent" -> logString.httpUserAgent().contains(value);
                        default -> false;
                    }
                );
            }
            p.filterChain(filterChain);
            p.process(args.path(), args.savePath() + suffix);
        } catch (DateTimeException e) {
            System.err.println("Не правильный формат даты: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Не удалось собрать статистику " + e.getMessage());
        }
    }
}
