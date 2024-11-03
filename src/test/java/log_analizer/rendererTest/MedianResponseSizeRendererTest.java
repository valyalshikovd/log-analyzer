package log_analizer.rendererTest;

import backend.academy.log_analizer.rendereSegment.adoc.MedianResponseSizeRendererAdoc;
import backend.academy.log_analizer.rendereSegment.markdown.MedianResponseSizeRenderer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianResponseSizeRendererTest {

    @Test
    void render() {

        MedianResponseSizeRenderer countRendererSegment = new MedianResponseSizeRenderer("s");
        assertEquals("| Медианный размер ответа |300b |", countRendererSegment.render("300"));

    }
}
