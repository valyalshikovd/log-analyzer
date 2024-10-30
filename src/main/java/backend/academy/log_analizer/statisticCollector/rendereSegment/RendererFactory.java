package backend.academy.log_analizer.statisticCollector.rendereSegment;

import backend.academy.log_analizer.statisticCollector.Renderer;
import backend.academy.log_analizer.statisticCollector.RendererImpl;
import backend.academy.log_analizer.statisticCollector.rendereSegment.adoc.AverageResponseSizeRendererAdoc;
import backend.academy.log_analizer.statisticCollector.rendereSegment.adoc.CountRendererSegmentAdoc;
import backend.academy.log_analizer.statisticCollector.rendereSegment.adoc.FrequentResourcesRendererSegmentAdoc;
import backend.academy.log_analizer.statisticCollector.rendereSegment.adoc.FrequentStatusRendererSegmentAdoc;
import backend.academy.log_analizer.statisticCollector.rendereSegment.adoc.HeaderAdoc;
import backend.academy.log_analizer.statisticCollector.rendereSegment.adoc.PercentileRendererAdoc;
import backend.academy.log_analizer.statisticCollector.rendereSegment.markdown.AverageResponseSizeRenderer;
import backend.academy.log_analizer.statisticCollector.rendereSegment.markdown.CountRendererSegment;
import backend.academy.log_analizer.statisticCollector.rendereSegment.markdown.FrequentResourcesRendererSegment;
import backend.academy.log_analizer.statisticCollector.rendereSegment.markdown.FrequentStatusRendererSegment;
import backend.academy.log_analizer.statisticCollector.rendereSegment.markdown.HeaderMarkdown;
import backend.academy.log_analizer.statisticCollector.rendereSegment.markdown.PercentileRenderer;

public class RendererFactory {

    public static Renderer getDefaultMarkdownRenderer() {
        Renderer renderer = new RendererImpl(new HeaderMarkdown());
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
        return renderer;
    }
    public static Renderer getDefaultAdocRenderer() {
        Renderer renderer = new RendererImpl(new HeaderAdoc());
        renderer.addRenderSegment(
            new CountRendererSegmentAdoc("Количество запросов")
        );
        renderer.addRenderSegment(
            new AverageResponseSizeRendererAdoc("Средний размер ответа")
        );
        renderer.addRenderSegment(
            new FrequentResourcesRendererSegmentAdoc("Наиболее запрашиваемые ресурсы")
        );

        renderer.addRenderSegment(
            new FrequentStatusRendererSegmentAdoc("Наиболее частые статусы ответов")
        );
        renderer.addRenderSegment(
            new PercentileRendererAdoc("Перцентиль")
        );
        return renderer;
    }
}
