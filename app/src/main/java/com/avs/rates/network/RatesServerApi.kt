package com.avs.rates.network

import android.util.Log
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

class RatesServerApi @Inject constructor(
    private val ratesApi: RatesApi,
    private val rxBus: RxBus
) {

    fun getRatesPeriodically(initialDelay: Long, baseCurrency: String): Disposable {
        return Observable.interval(0, EMISSION_PERIOD, TimeUnit.MILLISECONDS)
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
        }
    }

    private fun handleError(error: Throwable?) {
        Log.d(this.javaClass.simpleName, error.toString())
    }

}