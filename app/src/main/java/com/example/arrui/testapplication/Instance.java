package com.example.arrui.testapplication;

import com.example.arrui.testapplication.utils.LogUtil;

/**
 * Created by Arrui.c on 2017/3/7.
 */

public class Instance {
    private int a;
    private float b;
    private long c;

    public Instance(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
    }

    public void print() {
        LogUtil.log("a:" + a + ",b:" + b + ",c:" + c);
    }

    public static class Builder implements BuilderT {
        private int a;
        private float b;
        private long c;

        public Builder(int a, float b) {
            this.a = a;
            this.b = b;
        }

        public Builder setC(long c) {
            this.c = c;
            return this;
        }

        @Override
        public Instance build() {
            return new Instance(this);
        }
    }
}
