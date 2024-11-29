package backend.academy.log_analizer.rendereSegment.adoc;

import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;
import backend.academy.log_analizer.rendereSegment.RendererType;

public class FrequentIPRendererSegmentAdoc extends BaseRendererSegment {

    public FrequentIPRendererSegmentAdoc(String id) {
        super(id, RendererType.TABLE);
    }

    @Override
    public String render(String data) {

        StringBuilder sb = new StringBuilder();

        String[] dataArr = data.split("\n");
        sb.append("== Наиболее частые клиенты\n");

        try {
            sb.append(
                """
                    |===
                    |        IP клиента         |   Количество |
                    """);

            for (String line : dataArr) {
                String[] lineArr = line.split(":");
                sb.append('|').append(lineArr[0]).append('|').append(lineArr[1]).append(" |");
                sb.append('\n');
            }
            sb.append("|===\n");

        } catch (Exception e) {
            sb.append('-');
        }
        return sb.toString();
    }

}
