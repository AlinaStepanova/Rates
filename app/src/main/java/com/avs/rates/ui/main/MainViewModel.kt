package com.avs.rates.ui.main

import androidx.lifecycle.ViewModel
import com.avs.rates.DEFAULT_CURRENCY
import com.avs.rates.network.RatesServerApi
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val ratesServerApi: RatesServerApi): ViewModel() {

    private var disposable: Disposable? = null

    init {
        disposable = ratesServerApi.getRatesPeriodically(DEFAULT_CURRENCY)
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}