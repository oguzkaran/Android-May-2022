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

import java.io.Closeable;
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

    private void serverThreadCallback()
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

    private ConcurrentServer(int port, int backlog, InetAddress bindAddr) throws IOException
    {
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

    public ConcurrentServer setAcceptClientRunnable(IRunnable acceptClientRunnable)
    {
        m_acceptClientRunnable = acceptClientRunnable;

        return this;
    }

    public ConcurrentServer setServerSocketExceptionConsumer(Consumer<Throwable> serverSocketExceptionConsumer)
    {
        m_serverSocketExceptionConsumer = serverSocketExceptionConsumer;

        return this;
    }

    public ConcurrentServer setClientSocketConsumer(IConsumer<Socket> clientSocketConsumer)
    {
        m_clientSocketConsumer = clientSocketConsumer;

        return this;
    }

    public ConcurrentServer setClientIOExceptionConsumer(Consumer<IOException> clientIOExceptionConsumer)
    {
        m_clientIOExceptionConsumer = clientIOExceptionConsumer;

        return this;
    }

    public ConcurrentServer setClientExceptionConsumer(Consumer<Throwable> clientExceptionConsumer)
    {
        m_clientExceptionConsumer = clientExceptionConsumer;

        return this;
    }

    public ConcurrentServer run()
    {
        m_threadPool.execute(this::serverThreadCallback);

        return this;
    }

    @Override
    public void close() throws IOException
    {
        m_serverSocket.close();
        m_threadPool.shutdown();
    }
}
