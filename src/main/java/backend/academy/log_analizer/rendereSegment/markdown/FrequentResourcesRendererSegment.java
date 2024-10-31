package backend.academy.log_analizer.rendereSegment.markdown;


import backend.academy.log_analizer.rendereSegment.RendererType;
import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;

public class FrequentResourcesRendererSegment extends BaseRendererSegment {

    public FrequentResourcesRendererSegment(String id) {
        super(id, RendererType.TABLE);
    }

    @Override
    public String render(String data) {

        StringBuilder sb = new StringBuilder();

        String[] dataArr = data.split("\n");
        sb.append("## Наиболее частозапрашиваемые ресурсы\n");

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

}
