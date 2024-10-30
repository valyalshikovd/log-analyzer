package backend.academy.log_analizer.statisticCollector;

import backend.academy.log_analizer.RendererType;

public interface RenderSegment {

    String render(String data);

    String getId();

    RendererType getType();
}
