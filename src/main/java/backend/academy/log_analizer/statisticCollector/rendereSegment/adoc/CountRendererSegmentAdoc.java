package backend.academy.log_analizer.statisticCollector.rendereSegment.adoc;

import backend.academy.log_analizer.RendererType;
import backend.academy.log_analizer.statisticCollector.RenderSegment;
import backend.academy.log_analizer.statisticCollector.rendereSegment.BaseRendererSegment;

public class CountRendererSegmentAdoc extends BaseRendererSegment {

    public CountRendererSegmentAdoc(String id) {
        super(id, RendererType.METRIC);
    }

    @Override
    public String render(String data) {
        return "|  Количество запросов  |       " + data + " ";
    }

}
