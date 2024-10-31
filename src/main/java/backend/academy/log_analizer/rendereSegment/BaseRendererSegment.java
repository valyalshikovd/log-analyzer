package backend.academy.log_analizer.rendereSegment;

public abstract class BaseRendererSegment implements RenderSegment {

    protected final String id;
    protected final RendererType type;

    public BaseRendererSegment(String id, RendererType type) {
        this.id = id;
        this.type = type;
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
