package backend.academy.log_analizer.statisticCollector;

import java.util.Map;

public interface Renderer {

    String render(Map<String, String> data);

    void addRenderSegment(RenderSegment renderSegment);
}
