package log_analizer.processingConveyor;

import backend.academy.log_analizer.fileWriter.FileWriter;

public class MockWriter implements FileWriter {
    private String content;
    @Override
    public void writeFile(String filename, String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
