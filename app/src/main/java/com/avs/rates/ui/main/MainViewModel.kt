package com.avs.rates.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    private var baseCurrency = EUR()
    private var currenciesList = LinkedList<BaseCurrency>()
    private var _conversion = MutableLiveData<List<BaseCurrency>>()
    val conversion: LiveData<List<BaseCurrency>>
        get() = _conversion

    init {
        addCurrenciesToList()
        apiDisposable = ratesServerApi.getRatesPeriodically(baseCurrency.getShortName())
        rxBusDisposable = rxBus.events.subscribe { event ->
            if (event is Conversion) {
                updateList(event)
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

    private fun updateList(conversion: Conversion) {
        for (currency in currenciesList) {
            when (currency) {
                is AUD -> {
                    currency.setValue(conversion.rates.AUD)
                }
                is BGN -> {
                    currency.setValue(conversion.rates.BGN)
                }
                is BRL -> {
                    currency.setValue(conversion.rates.BRL)
                }
                is CAD -> {
                    currency.setValue(conversion.rates.CAD)
                }
                is CHF -> {
                    currency.setValue(conversion.rates.CHF)
                }
                is CNY -> {
                    currency.setValue(conversion.rates.CNY)
                }
                is CZK -> {
                    currency.setValue(conversion.rates.CZK)
                }
                is DKK -> {
                    currency.setValue(conversion.rates.DKK)
                }
                is GBP -> {
                    currency.setValue(conversion.rates.GBP)
                }
                is HKD -> {
                    currency.setValue(conversion.rates.HKD)
                }
                is HRK -> {
                    currency.setValue(conversion.rates.HRK)
                }
                is HUF -> {
                    currency.setValue(conversion.rates.HUF)
                }
                is IDR -> {
                    currency.setValue(conversion.rates.IDR)
                }
                is ILS -> {
                    currency.setValue(conversion.rates.ILS)
                }
                is INR -> {
                    currency.setValue(conversion.rates.INR)
                }
                is ISK -> {
                    currency.setValue(conversion.rates.ISK)
                }
                is JPY -> {
                    currency.setValue(conversion.rates.JPY)
                }
                is KRW -> {
                    currency.setValue(conversion.rates.KRW)
                }
                is MXN -> {
                    currency.setValue(conversion.rates.MXN)
                }
                is MYR -> {
                    currency.setValue(conversion.rates.MYR)
                }
                is NZD -> {
                    currency.setValue(conversion.rates.NZD)
                }
                is PHP -> {
                    currency.setValue(conversion.rates.PHP)
                }
                is PLN -> {
                    currency.setValue(conversion.rates.PLN)
                }
                is RON -> {
                    currency.setValue(conversion.rates.RON)
                }
                is RUB -> {
                    currency.setValue(conversion.rates.RUB)
                }
                is SEK -> {
                    currency.setValue(conversion.rates.SEK)
                }
                is SGD -> {
                    currency.setValue(conversion.rates.SGD)
                }
                is THB -> {
                    currency.setValue(conversion.rates.THB)
                }
                is USD -> {
                    currency.setValue(conversion.rates.USD)
                }
                is ZAR -> {
                    currency.setValue(conversion.rates.ZAR)
                }
            }
        }
    }
}