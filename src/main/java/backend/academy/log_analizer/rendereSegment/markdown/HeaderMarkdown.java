package backend.academy.log_analizer.rendereSegment.markdown;

import backend.academy.log_analizer.rendereSegment.Header;

public class HeaderMarkdown implements Header {
    @Override
    public String getHeader() {
        return """
                |        Метрика        |     Значение |
                |:---------------------:|-------------:|
                """;
    }

    @Override
    public String getTitle() {
        return "## Общая информация  \n";
    }

    @Override
    public String getFooter() {
        return "\n";
    }
}
