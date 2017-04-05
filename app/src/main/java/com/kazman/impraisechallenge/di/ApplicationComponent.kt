package com.kazman.impraisechallenge.di

import android.content.Context
import dagger.Component
import javax.inject.Singleton

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {
    fun getContext(): Context
}
