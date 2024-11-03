package log_analizer.rendererTest.adoc;

import backend.academy.log_analizer.rendereSegment.RenderSegment;
import backend.academy.log_analizer.rendereSegment.adoc.AverageResponseSizeRendererAdoc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageResponseSizeRendererAdocTest {

    @Test
    void render(){
        RenderSegment r = new AverageResponseSizeRendererAdoc("r");
        assertEquals("| Средний размер ответа |32.4b ", r.render("32.4"));
    }

}
