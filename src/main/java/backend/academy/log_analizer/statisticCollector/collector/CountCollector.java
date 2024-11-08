package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"LambdaParameterName", "IllegalIdentifierName"})
public class CountCollector extends AbstractCollector {

    private int count = 0;
    private final String id;

    public CountCollector(String id) {
        this.id = id;
    }


    @Override public String toString() {
        return id;
    }

    @Override
    public Supplier<Object> supplier() {
        return () -> null;
    }

    @Override
    public BiConsumer<Object, LogString> accumulator() {
        return (_, _) -> {
            count++;
        };
    }

    @Override
    public Function<Object, String> finisher() {
        return (_) -> {
            return count + "";
        };
    }

}
