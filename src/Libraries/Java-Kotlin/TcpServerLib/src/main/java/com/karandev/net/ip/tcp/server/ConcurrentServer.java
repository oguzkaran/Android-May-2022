/*----------------------------------------------------------------------
	FILE        : Server.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 24.04.2023

	Multi-client (concurrent) server class that support fluent pattern

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package com.karandev.net.ip.tcp.server;

import com.karandev.net.ip.tcp.server.functional.IConsumer;
import com.karandev.net.ip.tcp.server.functional.IRunnable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class ConcurrentServer extends Server {
    private final ExecutorService m_threadPool;
    private final ServerSocket m_serverSocket;
    private IRunnable m_acceptClientRunnable;
    private Consumer<Throwable> m_serverSocketExceptionConsumer;
    private IConsumer<Socket> m_clientSocketConsumer;
    private Consumer<IOException> m_clientIOExceptionConsumer;
    private Consumer<Throwable> m_clientExceptionConsumer;

    private void handleClient(Socket socket)
    {
        try (socket) {
            m_clientSocketConsumer.accept(socket);
        }
        catch (IOException ex) {
            m_clientIOExceptionConsumer.accept(ex);
        }
        catch (Throwable ex) {
            m_clientExceptionConsumer.accept(ex);
        }
    }

    protected void concurrentServerThreadCallback()
    {
        try {
            while (true) {
                if (m_acceptClientRunnable != null)
                    m_acceptClientRunnable.run();

                var clientSocket = m_serverSocket.accept();

                m_threadPool.execute(() -> handleClient(clientSocket));
            }
        }
        catch (Throwable ex) {
            m_serverSocketExceptionConsumer.accept(ex);
        }
    }

    protected void iterativeServerThreadCallback()
    {
        try {
            while (true) {
                if (m_acceptClientRunnable != null)
                    m_acceptClientRunnable.run();

                var clientSocket = m_serverSocket.accept();

                handleClient(clientSocket);
            }
        }
        catch (Throwable ex) {
            m_serverSocketExceptionConsumer.accept(ex);
        }
    }

    private ConcurrentServer(int port, int backlog, InetAddress bindAddr) throws IOException
    {
        super(port, backlog, bindAddr);
        m_threadPool = Executors.newCachedThreadPool();
        m_serverSocket = new ServerSocket(port, backlog, bindAddr);
    }

    public static ConcurrentServer of(int port) throws IOException
    {
        return of(port, 50);
    }

    public static ConcurrentServer of(int port, int backlog) throws IOException
    {
        return of(port, backlog, null);
    }

    public static ConcurrentServer of(int port, int backlog, InetAddress bindAddr) throws IOException
    {
        return new ConcurrentServer(port, backlog, bindAddr);
    }
}
