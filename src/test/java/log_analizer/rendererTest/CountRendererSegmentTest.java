package log_analizer.rendererTest;

import backend.academy.log_analizer.statisticCollector.rendereSegment.CountRendererSegment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CountRendererSegmentTest {

    @Test
    void render() {

        CountRendererSegment countRendererSegment = new CountRendererSegment("s");
        assertEquals("|  Количество запросов  |       300 |", countRendererSegment.render("300"));

    }
}
