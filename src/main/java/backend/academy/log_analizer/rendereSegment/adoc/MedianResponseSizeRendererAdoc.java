package backend.academy.log_analizer.rendereSegment.adoc;

import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;
import backend.academy.log_analizer.rendereSegment.RendererType;


public class MedianResponseSizeRendererAdoc extends BaseRendererSegment {

    public MedianResponseSizeRendererAdoc(String id) {
        super(id, RendererType.METRIC);
    }

    @Override
    public String render(String data) {
        return "| Медианный размер ответа |"+data+"b ";
    }

}
