package backend.academy.log_analizer.statisticCollector.rendereSegment.adoc;

import backend.academy.log_analizer.RendererType;
import backend.academy.log_analizer.statisticCollector.RenderSegment;

public class PercentileRendererAdoc implements RenderSegment {

    private final String id;
    private final RendererType type = RendererType.METRIC;

    public PercentileRendererAdoc(String id) {
        this.id = id;
    }

    @Override
    public String render(String data) {
        return "|  95p размера ответа |"+data+"b ";
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
