package backend.academy.log_analizer.rendereSegment.markdown;

import backend.academy.log_analizer.rendereSegment.RendererType;
import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;

public class FrequentStatusRendererSegment extends BaseRendererSegment {

    public FrequentStatusRendererSegment(String id) {
        super(id, RendererType.TABLE);
    }

    @Override
    public String render(String data) {

        StringBuilder sb = new StringBuilder();

        String[] dataArr = data.split("\n");

        sb.append("## Наиболее частые коды ответов\n");
        sb.append(
            """
                |      Код ответа       |   Количество |
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