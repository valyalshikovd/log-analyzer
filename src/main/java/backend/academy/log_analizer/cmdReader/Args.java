package backend.academy.log_analizer.cmdReader;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class Args {

    //analyzer --path logs/2024* --from 2024-08-31 --format markdown

    @Parameter(names = "--path", required = true)
    private String path;

    @Parameter(names = "--from")
    private String from;

    @Parameter(names = "--format", required = true)
    private String format;

    @Parameter(names = "--filter-field")
    private String filterField;

    @Parameter(names = "--filter-value")
    private String filterValue;

    @Parameter(names = "--to")
    private String to;
}
