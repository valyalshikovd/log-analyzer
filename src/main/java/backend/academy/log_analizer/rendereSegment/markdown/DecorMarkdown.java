package backend.academy.log_analizer.rendereSegment.markdown;

import backend.academy.log_analizer.rendereSegment.Decor;

public class DecorMarkdown implements Decor {

    private final String fileName;
    private final String startDate;
    private final String endDate;

    public DecorMarkdown(String fileName, String startDate, String endDate) {
        this.fileName = (fileName == null) ? "-" : fileName;
        this.startDate = (startDate == null) ? "-" : startDate;
        this.endDate = (endDate == null) ? "-" : endDate;
    }

    @Override
    public String getHeader() {

        String endingChar = "|\n";
        String fileNameString = "|Файл:|" + fileName + endingChar;
        String startDateString = "|Дата начала:|" + startDate + endingChar;
        String endDateString = "|Дата конца:|" + endDate + endingChar;

        return """
                |        Метрика        |     Значение |
                |:---------------------:|-------------:|
                """ + fileNameString + startDateString + endDateString;
    }

    @Override
    public String getTitle() {
        return "## Общая информация  \n";
    }

    @Override
    public String getFooter() {
        return "\n";
    }
}
