package log_analizer.rendererTest.adoc;

import backend.academy.log_analizer.rendereSegment.adoc.DecorAdoc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecorAdocTest {

    @Test
    void renderHeader() {
        assertEquals("|===\n" +
            "|        Метрика        |     Значение\n", new DecorAdoc().getHeader());
    }


    @Test
    void renderFooter() {
        assertEquals("|===\n", new DecorAdoc().getFooter());
    }
}
