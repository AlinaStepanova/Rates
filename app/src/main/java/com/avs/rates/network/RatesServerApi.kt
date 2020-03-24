package com.avs.rates.network

import android.util.Log
import com.avs.rates.EMISSION_PERIOD
import com.avs.rates.network.dto.Rates
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RatesServerApi @Inject constructor(private val ratesApi: RatesApi) {

    fun getRatesPeriodically(baseCurrency: String): Disposable {
        return Observable.interval(0, EMISSION_PERIOD, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { this.getRates(baseCurrency) }
    }

    private fun getRates(baseCurrency: String): Disposable {
        return ratesApi
            .getLatestRatesByBase(baseCurrency)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ checkResponse(it) }, { handleError(it) })
    }

    private fun handleError(error: Throwable?) {
        Log.d(this.javaClass.simpleName, error.toString())
    }

    private fun checkResponse(it: Response<Rates>) {
        Log.d(this.javaClass.simpleName, it.body().toString())
    }

}