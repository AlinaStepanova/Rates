package com.avs.rates.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avs.rates.DEFAULT_CURRENCY
import com.avs.rates.currency.*
import com.avs.rates.network.RatesServerApi
import com.avs.rates.network.dto.Conversion
import com.avs.rates.utils.RxBus
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    ratesServerApi: RatesServerApi,
    rxBus: RxBus
) : ViewModel() {

    private var apiDisposable: Disposable? = null
    private var rxBusDisposable: Disposable? = null
    private var baseCurrency: BaseCurrency? = null
    private var baseCurrencyValue = 1.0
    private var currenciesList = LinkedList<BaseCurrency>()
    private var _conversion = MutableLiveData<List<BaseCurrency>>()
    val conversion: LiveData<List<BaseCurrency>>
        get() = _conversion

    init {
        addCurrenciesToList()
        apiDisposable =
            ratesServerApi.getRatesPeriodically(baseCurrency?.getShortName() ?: DEFAULT_CURRENCY)
        rxBusDisposable = rxBus.events.subscribe { event ->
            if (event is Conversion) {
                val currency = getBaseCurrency(event.baseCurrency)
                if (event.baseCurrency != currenciesList[0].getShortName()) {
                    baseCurrency = currency
                    currenciesList.remove(currency)
                    currency.rate = baseCurrencyValue
                    currenciesList.addFirst(currency)
                }
                updateRateValues(event, currency)
                _conversion.value = currenciesList
            }
        }
    }

    override fun onCleared() {
        apiDisposable?.dispose()
        rxBusDisposable?.dispose()
        super.onCleared()
    }

    private fun addCurrenciesToList() {
        currenciesList.add(AUD())
        currenciesList.add(BGN())
        currenciesList.add(BRL())
        currenciesList.add(CAD())
        currenciesList.add(CHF())
        currenciesList.add(CNY())
        currenciesList.add(CZK())
        currenciesList.add(DKK())
        currenciesList.add(EUR())
        currenciesList.add(GBP())
        currenciesList.add(HKD())
        currenciesList.add(HRK())
        currenciesList.add(HUF())
        currenciesList.add(IDR())
        currenciesList.add(ILS())
        currenciesList.add(INR())
        currenciesList.add(ISK())
        currenciesList.add(JPY())
        currenciesList.add(KRW())
        currenciesList.add(MXN())
        currenciesList.add(MYR())
        currenciesList.add(NOK())
        currenciesList.add(NZD())
        currenciesList.add(PHP())
        currenciesList.add(PLN())
        currenciesList.add(RON())
        currenciesList.add(RUB())
        currenciesList.add(SEK())
        currenciesList.add(SGD())
        currenciesList.add(THB())
        currenciesList.add(USD())
        currenciesList.add(ZAR())
    }

    private fun updateRateValues(
        conversion: Conversion,
        baseCurrency: BaseCurrency
    ) {
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