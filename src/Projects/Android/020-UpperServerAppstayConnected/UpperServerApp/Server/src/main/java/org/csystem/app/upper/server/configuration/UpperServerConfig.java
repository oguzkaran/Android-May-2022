package org.csystem.app.upper.server.configuration;

import com.karandev.net.ip.tcp.server.Server;
import com.karandev.net.ip.tcp.server.functional.IConsumer;
import com.karandev.net.ip.tcp.server.functional.IRunnable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

@Configuration
public class UpperServerConfig {
    private final IRunnable m_serverSocketRunnable;

    private final Consumer<Throwable> m_serverSocketExceptionConsumer;

    private final Consumer<IOException> m_clientIOExceptionConsumer;

    private final Consumer<Throwable> m_clientExceptionConsumer;

    private final IConsumer<Socket> m_clientSocketConsumer;

    public UpperServerConfig(IRunnable serverSocketRunnable,
                             @Qualifier("serverSocketExceptionConsumer") Consumer<Throwable> serverSocketExceptionConsumer,
                             Consumer<IOException> clientIOExceptionConsumer,
                             @Qualifier("clientExceptionConsumer") Consumer<Throwable> clientExceptionConsumer,
                             IConsumer<Socket> clientSocketConsumer)
    {
        m_serverSocketRunnable = serverSocketRunnable;
        m_serverSocketExceptionConsumer = serverSocketExceptionConsumer;
        m_clientIOExceptionConsumer = clientIOExceptionConsumer;
        m_clientExceptionConsumer = clientExceptionConsumer;
        m_clientSocketConsumer = clientSocketConsumer;
    }

    @Bean
    public Server server(@Value("${server.port}") int port, @Value("${server.backlog:512}") int backlog)
            throws IOException
    {
        return Server.of(port, backlog)
                .setAcceptClientRunnable(m_serverSocketRunnable)
                .setServerSocketExceptionConsumer(m_serverSocketExceptionConsumer)
                .setClientIOExceptionConsumer(m_clientIOExceptionConsumer)
                .setClientExceptionConsumer(m_clientExceptionConsumer)
                .setClientSocketConsumer(m_clientSocketConsumer);
    }
}
