package backend.academy.log_analizer.filter;


import backend.academy.log_analizer.LogString;

public interface LogFilter {

    boolean filter(LogString logString);

}
