/*----------------------------------------------------------------------
	FILE        : ISupplier.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 24.04.2023

	ISupplier functional interface has an abstract get method which
	throws Exception

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package com.karandev.net.ip.tcp.server.functional;

@FunctionalInterface
public interface ISupplier<T> {
    T get() throws Exception;
}
