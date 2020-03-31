package com.avs.rates.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avs.rates.DEFAULT_CURRENCY
import com.avs.rates.EMISSION_PERIOD
import com.avs.rates.currency.*
import com.avs.rates.network.RatesServerApi
import com.avs.rates.network.dto.Conversion
import com.avs.rates.utils.RxBus
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val ratesServerApi: RatesServerApi,
    rxBus: RxBus
) : ViewModel() {

    private var apiDisposable: Disposable? = null
    private var rxBusDisposable: Disposable? = null
    private var delayDisposable: Disposable? = null
    private var baseCurrency: BaseCurrency? = null
    private var baseCurrencyValue = 1.0
    private var currenciesList = LinkedList<BaseCurrency>()
    private var _conversionList = MutableLiveData<List<BaseCurrency>>()
    val conversion: LiveData<List<BaseCurrency>>
        get() = _conversionList
    private var _updateBaseCurrencyItemEvent = MutableLiveData<Boolean>()
    val updateBaseCurrencyItemEvent: LiveData<Boolean>
        get() = _updateBaseCurrencyItemEvent

    init {
        _updateBaseCurrencyItemEvent.value = false
        addCurrenciesToList()
        apiDisposable =
            ratesServerApi.getRatesPeriodically(
                0, baseCurrency?.getShortName() ?: DEFAULT_CURRENCY
            )
        rxBusDisposable = rxBus.events.subscribe { event ->
            if (event is Conversion) {
                val currency = getBaseCurrency(event.baseCurrency)
                if (event.baseCurrency != currenciesList[0].getShortName()) {
                    updateBaseCurrency(currency)
                }
                updateRateValues(event, currency)
                _conversionList.value = currenciesList
            }
        }
    }

    override fun onCleared() {
        apiDisposable?.dispose()
        rxBusDisposable?.dispose()
        delayDisposable?.dispose()
        super.onCleared()
    }

    private fun addCurrenciesToList() {
        currenciesList.addAll(
            listOf(
                AUD(), BGN(), BRL(), CAD(), CHF(), CNY(), CZK(), DKK(), EUR(), GBP(), HKD(), HRK(),
                HUF(), IDR(), ILS(), INR(), ISK(), JPY(), KRW(), MXN(), MYR(), NOK(), NZD(), PHP(),
                PLN(), RON(), RUB(), SEK(), SGD(), THB(), USD(), ZAR()
            )
        )
    }

    private fun updateBaseCurrency(newBaseCurrency: BaseCurrency) {
        baseCurrency = newBaseCurrency
        currenciesList.remove(newBaseCurrency)
        newBaseCurrency.rate = baseCurrencyValue
        currenciesList.addFirst(newBaseCurrency)
    }

    fun updateRates(newBaseCurrency: BaseCurrency) {
        apiDisposable?.dispose()
        delayDisposable?.dispose()
        baseCurrencyValue = newBaseCurrency.rate
        updateBaseCurrency(newBaseCurrency)
        delayDisposable = updateRecyclerView()
        apiDisposable = ratesServerApi.getRatesPeriodically(
            EMISSION_PERIOD, baseCurrency?.getShortName() ?: DEFAULT_CURRENCY
        )
    }

    private fun updateRecyclerView(): Disposable {
        return Completable.complete()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(EMISSION_PERIOD / 2, TimeUnit.MILLISECONDS)
            .doOnComplete { _updateBaseCurrencyItemEvent.postValue(true) }
            .subscribe()
    }

    fun updateBaseCurrencyValue(text: String) {
        val value = text.trim()
        if (value.isNotEmpty()) {
            val doubleValue = value.toDoubleOrNull()
            if (doubleValue != null) {
                //todo provide formatting
                baseCurrencyValue = doubleValue
            }
        } /*else {
            baseCurrencyValue = 0.0
        }*/
    }

    private fun updateRateValues(conversion: Conversion, baseCurrency: BaseCurrency) {
        for (currency in currenciesList) {
            when (currency) {
                is EUR -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.EUR * baseCurrencyValue
                }
                is AUD -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.AUD * baseCurrencyValue
                }
                is BGN -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.BGN * baseCurrencyValue
                }
                is BRL -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.BRL * baseCurrencyValue
                }
                is CAD -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.CAD * baseCurrencyValue
                }
                is CHF -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.CHF * baseCurrencyValue
                }
                is CNY -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.CNY * baseCurrencyValue
                }
                is CZK -> {
                    if (currency != baseCurrency) currency.rate =
                        conversion.rates.CZK * baseCurrencyValue
                }
                is DKK -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.DKK * baseCurrencyValue
                }
                is GBP -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.GBP * baseCurrencyValue
                }
                is HKD -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.HKD * baseCurrencyValue
                }
                is HRK -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.HRK * baseCurrencyValue
                }
                is HUF -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.HUF * baseCurrencyValue
                }
                is IDR -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.IDR * baseCurrencyValue
                }
                is ILS -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.ILS * baseCurrencyValue
                }
                is INR -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.INR * baseCurrencyValue
                }
                is ISK -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.ISK * baseCurrencyValue
                }
                is JPY -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.JPY * baseCurrencyValue
                }
                is KRW -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.KRW * baseCurrencyValue
                }
                is MXN -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.MXN * baseCurrencyValue
                }
                is MYR -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.MYR * baseCurrencyValue
                }
                is NOK -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.NOK * baseCurrencyValue
                }
                is NZD -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.NZD * baseCurrencyValue
                }
                is PHP -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.PHP * baseCurrencyValue
                }
                is PLN -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.PLN * baseCurrencyValue
                }
                is RON -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.RON * baseCurrencyValue
                }
                is RUB -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.RUB * baseCurrencyValue
                }
                is SEK -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.SEK * baseCurrencyValue
                }
                is SGD -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.SGD * baseCurrencyValue
                }
                is THB -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.THB * baseCurrencyValue
                }
                is USD -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.USD * baseCurrencyValue
                }
                is ZAR -> {
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.ZAR * baseCurrencyValue
                }
            }
        }
    }
}