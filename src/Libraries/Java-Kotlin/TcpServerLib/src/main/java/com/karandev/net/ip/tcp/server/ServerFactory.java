package com.karandev.net.ip.tcp.server;

import java.io.IOException;

public class ServerFactory {
    public static Server createConcurrentServer(int port) throws IOException
    {
        return ConcurrentServer.of(port);
    }
}
