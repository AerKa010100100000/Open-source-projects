package com.cx330.utility;

public class ThreadLocalBuffer {
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    public ThreadLocalBuffer() {
    }

    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    public static <Object> java.lang.Object get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
