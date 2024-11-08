package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"LambdaParameterName", "IllegalIdentifierName"})
public class AverageResponseSizeCollector extends AbstractCollector {

    private int count = 0;
    private long amount = 0;
    private final String id;

    public AverageResponseSizeCollector(String id) {
        this.id = id;
    }

    @Override public String toString() {
        return id;
    }

    @Override
    public Supplier<Object> supplier() {
        return () -> {
            return null;
        };
    }

    @Override
    public BiConsumer<Object, LogString> accumulator() {
        return (_, logString) -> {
            count++;
            amount += logString.bodyBytesSent();
        };
    }

    @Override
    public Function<Object, String> finisher() {
        final int ROUNDED_VAL1 = 100;
        final double ROUNDED_VAL2 = 0.01;

        return (_) -> {
            return Math.round((ROUNDED_VAL1 * (double) amount / count)) * ROUNDED_VAL2 + "";
        };
    }

}
