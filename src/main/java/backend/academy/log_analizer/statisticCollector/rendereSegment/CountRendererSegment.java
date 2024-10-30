package backend.academy.log_analizer.statisticCollector.rendereSegment;

import backend.academy.log_analizer.RendererType;
import backend.academy.log_analizer.statisticCollector.RenderSegment;

public class CountRendererSegment implements RenderSegment {

    private final String id;
    private final RendererType type = RendererType.METRIC;

    public CountRendererSegment(String id) {
        this.id = id;
    }

    @Override
    public String render(String data) {
        return "|  Количество запросов  |       " + data + " |";
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
