package com.example.lib;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class Test {

    public static void main(String[] args) {
        try {
            int[] arr = new int[]{2, 4, 1, 6, 19, 3};
            Class cls = ClassLoader.getSystemClassLoader().loadClass("com.example.lib.ALClass");
//            Class cls = Class.forName("com.example.lib.ALClass");
            Object obj = cls.newInstance();
            Method method = cls.getMethod("bubbleSort", int[].class);
            method.invoke(obj, arr);
            for (int i = 0; i < arr.length; i++) {
                System.out.println("" + arr[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        final Looper looper = Looper.myLooper();
//        try {
//            System.out.println("0:"+Thread.currentThread());
//            final Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
////                    new Handler(looper);
//                    System.out.println("1:"+Thread.currentThread());
//                }
//            });
//            thread.start();
//            thread.join(1000);
//            System.out.println("2:"+Thread.currentThread());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("===main==="+Runtime.getRuntime().availableProcessors());
//        new JavaClass().find2(3, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
//        new JavaClass().isValid("()[](){[]}");
//        new JavaClass().sortTest();
//        new JavaClass().climbStairs(6);
//        new KotlinClass().testCoroutine();
//        for (int i = 0; i < 1300; i++) {
//            final int index = i;
//            ThreadUtils.getCachedPool().submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(new Random().nextInt(3000) + 1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("tag 1 threadName:" + Thread.currentThread().getName() + "index:" + index);
//                }
//            });
//            Executors.newFixedThreadPool(10).submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(new Random().nextInt(3000) + 1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("tag 2 threadName:" + Thread.currentThread().getName() + "index:" + index);
//                }
//            });
//            Executors.newCachedThreadPool().submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(new Random().nextInt(3000) + 1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("tag 2 threadName:" + Thread.currentThread().getName() + "index:" + index);
//                }
//            });
//        }
    }
}