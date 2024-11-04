package backend.academy.log_analizer.rendereSegment.adoc;

import backend.academy.log_analizer.rendereSegment.RenderSegment;
import backend.academy.log_analizer.rendereSegment.RendererType;

public class PercentileRendererAdoc implements RenderSegment {

    private final String id;
    private final RendererType type = RendererType.METRIC;

    public PercentileRendererAdoc(String id) {
        this.id = id;
    }

    @Override
    public String render(String data) {
        return "|  95p размера ответа |" + data + "b ";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public RendererType getType() {
        return type;
    }
}
