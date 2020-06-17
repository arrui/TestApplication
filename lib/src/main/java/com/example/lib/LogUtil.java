package com.example.lib;

/**
 * @file LogUtil.java
 * @Synopsis Java实现类似C/C++中的__FILE__、__FUNC__、__LINE__等,主要用于日志等功能中。
 * @author Arrui.c@gmail.com
 * @version 1.0
 * @date 2014-04-10
 */

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogUtil {
    public final static String TAG = "LogUtil";
    public final static boolean DEBUG = true;


    public static void log() {
        if (DEBUG) {
            Log.d(TAG, getFileLineMethod(true));
        }
    }

    public static void log(String str) {
        if (DEBUG) {
            Log.d(TAG, getFileLineMethod(true) + ": " + (str == null ? "" : str));
        }
    }

    public static String getFileLineMethod() {
        return getFileLineMethod(false);
    }

    /**
     * 打印调用栈
     *
     * @return
     */
    public static void printStackTrace(Exception e) {
        log(getStackTrace(e));
    }

    /**
     * 获取调用栈
     *
     * @return
     */
    public static String getStackTrace(Exception e) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder("");
            if (e == null) e = new Exception("stack");
            StackTraceElement[] trace = e.getStackTrace();
            for (int i = 0; i < trace.length; i++) {
                sb.append("\nat " + trace[i]);
            }
            return sb.toString();
        } else {
            return null;
        }
    }

    /**
     * @Synopsis 打印日志时获取当前的程序文件名、行号、方法名 输出格式为：[FileName | LineNumber | MethodName]
     */
    public static String getFileLineMethod(boolean selfCall) {
        if (DEBUG) {
            StackTraceElement traceElement = ((new Exception()).getStackTrace())[selfCall ? 2 : 1];
            StringBuffer toStringBuffer = new StringBuffer("[").append(traceElement.getFileName()).append(" | ")
                    .append(traceElement.getLineNumber()).append(" | ").append(traceElement.getMethodName())
                    .append("]");
            return toStringBuffer.toString();

        } else {
            return null;
        }
    }

    // 当前文件名
    public static String _FILE_() {
        if (DEBUG) {
            StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
            return traceElement.getFileName();
        } else {
            return null;
        }
    }

    // 当前方法名
    public static String _FUNC_() {
        if (DEBUG) {
            StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
            return traceElement.getMethodName();
        } else {
            return null;
        }
    }

    // 当前行号
    public static int _LINE_() {
        if (DEBUG) {
            StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
            return traceElement.getLineNumber();
        }
        return 0;
    }

    // 当前时间
    public static String _TIME_() {
        if (DEBUG) {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
            return sdf.format(now);
        } else {
            return null;
        }
    }

}