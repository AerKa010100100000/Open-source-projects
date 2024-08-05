package com.cx330.utility;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class HashMapBuffer {

    private static volatile HashMapBuffer hashMapBuffer;
    private static Map<String,Object> buffer;
    private HashMapBuffer(){
        buffer = new HashMap<String, Object>();
    }

    // 懒汉式单例模式
    public static HashMapBuffer getInstance(){
        if (hashMapBuffer == null){// 线程A和线程B同时看
            synchronized (HashMapBuffer.class) {// 线程A或线程B获得该锁进行初始化
                if (hashMapBuffer == null) {// 其中一个线程进入该分支，另外一个线程则不会进入该分支
                    hashMapBuffer = new HashMapBuffer();
                }
            }
        }
        return hashMapBuffer;
    }

    /**
     * 添加到内存
     */
    public void addCacheData(String key,Object obj){
        buffer.put(key, obj);
    }

    /**
     * 从内存中取出
     */
    public Object getCacheData(String key){
        return buffer.get(key);
    }

    /**
     * 从内存中清除
     */
    public void removeCacheData(String key){
        buffer.remove(key);
    }

}

