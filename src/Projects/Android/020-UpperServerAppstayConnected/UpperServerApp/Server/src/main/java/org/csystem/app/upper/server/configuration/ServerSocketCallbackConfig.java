package org.csystem.app.upper.server.configuration;

import com.karandev.net.ip.tcp.server.functional.IRunnable;
import org.csystem.util.console.Console;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ServerSocketCallbackConfig {
    @Bean
    public IRunnable acceptClientRunnable()
    {
        return () -> Console.writeLine("Waiting for a client");
    }

    @Bean
    public Consumer<Throwable> serverSocketExceptionConsumer()
    {
        return ex -> Console.Error.writeLine("Exception occurs:%s", ex.getMessage());
    }
}
