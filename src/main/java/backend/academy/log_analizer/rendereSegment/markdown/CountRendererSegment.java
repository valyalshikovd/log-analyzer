package backend.academy.log_analizer.rendereSegment.markdown;


import backend.academy.log_analizer.rendereSegment.RendererType;
import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;

public class CountRendererSegment extends BaseRendererSegment {

    public CountRendererSegment(String id) {
        super(id, RendererType.METRIC);
    }

    @Override
    public String render(String data) {
        return "|  Количество запросов  |       " + data + " |";
    }

}