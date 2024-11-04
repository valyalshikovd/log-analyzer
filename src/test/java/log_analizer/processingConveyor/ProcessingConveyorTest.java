package log_analizer.processingConveyor;

import backend.academy.log_analizer.ProcessingConveyor;
import backend.academy.log_analizer.filter.FilterChain;
import backend.academy.log_analizer.parser.LogStringParserImpl;
import backend.academy.log_analizer.parser.ZoneDateParserImpl;
import backend.academy.log_analizer.parser.ZoneDateTimeParser;
import backend.academy.log_analizer.rendereSegment.RendererFactory;
import backend.academy.log_analizer.statisticCollector.collector.CollectorFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessingConveyorTest {

    @Test
    public void test1() {

        ProcessingConveyor p = new ProcessingConveyor(new LogStringParserImpl(new ZoneDateParserImpl()));
        p.collectorComposer(CollectorFactory.getDefaultStatisticCollector());
        String suffix;
        p.renderer(RendererFactory.getDefaultMarkdownRenderer());
        suffix = ".md";
        FilterChain filterChain = new FilterChain();
        ZoneDateTimeParser parser = new ZoneDateParserImpl();
        filterChain.addAfterFilter(parser.zonedDateTimeParse("[17/May/2015:08:05:25 +0000]"));
        p.filterChain(filterChain);
        MockWriter mw = new MockWriter();
        p.fileWriter(mw);
        p.process("src/main/resources/nginx_logs.txt", suffix);

        assertEquals(
            """
                ## Общая информация \s
                |        Метрика        |     Значение |
                |:---------------------:|-------------:|
                |  Количество запросов  |       51408 |
                | Средний размер ответа |660201.9500000001b |
                |  95p размера ответа |200b |
                | Медианный размер ответа |334b |

                ## Наиболее частозапрашиваемые ресурсы
                |        Ресурс         |   Количество |
                |:---------------------:|-------------:|
                |"GET /downloads/product_1 HTTP/1.1"| 30237 |
                |"GET /downloads/product_2 HTTP/1.1"| 21015 |
                |"GET /downloads/product_3 HTTP/1.1"| 73 |
                |"HEAD /downloads/product_2 HTTP/1.1"| 70 |
                |"HEAD /downloads/product_1 HTTP/1.1"| 13 |

                ## Наиболее частые коды ответов
                |      Код ответа       |   Количество |
                |:---------------------:|-------------:|
                |404|33862 |
                |304|13298 |
                |200|4020 |
                |206|186 |
                |403|38 |

                ## Наиболее частые клиенты:
                |        IP клиента         |   Количество |
                |:---------------------:|-------------:|
                |216.46.173.126| 2350 |
                |180.179.174.219| 1720 |
                |204.77.168.241| 1439 |
                |65.39.197.164| 1365 |
                |80.91.33.133| 1190 |

                """
            , mw.getContent());

    }

    @Test
    void test2() {
        ProcessingConveyor p = new ProcessingConveyor(new LogStringParserImpl(new ZoneDateParserImpl()));
        p.collectorComposer(CollectorFactory.getDefaultStatisticCollector());
        String suffix;
        p.renderer(RendererFactory.getDefaultAdocRenderer());
        suffix = ".md";
        FilterChain filterChain = new FilterChain();
        ZoneDateTimeParser parser = new ZoneDateParserImpl();
        filterChain.addAfterFilter(parser.zonedDateTimeParse("[30/May/2015:08:05:25 +0000]"));
        p.filterChain(filterChain);
        MockWriter mw = new MockWriter();
        p.fileWriter(mw);
        p.process("src/main/resources/nginx_logs.txt", suffix);

        assertEquals(
            """
                == Общая информация\s\s
                |===
                |        Метрика        |     Значение
                |  Количество запросов  |       14152\s
                | Средний размер ответа |615723.01b\s
                |  95p размера ответа |304b\s
                | Медианный размер ответа |334b\s
                |===
                == Наиболее частозапрашиваемые ресурсы
                |===
                |        Ресурс         |   Количество |
                |"GET /downloads/product_1 HTTP/1.1"| 8546 |
                |"GET /downloads/product_2 HTTP/1.1"| 5582 |
                |"GET /downloads/product_3 HTTP/1.1"| 19 |
                |"HEAD /downloads/product_2 HTTP/1.1"| 5 |
                |===

                == Наиболее частые коды ответов
                |===
                |      Код ответа       |   Количество |
                |404|9442 |
                |304|3575 |
                |200|1074 |
                |206|56 |
                |403|5 |
                |===

                == Наиболее частые клиенты
                |===
                |        IP клиента         |   Количество |
                |216.46.173.126 | 650 |
                |180.179.174.219 | 564 |
                |74.125.60.158 | 410 |
                |65.39.197.164 | 405 |
                |80.91.33.133 | 377 |
                |===

                """, mw.getContent()
        );
    }
}
