package log_analizer.rendererTest.adoc;

import backend.academy.log_analizer.rendereSegment.adoc.DecorAdoc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecorAdocTest {

    @Test
    void renderHeader() {
        assertEquals("|===\n" +
            "|        Метрика        |     Значение\n" +
            "|Файл:|file\n" +
            "|Дата начала:|someting date\n" +
            "|Дата конца:|someting date\n", new DecorAdoc("file", "someting date", "someting date").getHeader());
    }


    @Test
    void renderFooter() {
        assertEquals("|===\n", new DecorAdoc("file", "someting date", "someting date").getFooter());
    }
}
