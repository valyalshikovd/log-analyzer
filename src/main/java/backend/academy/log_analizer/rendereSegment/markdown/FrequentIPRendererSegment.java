package backend.academy.log_analizer.rendereSegment.markdown;

import backend.academy.log_analizer.rendereSegment.BaseRendererSegment;
import backend.academy.log_analizer.rendereSegment.RendererType;

public class FrequentIPRendererSegment extends BaseRendererSegment {

    public FrequentIPRendererSegment(String id) {
        super(id, RendererType.TABLE);
    }

    @Override
    public String render(String data) {

        StringBuilder sb = new StringBuilder();

        String[] dataArr = data.split("\n");
        sb.append("## Наиболее частые клиенты:\n");

        sb.append(
            """
                |        IP клиента         |   Количество |
                |:---------------------:|-------------:|
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

        return sb.toString();
    }

}
