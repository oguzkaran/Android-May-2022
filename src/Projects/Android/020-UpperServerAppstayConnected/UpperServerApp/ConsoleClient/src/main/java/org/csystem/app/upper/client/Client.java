package org.csystem.app.upper.client;

import org.csystem.util.console.Console;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;

@Component
public class Client {
    private final ApplicationContext m_context;

    private final ExecutorService m_threadPool;

    private void sendAndReceive()
    {
        try (var socket = m_context.getBean(Socket.class)) {
            var br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            var bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            while (true) {
                var text = Console.read("Text:");

                bw.write(text + "\r\n");
                bw.flush();

                if ("quit".equals(text))
                    break;

                Console.writeLine("Upper:%s", br.readLine().strip());
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO Exception occurs:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Exception occurs:%s", ex.getMessage());
        }
    }


    public Client(ApplicationContext context, ExecutorService threadPool)
    {
        m_context = context;
        m_threadPool = threadPool;
    }

    public void run()
    {
        sendAndReceive();
            
        m_threadPool.shutdown();
    }
}
