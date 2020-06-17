package com.example.testapplication;

import android.util.Log;

import java.util.Random;

public class TestLock {
    private final static String TAG = "TestLock";
    public TestLock(){
        StuThread1 stuThread1 = new StuThread1();
        StuThread2 stuThread2 = new StuThread2();
        for (int i = 0; i < 2; i++) {
            new Thread(stuThread1).start();
            new Thread(stuThread2).start();
        }
    }
    class StuThread1 implements Runnable {
        @Override
        public void run() {
            writeLog();
        }
    }

    class StuThread2 implements Runnable {
        @Override
        public void run() {
            readLog();
        }
    }

    private synchronized void writeLog() {
        for (int i = 0; i < 3; i++) {
            try {
                Log.e(TAG, "showLog: " + Thread.currentThread().getName() + "写入中");
                Thread.sleep(new Random().nextInt(1000));
                Log.e(TAG, "showLog: " + Thread.currentThread().getName() + "写入完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void readLog() {
        for (int i = 0; i < 3; i++) {
            try {
                Log.e(TAG, "showLog: " + Thread.currentThread().getName() + "读取中");
                Thread.sleep(new Random().nextInt(1000));
                Log.e(TAG, "showLog: " + Thread.currentThread().getName() + "读取完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
