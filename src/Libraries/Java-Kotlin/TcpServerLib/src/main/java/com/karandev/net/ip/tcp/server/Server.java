/*----------------------------------------------------------------------
	FILE        : Server.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 23.04.2023

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

public class Server implements Closeable {
    private final ExecutorService m_threadPool;
    private final ServerSocket m_serverSocket;
    private IRunnable m_acceptClientRunnable;
    private Consumer<Throwable> m_serverSocketExceptionConsumer;
    private IConsumer<Socket> m_socketConsumer;
    private Consumer<IOException> m_clientIOExceptionConsumer;
    private Consumer<Throwable> m_clientExceptionConsumer;

    private void handleClient(Socket socket)
    {
        try (socket) {
            m_socketConsumer.accept(socket);
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

    public Server(int port) throws IOException
    {
        this(port, 50);
    }

    public Server(int port, int backlog) throws IOException
    {
        this(port, backlog, null);
    }

    public Server(int port, int backlog, InetAddress bindAddr) throws IOException
    {
        m_threadPool = Executors.newCachedThreadPool();
        m_serverSocket = new ServerSocket(port, backlog, bindAddr);
    }

    public Server setAcceptClientRunnable(IRunnable acceptClientRunnable)
    {
        m_acceptClientRunnable = acceptClientRunnable;

        return this;
    }

    public Server setServerSocketExceptionConsumer(Consumer<Throwable> serverSocketExceptionConsumer)
    {
        m_serverSocketExceptionConsumer = serverSocketExceptionConsumer;

        return this;
    }

    public Server setSocketConsumer(IConsumer<Socket> socketConsumer)
    {
        m_socketConsumer = socketConsumer;

        return this;
    }

    public Server setClientIOExceptionConsumer(Consumer<IOException> clientIOExceptionConsumer)
    {
        m_clientIOExceptionConsumer = clientIOExceptionConsumer;

        return this;
    }

    public Server setClientExceptionConsumer(Consumer<Throwable> clientExceptionConsumer)
    {
        m_clientExceptionConsumer = clientExceptionConsumer;

        return this;
    }

    public Server run()
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
