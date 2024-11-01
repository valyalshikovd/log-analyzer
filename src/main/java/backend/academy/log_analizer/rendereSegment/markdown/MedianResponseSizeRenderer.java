package backend.academy.log_analizer.rendereSegment.markdown;

import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;
import backend.academy.log_analizer.rendereSegment.RendererType;

public class MedianResponseSizeRenderer extends BaseRendererSegment {

    public MedianResponseSizeRenderer(String id) {
        super(id, RendererType.METRIC);
    }

    @Override
    public String render(String data) {
        return "| Медианный размер ответа |"+data+"b |";
    }

}
