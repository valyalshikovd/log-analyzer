package log_analizer.rendererTest;

import backend.academy.log_analizer.rendereSegment.markdown.DecorMarkdown;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecorMarkdownTest {
    @Test
    void renderHeader() {
        assertEquals("|        Метрика        |     Значение |\n" +
            "|:---------------------:|-------------:|\n", new DecorMarkdown().getHeader());
    }


    @Test
    void renderFooter() {
        assertEquals("\n", new DecorMarkdown().getFooter());
    }
}
