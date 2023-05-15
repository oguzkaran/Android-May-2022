package org.csystem.app.upper.client.runner;

import org.csystem.app.upper.client.Client;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class ClientRunner implements ApplicationRunner {
    private final ExecutorService m_threadPool;
    private final Client m_client;

    public ClientRunner(ExecutorService threadPool, Client client)
    {
        m_threadPool = threadPool;
        m_client = client;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_threadPool.execute(m_client::run);
    }
}
