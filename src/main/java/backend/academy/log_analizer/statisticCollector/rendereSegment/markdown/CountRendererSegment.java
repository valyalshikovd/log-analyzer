package backend.academy.log_analizer.statisticCollector.rendereSegment.markdown;


import backend.academy.log_analizer.statisticCollector.rendereSegment.BaseRendererSegment;

public class CountRendererSegment extends BaseRendererSegment {

    public CountRendererSegment(String id) {
        super(id);
    }

    @Override
    public String render(String data) {
        return "|  Количество запросов  |       " + data + " |";
    }

}
