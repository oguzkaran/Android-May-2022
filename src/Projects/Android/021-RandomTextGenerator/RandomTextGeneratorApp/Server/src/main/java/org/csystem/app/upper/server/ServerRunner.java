package org.csystem.app.upper.server;

import com.karandev.net.ip.tcp.server.Server;
import com.karandev.util.console.Console;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerRunner implements ApplicationRunner {
    private final Server m_server;

    public ServerRunner(Server server)
    {
        m_server = server;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        Console.writeLine("Random Text Generator Server Started!...");
        m_server.run();
    }
}
