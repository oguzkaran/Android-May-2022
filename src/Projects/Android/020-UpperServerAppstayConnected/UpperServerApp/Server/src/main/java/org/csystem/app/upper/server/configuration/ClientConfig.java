package org.csystem.app.upper.server.configuration;

import com.karandev.net.ip.tcp.server.functional.IConsumer;
import org.csystem.util.console.Console;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

@Configuration
public class ClientConfig {
    private void clientSocketConsumerCallback(Socket socket, int clientSocketTimeout) throws IOException
    {
        socket.setSoTimeout(clientSocketTimeout);
        Console.writeLine("Client connected: %s:%d Local Port: %d", socket.getInetAddress().getHostAddress(), socket.getPort(), socket.getLocalPort());
        var br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        var bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        while (true) {
            var text = br.readLine().strip();

            if ("quit".equals(text))
                break;

            var upper = text.toUpperCase(); //Burada yapılan işlem sadece göstermek içindir. Başka senaryolar eklenebilir

            bw.write(upper + "\r\n");
            bw.flush();
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
    public IConsumer<Socket> clientSocketConsumer(@Value("${socket.client.timeout}") int clientSocketTimeout)
    {
        return socket -> clientSocketConsumerCallback(socket, clientSocketTimeout);
    }
}
