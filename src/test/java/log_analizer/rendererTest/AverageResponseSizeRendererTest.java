package log_analizer.rendererTest;


import backend.academy.log_analizer.statisticCollector.rendereSegment.markdown.AverageResponseSizeRenderer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageResponseSizeRendererTest {

    @Test
    void render() {

        AverageResponseSizeRenderer r = new AverageResponseSizeRenderer("s");
        assertEquals("| Средний размер ответа |254.54b |", r.render("254.54"));

    }
}
