package log_analizer.rendererTest.adoc;

import backend.academy.log_analizer.rendereSegment.adoc.CountRendererSegmentAdoc;
import backend.academy.log_analizer.rendereSegment.adoc.PercentileRendererAdoc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PercentileRendererAdocTest {
    @Test
    void render() {

        PercentileRendererAdoc countRendererSegment = new PercentileRendererAdoc("s");
        assertEquals("|  95p размера ответа |300b ", countRendererSegment.render("300"));

    }
}
