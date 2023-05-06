/*----------------------------------------------------------------------
	FILE        : ServerFactory.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 05.05.2023

	Factory class for Servers

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package com.karandev.net.ip.tcp.server;

import java.io.IOException;

public class ServerFactory {
    public static Server createConcurrentServer(int port) throws IOException
    {
        return ConcurrentServer.of(port);
    }

    public static Server createIterativeServer(int port) throws IOException
    {
        return IterativeServer.of(port);
    }
}
