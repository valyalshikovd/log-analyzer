package backend.academy.log_analizer.rendereSegment.markdown;

import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;
import backend.academy.log_analizer.rendereSegment.RendererType;

public class AverageResponseSizeRenderer extends BaseRendererSegment {

    public AverageResponseSizeRenderer(String id) {
        super(id, RendererType.METRIC);
    }

    @Override
    public String render(String data) {
        return "| Средний размер ответа |" + data + "b |";
    }

}
