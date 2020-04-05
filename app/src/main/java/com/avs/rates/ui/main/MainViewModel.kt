package com.avs.rates.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avs.rates.DEFAULT_CURRENCY
import com.avs.rates.DEFAULT_RATE_VALUE
import com.avs.rates.EMISSION_PERIOD
import com.avs.rates.currency.*
import com.avs.rates.network.ErrorType
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

    private var baseCurrency: BaseCurrency? = null
    private var baseCurrencyValue = DEFAULT_RATE_VALUE
    private var currenciesList = LinkedList<BaseCurrency>()
    private var _conversionList = MutableLiveData<List<BaseCurrency>>()
    val conversionList: LiveData<List<BaseCurrency>>
        get() = _conversionList
    private var _updateBaseCurrencyEvent = MutableLiveData<Boolean>()
    val updateBaseCurrencyEvent: LiveData<Boolean>
        get() = _updateBaseCurrencyEvent
    private var _networkErrorEvent = MutableLiveData<ErrorType>()
    val networkErrorEvent: LiveData<ErrorType>
        get() = _networkErrorEvent
    private var apiDisposable: Disposable? = null
    private var rxBusDisposable: Disposable? = null
    private var delayDisposable: Disposable? = null

    init {
        addCurrenciesToList()
        getRatesPeriodically(0)
        rxBusDisposable = rxBus.events.subscribe { event -> handleServerResponse(event) }
    }

    override fun onCleared() {
        apiDisposable?.dispose()
        rxBusDisposable?.dispose()
        delayDisposable?.dispose()
        super.onCleared()
    }

    /**
     * Adds known predefined currencies to the list of currencies
     */
    @VisibleForTesting
    fun addCurrenciesToList() {
        currenciesList.clear()
        currenciesList.addAll(
            listOf(
                AUD(), BGN(), BRL(), CAD(), CHF(), CNY(), CZK(), DKK(), EUR(), GBP(), HKD(), HRK(),
                HUF(), IDR(), ILS(), INR(), ISK(), JPY(), KRW(), MXN(), MYR(), NOK(), NZD(), PHP(),
                PLN(), RON(), RUB(), SEK(), SGD(), THB(), USD(), ZAR()
            )
        )
    }

    /**
     * Handles server response depending on a event type
     * @param event - a response from the server or network error
     */
    @VisibleForTesting
    fun handleServerResponse(event: Any?) {
        if (event is Conversion) {
            _networkErrorEvent.value = null
            handleConvertionResponse(event)
        } else if (event is ErrorType) {
            if (_conversionList.value == null) {
                _networkErrorEvent.value = event
            }
        }
    }

    @VisibleForTesting
    fun getCurrenciesList() = currenciesList

    @VisibleForTesting
    fun getBaseCurrency() = baseCurrency

    @VisibleForTesting
    fun getBaseCurrencyValue() = baseCurrencyValue

    /**
     * Makes request to the server to update the rates
     * @param initialDelay - the initial delay time to wait before emitting the first value
     */
    @VisibleForTesting
    fun getRatesPeriodically(initialDelay: Long) {
        apiDisposable =
            ratesServerApi.getRatesPeriodically(
                initialDelay, baseCurrency?.getShortName() ?: DEFAULT_CURRENCY
            )
    }

    /**
     * Performs updates of the base currency and currencies list according to the latest from the server
     * @param conversion - dto which contains latest data from the server
     */
    @VisibleForTesting
    fun handleConvertionResponse(conversion: Conversion) {
        val currency = getBaseCurrency(conversion.baseCurrency)
        var isBaseCurrencyChanged = false
        if (currency != baseCurrency) {
            updateBaseCurrency(currency)
            isBaseCurrencyChanged = true
        }
        updateRatesValue(conversion, currency)
        _conversionList.value = currenciesList
        if (isBaseCurrencyChanged) {
            _updateBaseCurrencyEvent.value = true
        }
    }

    /**
     * MPuts a new base currency to the first position in the currencies list
     * @param newBaseCurrency - a new base currency selected by the user
     */
    @VisibleForTesting
    fun updateBaseCurrency(newBaseCurrency: BaseCurrency) {
        baseCurrency = newBaseCurrency
        currenciesList.remove(newBaseCurrency)
        newBaseCurrency.rate = baseCurrencyValue
        currenciesList.addFirst(newBaseCurrency)
    }

    /**
     * Resets fields depending on the new selected base currency by the user
     * @param newBaseCurrency - a new base currency selected by the user
     */
    fun handleUserInteraction(newBaseCurrency: BaseCurrency) {
        apiDisposable?.dispose()
        delayDisposable?.dispose()
        baseCurrencyValue = newBaseCurrency.rate
        updateBaseCurrency(newBaseCurrency)
        delayDisposable = updateRecyclerView()
        getRatesPeriodically(EMISSION_PERIOD)
    }

    /**
     * Sends event to update base currency in the recycler view, when the delay completes
     */
    @VisibleForTesting
    fun updateRecyclerView(): Disposable {
        return Completable.complete()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(EMISSION_PERIOD / 2, TimeUnit.MILLISECONDS)
            .doOnComplete { _updateBaseCurrencyEvent.postValue(true) }
            .subscribe()
    }

    /**
     * @param text - string representation of the rate entered by the user
     */
    fun updateBaseCurrencyValue(text: String) {
        baseCurrencyValue = text.toDoubleOrNull() ?: -1.0
    }

    /**
     * Updates rate values in currenciesList according to the new value of a base currency
     * @param conversion - dto which contains latest data from the server
     * @param baseCurrency - a base currency
     */
    @VisibleForTesting
    fun updateRatesValue(conversion: Conversion, baseCurrency: BaseCurrency) {
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
                    if (currency != baseCurrency)
                        currency.rate = conversion.rates.CZK * baseCurrencyValue
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