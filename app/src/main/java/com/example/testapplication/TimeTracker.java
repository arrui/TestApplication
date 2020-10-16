package com.example.testapplication;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeTracker {
    public static final String TAG = TimeTracker.class.getSimpleName();
    private Map<String, Long> map;

    private TimeTracker() {
        map = new LinkedHashMap<>();
    }

    private static class TimeTraceUtilHolder {
        private static final TimeTracker instance = new TimeTracker();
    }

    public static TimeTracker getInstance() {
        return TimeTraceUtilHolder.instance;
    }

    public void clear() {
        map.clear();
    }

    public void logHeadAndClear() {
        Map.Entry<String, Long> entry = getHead(map);
        if (entry != null) {
            log(entry.getKey());
        }
        clear();
    }

    public void log(String tag) {
        long now = System.currentTimeMillis();
        if (map.containsKey(tag)) {
            end(tag, now);
        } else {
            begin(tag, now);
        }
    }

    private void begin(String tag, long time) {
        Map.Entry<String, Long> last = null;
        try {
            last = getTail(map);
        } catch (Exception ignored) {

        }
        if (map.size() > 1 && last != null) {
            Log.e(TAG, " begin: [" + last.getKey() + "] ==>> [" + tag + "] consume [" + (time - last.getValue()) + "ms]");
        } else {
            Log.e(TAG, " begin: [" + tag + "]");
        }
        map.put(tag, time);
    }

    private void end(String tag, long time) {
        if (map.containsKey(tag)) {
            long last = map.get(tag);
            Log.e(TAG, " end:   [" + tag + "] consume [" + (time - last) + "ms]");
            map.remove(tag);
        }
    }

    private Map.Entry<String, Long> getHead(Map<String, Long> map) {
        Iterator<Map.Entry<String, Long>> iterator = map.entrySet().iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    private Map.Entry<String, Long> getTail(Map<String, Long> map) {
        Iterator<Map.Entry<String, Long>> iterator = map.entrySet().iterator();
        Map.Entry<String, Long> tail = null;
        while (iterator.hasNext()) {
            tail = iterator.next();
        }
        return tail;
    }
}
