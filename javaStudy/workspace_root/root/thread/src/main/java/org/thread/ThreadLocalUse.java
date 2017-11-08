package org.thread;

import java.util.Date;

public class ThreadLocalUse
{
	/*
	 * InheritableThreadLocal 可以继承从父线程中继承下来的值。
	 */
    public static final InheritableThreadLocal<?> itl = new InheritableThreadLocal<Object>(){
        @Override protected Object initialValue()
        {
            return new Date().getTime();
        }
 
        @Override protected Object childValue(Object parentValue)
        {
            return parentValue+" which plus in subThread.";
        }
    };
 
    public static void main(String[] args)
    {
        System.out.println("Main: get value = "+itl.get());
        Thread a = new Thread(new Runnable(){
            public void run()
            {
                System.out.println(Thread.currentThread().getName()+": get value = "+itl.get());
            }
        });
        a.start();
    }
}