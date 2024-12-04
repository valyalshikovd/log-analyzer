package backend.academy.log_analizer.rendereSegment.adoc;

import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;
import backend.academy.log_analizer.rendereSegment.RendererType;

public class FrequentStatusRendererSegmentAdoc extends BaseRendererSegment {

    public FrequentStatusRendererSegmentAdoc(String id) {
        super(id, RendererType.TABLE);
    }

    @Override
    public String render(String data) {

        StringBuilder sb = new StringBuilder();

        String[] dataArr = data.split("\n");

        sb.append("== Наиболее частые коды ответов\n");
        sb.append(
            """
                |===
                |      Код ответа       |   Количество |
                """);

        for (String line : dataArr) {
            String[] lineArr = line.split(":");

            if (lineArr.length < 2) {
                sb.append('-');
                break;
            }

            sb.append('|').append(lineArr[0]).append('|').append(lineArr[1]).append(" |");
            sb.append('\n');
        }
        sb.append("|===\n");
        return sb.toString();
    }

}
