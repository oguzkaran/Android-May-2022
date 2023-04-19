package org.csystem.app.upper.server;

import org.csystem.util.console.Console;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;

@Component
public class Server {
    private final ExecutorService m_threadPool;
    private final ServerSocket m_serverSocket;

    @Value("${socket.client.timeout}")
    private int m_clientSocketTimeout;

    private void handleClient(Socket socket)
    {
        try (socket) {
            socket.setSoTimeout(m_clientSocketTimeout);
            Console.writeLine("Client connected: %s:%d Local Port: %d", socket.getInetAddress().getHostAddress(), socket.getPort(), socket.getLocalPort());
            var br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            var bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            var text = br.readLine().strip();
            var upper = text.toUpperCase(); //Burada yapılan işlem sadece göstermek içindir

            bw.write(upper + "\r\n");
            bw.flush();
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
