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
    private var baseCurrency : BaseCurrency? = null
    private var currenciesList = LinkedList<BaseCurrency>()
    private var _conversion = MutableLiveData<List<BaseCurrency>>()
    val conversion: LiveData<List<BaseCurrency>>
        get() = _conversion

    init {
        addCurrenciesToList()
        apiDisposable = ratesServerApi.getRatesPeriodically(baseCurrency?.getShortName() ?: DEFAULT_CURRENCY)
        rxBusDisposable = rxBus.events.subscribe { event ->
            if (event is Conversion) {
                val currency = getBaseCurrency(event.baseCurrency)
                if (event.baseCurrency != currenciesList[0].getShortName()) {
                    baseCurrency = currency
                    currenciesList.removeLastOccurrence(currency)
                    currenciesList.addFirst(currency)
                }
                updateRateValues(event)
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

    private fun updateRateValues(conversion: Conversion) {
        for (currency in currenciesList) {
            when (currency) {
                is EUR -> {
                    currency.rate = conversion.rates.EUR
                }
                is AUD -> {
                    currency.rate = conversion.rates.AUD
                }
                is BGN -> {
                    currency.rate = conversion.rates.BGN
                }
                is BRL -> {
                    currency.rate = conversion.rates.BRL
                }
                is CAD -> {
                    currency.rate = conversion.rates.CAD
                }
                is CHF -> {
                    currency.rate = conversion.rates.CHF
                }
                is CNY -> {
                    currency.rate = conversion.rates.CNY
                }
                is CZK -> {
                    currency.rate = conversion.rates.CZK
                }
                is DKK -> {
                    currency.rate = conversion.rates.DKK
                }
                is GBP -> {
                    currency.rate = conversion.rates.GBP
                }
                is HKD -> {
                    currency.rate = conversion.rates.HKD
                }
                is HRK -> {
                    currency.rate = conversion.rates.HRK
                }
                is HUF -> {
                    currency.rate = conversion.rates.HUF
                }
                is IDR -> {
                    currency.rate = conversion.rates.IDR
                }
                is ILS -> {
                    currency.rate = conversion.rates.ILS
                }
                is INR -> {
                    currency.rate = conversion.rates.INR
                }
                is ISK -> {
                    currency.rate = conversion.rates.ISK
                }
                is JPY -> {
                    currency.rate = conversion.rates.JPY
                }
                is KRW -> {
                    currency.rate = conversion.rates.KRW
                }
                is MXN -> {
                    currency.rate = conversion.rates.MXN
                }
                is MYR -> {
                    currency.rate = conversion.rates.MYR
                }
                is NOK -> {
                    currency.rate = conversion.rates.NOK
                }
                is NZD -> {
                    currency.rate = conversion.rates.NZD
                }
                is PHP -> {
                    currency.rate = conversion.rates.PHP
                }
                is PLN -> {
                    currency.rate = conversion.rates.PLN
                }
                is RON -> {
                    currency.rate = conversion.rates.RON
                }
                is RUB -> {
                    currency.rate = conversion.rates.RUB
                }
                is SEK -> {
                    currency.rate = conversion.rates.SEK
                }
                is SGD -> {
                    currency.rate = conversion.rates.SGD
                }
                is THB -> {
                    currency.rate = conversion.rates.THB
                }
                is USD -> {
                    currency.rate = conversion.rates.USD
                }
                is ZAR -> {
                    currency.rate = conversion.rates.ZAR
                }
            }
        }
    }
}