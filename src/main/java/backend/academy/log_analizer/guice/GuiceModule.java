package backend.academy.log_analizer.guice;

import backend.academy.log_analizer.parser.LogStringParser;
import backend.academy.log_analizer.parser.LogStringParserImpl;
import backend.academy.log_analizer.parser.ZoneDateParserImpl;
import backend.academy.log_analizer.parser.ZoneDateTimeParser;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;


public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LogStringParser.class).to(LogStringParserImpl.class).in(Scopes.SINGLETON);
        bind(ZoneDateTimeParser.class).to(ZoneDateParserImpl.class).in(Scopes.SINGLETON);
    }
}
