package backend.academy.log_analizer.rendereSegment;

public interface RenderSegment {

    String render(String data);

    String getId();

    RendererType getType();
}
