package backend.academy.log_analizer.rendereSegment;


import java.util.Map;

public interface Renderer {

    String render(Map<String, String> data);

    void addRenderSegment(RenderSegment renderSegment);

}
