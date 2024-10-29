package backend.academy.log_analizer.guice;

import backend.academy.log_analizer.LogStringParser;
import backend.academy.log_analizer.LogStringParserImpl;
import backend.academy.log_analizer.ZoneDateParserImpl;
import backend.academy.log_analizer.ZoneDateTimeParser;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;


public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LogStringParser.class).to(LogStringParserImpl.class).in(Scopes.SINGLETON);
        bind(ZoneDateTimeParser.class).to(ZoneDateParserImpl.class).in(Scopes.SINGLETON);
    }
}
