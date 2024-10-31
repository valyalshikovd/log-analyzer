package backend.academy;

import backend.academy.log_analizer.parser.LogStringParserImpl;
import backend.academy.log_analizer.ProcessingConveyor;
import backend.academy.log_analizer.parser.ZoneDateParserImpl;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        new ProcessingConveyor(new LogStringParserImpl(new ZoneDateParserImpl())).process("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
    }
}
