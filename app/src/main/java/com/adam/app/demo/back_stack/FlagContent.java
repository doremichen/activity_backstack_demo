/**
 * Copyright (C) 2025 Adam Chen. All rights reserved.
 *
 * Description: This class is the flag content
 * Author: Adam Chen
 * Date: 2025-10-13
 */
package com.adam.app.demo.back_stack;

/**
 * @author AdamChen
 */
public enum FlagContent {

    INSTANCE;

    private int mFlag;

    public int getFlag() {
        return this.mFlag;
    }

    public void addFlag(int flag) {
        this.mFlag |= flag;
    }

    public void removeFlag(int flag) {
        this.mFlag &= ~flag;
    }

    public void initFlag() {
        this.mFlag = 0;
    }
}
