package log_analizer.rendererTest.adoc;

import backend.academy.log_analizer.rendereSegment.adoc.CountRendererSegmentAdoc;
import backend.academy.log_analizer.rendereSegment.markdown.CountRendererSegment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CountRendererSegmentAdocTest {

    @Test
    void render() {

        CountRendererSegmentAdoc countRendererSegment = new CountRendererSegmentAdoc("s");
        assertEquals("|  Количество запросов  |       300 ", countRendererSegment.render("300"));

    }
}
