package backend.academy.log_analizer.statisticCollector;

import backend.academy.log_analizer.RendererType;
import backend.academy.log_analizer.statisticCollector.rendereSegment.Header;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RendererImpl implements Renderer {

    private final List<RenderSegment> metrics = new ArrayList<>();
    private final List<RenderSegment> tables = new ArrayList<>();

    private final Header header;

    public RendererImpl(Header header) {
        this.header = header;
    }

    @Override
    public String render(Map<String, String> data) {

        StringBuilder sb = new StringBuilder();
        sb.append(header.getTitle());
        sb.append(header.getHeader());
        metrics.forEach(
            segment -> {
                if (data.containsKey(segment.getId())) {
                    sb.append(segment.render(data.get(segment.getId())));
                    sb.append("\n");
                }
            }
        );
        sb.append(header.getFooter());
        tables.forEach(
            segment -> {
                if (data.containsKey(segment.getId())) {
                    sb.append(segment.render(data.get(segment.getId())));
                    sb.append("\n");
                }
            }
        );
        return sb.toString();
    }

    @Override
    public void addRenderSegment(RenderSegment renderSegment) {

        if (renderSegment.getType() == RendererType.METRIC) {
            metrics.add(renderSegment);
        }

        if (renderSegment.getType() == RendererType.TABLE) {
            tables.add(renderSegment);
        }
    }



}
