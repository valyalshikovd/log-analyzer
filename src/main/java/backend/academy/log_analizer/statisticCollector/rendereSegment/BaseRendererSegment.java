package backend.academy.log_analizer.statisticCollector.rendereSegment;

import backend.academy.log_analizer.RendererType;
import backend.academy.log_analizer.statisticCollector.RenderSegment;

public abstract class BaseRendererSegment implements RenderSegment {

    protected final String id;
    protected final RendererType type = RendererType.TABLE;

    public BaseRendererSegment(String id) {
        this.id = id;
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
