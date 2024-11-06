package backend.academy.log_analizer.rendereSegment;

import backend.academy.log_analizer.rendereSegment.adoc.AverageResponseSizeRendererAdoc;
import backend.academy.log_analizer.rendereSegment.adoc.CountRendererSegmentAdoc;
import backend.academy.log_analizer.rendereSegment.adoc.DecorAdoc;
import backend.academy.log_analizer.rendereSegment.adoc.FrequentIPRendererSegmentAdoc;
import backend.academy.log_analizer.rendereSegment.adoc.FrequentResourcesRendererSegmentAdoc;
import backend.academy.log_analizer.rendereSegment.adoc.FrequentStatusRendererSegmentAdoc;
import backend.academy.log_analizer.rendereSegment.adoc.MedianResponseSizeRendererAdoc;
import backend.academy.log_analizer.rendereSegment.adoc.PercentileRendererAdoc;
import backend.academy.log_analizer.rendereSegment.markdown.AverageResponseSizeRenderer;
import backend.academy.log_analizer.rendereSegment.markdown.CountRendererSegment;
import backend.academy.log_analizer.rendereSegment.markdown.DecorMarkdown;
import backend.academy.log_analizer.rendereSegment.markdown.FrequentIPRendererSegment;
import backend.academy.log_analizer.rendereSegment.markdown.FrequentResourcesRendererSegment;
import backend.academy.log_analizer.rendereSegment.markdown.FrequentStatusRendererSegment;
import backend.academy.log_analizer.rendereSegment.markdown.MedianResponseSizeRenderer;
import backend.academy.log_analizer.rendereSegment.markdown.PercentileRenderer;
import lombok.experimental.UtilityClass;

@SuppressWarnings("MultipleStringLiterals")
@UtilityClass
public class RendererFactory {

    public static Renderer getDefaultMarkdownRenderer(String fileName, String start, String end) {
        Renderer renderer = new RendererImpl(new DecorMarkdown(fileName, start, end));
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
        renderer.addRenderSegment(
            new FrequentIPRendererSegment("Наиболее частые IP")
        );
        renderer.addRenderSegment(
            new MedianResponseSizeRenderer("Медиана размера ответа")
        );
        return renderer;
    }

    public static Renderer getDefaultAdocRenderer(String fileName, String start, String end) {
        Renderer renderer = new RendererImpl(new DecorAdoc(fileName, start, end));
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
        renderer.addRenderSegment(
            new FrequentIPRendererSegmentAdoc("Наиболее частые IP")
        );
        renderer.addRenderSegment(
            new MedianResponseSizeRendererAdoc("Медиана размера ответа")
        );

        return renderer;
    }
}
