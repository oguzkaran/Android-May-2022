package org.csystem.app.upper.server.configuration.socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class ServerSocketConfig {
    @Bean
    public ServerSocket serverSocket(@Value("${server.port}") int port, @Value("${server.backlog:512}") int backlog)
            throws IOException
    {
        return new ServerSocket(port, backlog);
    }
}
