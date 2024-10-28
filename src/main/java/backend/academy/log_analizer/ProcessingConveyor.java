package backend.academy.log_analizer;

import jakarta.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ProcessingConveyor {


    private final LogStringParser logStringParser;

    @Inject
    public ProcessingConveyor(LogStringParser logStringParser) {
        this.logStringParser = logStringParser;
    }

    public void process(String pathString) {

        Path path = Paths.get(pathString);

        try (Stream<String> lines = Files.lines(path)) {
            lines.map(logStringParser::parseLogString).forEach(System.out::println);
        } catch (IOException e) {
            return;
        }

    }

}
