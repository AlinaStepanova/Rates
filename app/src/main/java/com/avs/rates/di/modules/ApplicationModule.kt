package com.avs.rates.di.modules

import com.avs.rates.network.RatesApi
import com.avs.rates.network.RatesServerApi
import com.avs.rates.utils.RxBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    NetworkModule::class
])
class ApplicationModule {

    @Provides
    @Singleton
    fun provideBus(): RxBus {
        return RxBus()
    }

    @Provides
    fun provideRatesServerApi(ratesApi: RatesApi, rxBus: RxBus): RatesServerApi {
        return RatesServerApi(ratesApi, rxBus)
    }
}