package com.avs.rates

import android.app.Application
import com.avs.rates.di.ApplicationComponent
import com.avs.rates.di.DaggerApplicationComponent

class RatesApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}