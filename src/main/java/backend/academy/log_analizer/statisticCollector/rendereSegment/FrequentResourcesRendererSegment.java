package backend.academy.log_analizer.statisticCollector.rendereSegment;

import backend.academy.log_analizer.RendererType;
import backend.academy.log_analizer.statisticCollector.RenderSegment;

public class FrequentResourcesRendererSegment implements RenderSegment {

    private final String id;
    private final RendererType type = RendererType.TABLE;

    public FrequentResourcesRendererSegment(String id) {
        this.id = id;
    }

    @Override
    public String render(String data) {

        StringBuilder sb = new StringBuilder();

        String[] dataArr = data.split("\n");


        sb.append(
            """
                |        Ресурс         |   Количество |
                |:---------------------:|-------------:|
                """);

        for (String line : dataArr) {
            String[] lineArr = line.split(":");
            sb.append("|").append(lineArr[0]).append("|").append(lineArr[1]).append(" |");
            sb.append("\n");
        }

        return sb.toString();
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
