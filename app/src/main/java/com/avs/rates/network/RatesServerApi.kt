package com.avs.rates.network

import android.util.Log
import com.avs.rates.BuildConfig
import com.avs.rates.EMISSION_PERIOD
import com.avs.rates.network.dto.Conversion
import com.avs.rates.utils.RxBus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * A singleton class which encapsulates work with the networking
 * @property ratesApi - an interface used by Retrofit library
 * @property rxBus - event bus
 */
class RatesServerApi @Inject constructor(
    private val ratesApi: RatesApi,
    private val rxBus: RxBus
) {

    fun getRatesPeriodically(initialDelay: Long, baseCurrency: String): Disposable {
        return Observable.interval(initialDelay, EMISSION_PERIOD, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { getRates(baseCurrency) }
    }

    private fun getRates(baseCurrency: String): Disposable {
        return ratesApi
            .getLatestRatesByBase(baseCurrency)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ checkResponse(it) }, { handleError(it) })
    }

    private fun checkResponse(response: Response<Conversion>) {
        if (response.body() != null && response.isSuccessful) {
            rxBus.send(response.body()!!)
        } else {
            rxBus.send(ErrorType.SERVER)
        }
    }

    private fun handleError(error: Throwable?) {
        if (error != null) {
            rxBus.send(ErrorType.NETWORK)
        }
        if (BuildConfig.DEBUG) {
            Log.d(this.javaClass.simpleName, error.toString())
        }
    }

}