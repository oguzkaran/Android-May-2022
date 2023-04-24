package com.karandev.net.ip.tcp.server.functional;

@FunctionalInterface
public interface ISupplier<T> {
    T get() throws Exception;
}
