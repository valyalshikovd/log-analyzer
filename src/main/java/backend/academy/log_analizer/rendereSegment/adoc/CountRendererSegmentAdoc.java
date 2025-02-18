package backend.academy.log_analizer.rendereSegment.adoc;

import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;
import backend.academy.log_analizer.rendereSegment.RendererType;

public class CountRendererSegmentAdoc extends BaseRendererSegment {

    public CountRendererSegmentAdoc(String id) {
        super(id, RendererType.METRIC);
    }

    @Override
    public String render(String data) {
        return "|  Количество запросов  |       " + data + " ";
    }

}
