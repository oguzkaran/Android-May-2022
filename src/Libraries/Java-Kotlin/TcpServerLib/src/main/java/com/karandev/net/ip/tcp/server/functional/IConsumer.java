package com.karandev.net.ip.tcp.server.functional;

import java.net.Socket;

@FunctionalInterface
public interface IConsumer<T> {
    void accept(T t) throws Exception;
}
