package backend.academy.log_analizer.statisticCollector.rendereSegment.markdown;

import backend.academy.log_analizer.RendererType;
import backend.academy.log_analizer.statisticCollector.rendereSegment.BaseRendererSegment;

public class AverageResponseSizeRenderer extends BaseRendererSegment {

    public AverageResponseSizeRenderer(String id) {
        super(id, RendererType.METRIC);
    }

    @Override
    public String render(String data) {
        return "| Средний размер ответа |"+data+"b |";
    }

}
