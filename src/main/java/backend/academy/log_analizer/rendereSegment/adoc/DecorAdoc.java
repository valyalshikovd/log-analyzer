package backend.academy.log_analizer.rendereSegment.adoc;

import backend.academy.log_analizer.rendereSegment.Decor;

public class DecorAdoc implements Decor {
    @Override
    public String getHeader() {
        return """
            |===
            |        Метрика        |     Значение
            """;
    }

    @Override
    public String getTitle() {
        return "== Общая информация  \n";
    }

    @Override
    public String getFooter() {
        return "|===\n";
    }
}