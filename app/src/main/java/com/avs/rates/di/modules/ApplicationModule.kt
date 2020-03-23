package com.avs.rates.di.modules

import com.avs.rates.network.RatesApi
import com.avs.rates.network.RatesServerApi
import dagger.Module
import dagger.Provides

@Module(includes = [
    NetworkModule::class
])
class ApplicationModule {

    @Provides
    fun provideRatesServerApi(ratesApi: RatesApi): RatesServerApi {
        return RatesServerApi(ratesApi)
    }
}