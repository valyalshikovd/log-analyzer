package backend.academy.log_analizer.statisticCollector.collector;

import backend.academy.log_analizer.LogString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MedianSizeCollector extends AbstractCollector {
    private final String id;

    public MedianSizeCollector(String id) {
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
            listDownCasted.add(logString.bodyBytesSent());
        };
    }

    @Override
    public Function<Object, String> finisher() {
        return (list) -> {
            List<Integer> listDownCasted = (List<Integer>) list;
            Collections.sort(listDownCasted);
            try {
                return listDownCasted.get(listDownCasted.size() / 2) + "";
            } catch (Exception e) {
                return "-";
            }
        };
    }
}
