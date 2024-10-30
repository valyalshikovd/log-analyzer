package log_analizer.rendererTest;

import backend.academy.log_analizer.statisticCollector.rendereSegment.PercentileRenderer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PercentileRendererTest {


    @Test
    public void percentileRendererTest() {

        PercentileRenderer percentileRenderer = new PercentileRenderer("s");

        assertEquals("|  95p размера ответа |412.32b |", percentileRenderer.render("412.32"));
    }

}
