package log_analizer.rendererTest;

import backend.academy.log_analizer.rendereSegment.markdown.DecorMarkdown;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecorMarkdownTest {
    @Test
    void renderHeader() {
        assertEquals("|        Метрика        |     Значение |\n" +
            "|:---------------------:|-------------:|\n" +
            "|Файл:|file|\n" +
            "|Дата начала:|someting date|\n" +
            "|Дата конца:|someting date|\n", new DecorMarkdown("file", "someting date", "someting date").getHeader());
    }


    @Test
    void renderFooter() {
        assertEquals("\n", new DecorMarkdown("file", "someting date", "someting date").getFooter());
    }
}
