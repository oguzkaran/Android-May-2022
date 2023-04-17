package org.csystem.app.upper.server;

import org.csystem.util.console.Console;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Component
public class Server {
    private final ExecutorService m_threadPool;
    private final ServerSocket m_serverSocket;

    private void handleClient(Socket socket)
    {
        try (socket) {
            Console.writeLine("Client connected: %s:%d Local Port: %d", socket.getInetAddress().getHostAddress(), socket.getPort(), socket.getLocalPort());
            //...
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO Exception occurs while transferring data:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Exception occurs:%s", ex.getMessage());
        }
    }

    private void serverThreadCallback()
    {
        try {
            while (true) {
                Console.writeLine("Waiting for a client");
                var clientSocket = m_serverSocket.accept();

                m_threadPool.execute(() -> handleClient(clientSocket));
            }
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO Exception occurs:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Exception occurs:%s", ex.getMessage());
        }
    }

    public Server(ExecutorService threadPool, ServerSocket serverSocket)
    {
        m_threadPool = threadPool;
        m_serverSocket = serverSocket;
    }

    public void run()
    {
        m_threadPool.execute(this::serverThreadCallback);
    }
}
