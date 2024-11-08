package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;

public abstract class AbstractCollector implements Collector<LogString, Object, String>  {

    @Override
    public final Set<Characteristics> characteristics() {
        return Set.of();
    }

    @Override
    public final BinaryOperator<Object> combiner() {
        return null;
    }

}
