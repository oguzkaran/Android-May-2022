package org.csystem.app.upper.client;

import com.karandev.util.net.TcpUtil;
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
        while (true) {
            var count = Console.readInt("Input count:");

            if (count == 0)
                break;

            var min = Console.readInt("Input min:");
            var bound = Console.readInt("Input bound:");

            try (var socket = m_context.getBean(Socket.class)) {
                TcpUtil.sendInt(socket, count);
                if (TcpUtil.receiveInt(socket) == 0) {
                    Console.writeLine("Invalid count");
                    continue;
                }

                TcpUtil.sendInt(socket, min);
                TcpUtil.sendInt(socket, bound);
                if (TcpUtil.receiveInt(socket) == 0) {
                    Console.writeLine("Invalid min/bound value");
                    continue;
                }
                Console.writeLine("Reading texts");
                for (int i = 0; i < count; ++i)
                    Console.writeLine(TcpUtil.receiveStringViaLength(socket));
            }

            catch (IOException ex) {
                Console.Error.writeLine("IO Exception occurs:%s", ex.getMessage());
            }
            catch (Throwable ex) {
                Console.Error.writeLine("Exception occurs:%s", ex.getMessage());
            }
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
