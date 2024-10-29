package backend.academy.log_analizer.statisticCollector;

import backend.academy.log_analizer.LogString;

public interface StatisticCollector {

    void collectStatistics(LogString logString);

    String getStatistics();
}
