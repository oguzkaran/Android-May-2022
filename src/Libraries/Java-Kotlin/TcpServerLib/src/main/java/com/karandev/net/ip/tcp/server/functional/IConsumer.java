/*----------------------------------------------------------------------
	FILE        : IConsumer.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 24.04.2023

	IRunnable functional interface has an abstract accept method which
	throws Exception

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package com.karandev.net.ip.tcp.server.functional;

@FunctionalInterface
public interface IConsumer<T> {
    void accept(T t) throws Exception;
}
