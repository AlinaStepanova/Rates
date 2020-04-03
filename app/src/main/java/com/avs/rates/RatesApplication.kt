package com.avs.rates

import android.app.Application
import com.avs.rates.di.ApplicationComponent
import com.avs.rates.di.DaggerApplicationComponent

/**
 * A starting point of the app
 */
class RatesApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}