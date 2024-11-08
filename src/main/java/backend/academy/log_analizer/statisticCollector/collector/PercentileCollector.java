package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PercentileCollector extends AbstractCollector {
    private int count = 0;
    private final String id;
    private static final double PERCENT = 0.95;

    public PercentileCollector(String id) {
        this.id = id;
    }

    @Override public String toString() {
        return id;
    }

    @Override
    public Supplier<Object> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<Object, LogString> accumulator() {
        return (list, logString) -> {
            List<Integer> listDownCasted = (List<Integer>) list;
            count += 1;
            listDownCasted.add(logString.bodyBytesSent());
        };
    }

    @Override
    public Function<Object, String> finisher() {
        return (list) -> {
            List<Integer> listDownCasted = (List<Integer>) list;
            try {
                Collections.sort(listDownCasted);
                return listDownCasted.get((int) Math.floor((count) * PERCENT)) + "";
            } catch (Exception e) {
                return "-";
            }
        };
    }
}
