package backend.academy.log_analizer.cmdReader;

import backend.academy.log_analizer.ProcessingConveyor;
import backend.academy.log_analizer.parser.LogStringParserImpl;
import backend.academy.log_analizer.parser.ZoneDateParserImpl;
import com.beust.jcommander.JCommander;

public class Reader {

    private Args args;

    public Reader(String[] args) {

        Args analyzerArgs = new Args();
        JCommander jCommander = JCommander.newBuilder()
            .addObject(analyzerArgs)
            .build();
        try {
            jCommander.parse(args);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            jCommander.usage();
        }

    }

    public void start(){


        ProcessingConveyor p =  new ProcessingConveyor(new LogStringParserImpl(new ZoneDateParserImpl()));



        p.process(args.path());

    }

}
