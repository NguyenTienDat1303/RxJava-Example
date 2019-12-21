package com.example.rxjavademo.activity.dagger2;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class NCBatteryModule {
    @Binds
    abstract Battery bindNCBattery(NickelCadmiumBattery nickelCadmiumBattery);
}
