package backend.academy;

import backend.academy.log_analizer.cmdReader.Reader;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {

        new Reader(args).start();

    }
}
