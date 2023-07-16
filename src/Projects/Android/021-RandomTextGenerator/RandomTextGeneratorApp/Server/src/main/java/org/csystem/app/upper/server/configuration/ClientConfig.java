package org.csystem.app.upper.server.configuration;

import com.karandev.net.ip.tcp.server.functional.IConsumer;
import com.karandev.util.console.Console;
import com.karandev.util.net.TcpUtil;
import org.csystem.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.function.Consumer;

@Configuration
public class ClientConfig {
    @Value("${socket.client.timeout}")
    private int m_clientSocketTimeout;

    @Value("${server.text.count.max}")
    private int m_maxCount;

    @Value("${server.text.random.bound.max}")
    private int m_maxBound;

    private void clientSocketConsumerCallback(Socket socket) throws IOException
    {
        socket.setSoTimeout(3000);
        Console.writeLine("Client connected: %s:%d Local Port: %d", socket.getInetAddress().getHostAddress(), socket.getPort(), socket.getLocalPort());

        var count = TcpUtil.receiveInt(socket);
        Console.writeLine("Count:%d", count);

        if (count > m_maxCount || count < 1) {
            TcpUtil.sendInt(socket, 0);
            TcpUtil.sendInt(socket, m_maxCount);
            return;
        }

        TcpUtil.sendInt(socket, 1);

        var min = TcpUtil.receiveInt(socket);
        var bound = TcpUtil.receiveInt(socket);

        if (bound > m_maxBound || min >= bound || min < 1) {
            TcpUtil.sendInt(socket, 0);
            TcpUtil.sendInt(socket, 1);
            TcpUtil.sendInt(socket, m_maxBound);
            return;
        }

        TcpUtil.sendInt(socket, 1);
        var random = new Random();

        for (int i = 0; i < count; ++i) {
            var text = StringUtil.getRandomTextTR(random, random.nextInt(min, bound));
            TcpUtil.sendStringViaLength(socket, text);
        }
    }

    @Bean
    public Consumer<IOException> clientIOExceptionConsumer()
    {
        return ex -> Console.Error.writeLine("IO Exception occurs while transferring data:%s", ex.getMessage());
    }

    @Bean
    public Consumer<Throwable> clientExceptionConsumer()
    {
        return ex -> Console.Error.writeLine("Exception occurs:%s", ex.getMessage());
    }

    @Bean
    public IConsumer<Socket> clientSocketConsumer()
    {
        return this::clientSocketConsumerCallback;
    }
}
