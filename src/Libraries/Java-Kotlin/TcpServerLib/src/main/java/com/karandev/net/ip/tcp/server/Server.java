/*----------------------------------------------------------------------
	FILE        : Server.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 05.05.2023

	Base class of server

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
    private IRunnable m_acceptClientRunnable;
    private Consumer<Throwable> m_serverSocketExceptionConsumer;
    private IConsumer<Socket> m_clientSocketConsumer;
    private Consumer<IOException> m_clientIOExceptionConsumer;
    private Consumer<Throwable> m_clientExceptionConsumer;

    protected final ExecutorService threadPool;
    protected final ServerSocket serverSocket;

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

                var clientSocket = serverSocket.accept();

                threadPool.execute(() -> handleClient(clientSocket));
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

                var clientSocket = serverSocket.accept();

                handleClient(clientSocket);
            }
        }
        catch (Throwable ex) {
            m_serverSocketExceptionConsumer.accept(ex);
        }
    }

    protected Server(int port, int backlog, InetAddress bindAddr) throws IOException
    {
        threadPool = Executors.newCachedThreadPool();
        serverSocket = new ServerSocket(port, backlog, bindAddr);
    }

    public static Server of(int port) throws IOException
    {
        return of(port, 50);
    }

    public static Server of(int port, int backlog) throws IOException
    {
        return of(port, backlog, null);
    }

    public static Server of(int port, int backlog, InetAddress bindAddr) throws IOException
    {
        return new Server(port, backlog, bindAddr);
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

    public Server setClientSocketConsumer(IConsumer<Socket> clientSocketConsumer)
    {
        m_clientSocketConsumer = clientSocketConsumer;

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
        threadPool.execute(this::concurrentServerThreadCallback);

        return this;
    }

    @Override
    public void close() throws IOException
    {
        serverSocket.close();
        threadPool.shutdown();
    }
}
