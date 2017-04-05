package com.kazman.impraisechallenge.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */

@Module
class AndroidModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }
}
