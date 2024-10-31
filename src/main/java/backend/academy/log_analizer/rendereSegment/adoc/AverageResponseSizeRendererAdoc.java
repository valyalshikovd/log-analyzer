package backend.academy.log_analizer.rendereSegment.adoc;

import backend.academy.log_analizer.rendereSegment.RendererType;
import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;


public class AverageResponseSizeRendererAdoc extends BaseRendererSegment {

    public AverageResponseSizeRendererAdoc(String id) {
        super(id, RendererType.METRIC);
    }

    @Override
    public String render(String data) {
        return "| Средний размер ответа |"+data+"b ";
    }

}
