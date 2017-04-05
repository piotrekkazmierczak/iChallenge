package com.kazman.impraisechallenge.di

import android.app.Application

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class Injector {
    companion object {
        var component: ApplicationComponent? = null
        fun start(application: Application) {
            component = DaggerApplicationComponent.builder().androidModule(AndroidModule(application)).build()
        }
    }
}
