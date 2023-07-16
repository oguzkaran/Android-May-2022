package org.csystem.app.upper.client;

import com.karandev.util.console.Console;
import com.karandev.util.net.TcpUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
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
                    Console.writeLine("Maximum value of count is %d", TcpUtil.receiveInt(socket));
                    continue;
                }

                TcpUtil.sendInt(socket, min);
                TcpUtil.sendInt(socket, bound);
                if (TcpUtil.receiveInt(socket) == 0) {
                    Console.writeLine("Invalid min/bound value");
                    Console.writeLine("Minimum value is %d", TcpUtil.receiveInt(socket));
                    Console.writeLine("Maximum value of bound is %d", TcpUtil.receiveInt(socket));
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
