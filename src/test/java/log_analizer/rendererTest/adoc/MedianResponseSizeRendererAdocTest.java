package log_analizer.rendererTest.adoc;

import backend.academy.log_analizer.rendereSegment.adoc.MedianResponseSizeRendererAdoc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedianResponseSizeRendererAdocTest {

    @Test
    void render() {

        MedianResponseSizeRendererAdoc countRendererSegment = new MedianResponseSizeRendererAdoc("s");
        assertEquals("| Медианный размер ответа |300b ", countRendererSegment.render("300"));

    }
}
