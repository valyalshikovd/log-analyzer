package backend.academy.log_analizer.statisticCollector.rendereSegment.markdown;

import backend.academy.log_analizer.RendererType;
import backend.academy.log_analizer.statisticCollector.RenderSegment;
import backend.academy.log_analizer.statisticCollector.rendereSegment.BaseRendererSegment;

public class PercentileRenderer extends BaseRendererSegment {

    public PercentileRenderer(String id) {
        super(id, RendererType.METRIC);
    }

    @Override
    public String render(String data) {
        return "|  95p размера ответа |"+data+"b |";
    }


}
