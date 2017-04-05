package com.kazman.impraisechallenge

import android.app.Application
import com.kazman.impraisechallenge.di.Injector

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class ChallengeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.start(this)
    }
}
