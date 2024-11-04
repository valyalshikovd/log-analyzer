package backend.academy.log_analizer.fileWriter;

import backend.academy.log_analizer.exception.FailToWriteException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressFBWarnings("LEST_LOST_EXCEPTION_STACK_TRACE")
public class FileWriterDefault implements FileWriter {

    /**
     * fileWriter по умолчанию
     */
    public void writeFile(String filename, String content) {
        try (OutputStream outputStream = Files.newOutputStream(Path.of(filename))) {
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new FailToWriteException(e.getMessage());
        }
    }

}
