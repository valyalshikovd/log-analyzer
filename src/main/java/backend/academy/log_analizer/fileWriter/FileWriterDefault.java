package backend.academy.log_analizer.fileWriter;

import backend.academy.log_analizer.exception.FailToReadException;
import backend.academy.log_analizer.exception.FailToWriteException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileWriterDefault implements FileWriter {

    /**
     * fileWriter по умолчанию
     */
    public void writeFile(String filename, String content) {
        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            throw new FailToWriteException(e.getMessage());
        }
    }

}
