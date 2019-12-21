package com.example.rxjavademo.activity.dagger2;

import android.util.Log;

import dagger.Module;
import dagger.Provides;

@Module
public class MemoryCardModul {
    private int memorySize;
    private static final String TAG = "SmartPhone";

    public MemoryCardModul(int memorySize) {
        this.memorySize = memorySize;
    }

    @Provides
    MemoryCard provideMemoryCard(){
        Log.i(TAG, "size of memory card is "+ memorySize);
        return new MemoryCard();
    }
}
