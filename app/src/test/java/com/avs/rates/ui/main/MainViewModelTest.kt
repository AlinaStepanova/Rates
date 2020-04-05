package com.avs.rates.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.avs.rates.RxSchedulerRule
import com.avs.rates.currency.Currency
import com.avs.rates.currency.HUF
import com.avs.rates.network.RatesServerApi
import com.avs.rates.network.dto.Conversion
import com.avs.rates.network.dto.Rates
import com.avs.rates.utils.RxBus
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks

import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @InjectMocks
    lateinit var rxBus: RxBus

    @Mock
    lateinit var ratesServerApi: RatesServerApi

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var conversion: Conversion

    private val delta = 0.0001

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(ratesServerApi, rxBus)
        conversion = Conversion(Currency.EUR.name, Rates(1.0, 12.0, 23.0, 0.1,
        12.3, 24.02, 23.04, 0.99, 3.23, 32.2, 12.234, 23.09,
        12.34, 9.32, 8.0988, 19.0, 33.8, 0.99, 1.99, 12.9, 9.09,
        8.009, 100.4, 1.1, 2.2, 333.9, 60.0, 0.999, 5.21, 1.1,
        12.2, 1.5))
    }

    @Test
    fun addCurrenciesToListTest() {
        viewModel.addCurrenciesToList()
        assertTrue(viewModel.getCurrenciesList().isNotEmpty())
        assertEquals(viewModel.getCurrenciesList().size, Currency.values().size)
    }

    @Test
    fun updateBaseCurrencyValueTest() {
        viewModel.updateBaseCurrencyValue("AUD")
        assertEquals(viewModel.getBaseCurrencyValue(), -1.0, delta)
        viewModel.updateBaseCurrencyValue("")
        assertEquals(viewModel.getBaseCurrencyValue(), -1.0, delta)
        viewModel.updateBaseCurrencyValue("12.02")
        assertEquals(viewModel.getBaseCurrencyValue(), 12.02, delta)
    }

    @Test
    fun updateBaseCurrencyTest() {
        viewModel.addCurrenciesToList()
        viewModel.handleServerResponse(conversion)
        val oldBaseCurrency = viewModel.getBaseCurrency()
        val newBaseCurrency = HUF()
        viewModel.updateBaseCurrency(newBaseCurrency)
        assertEquals(viewModel.getBaseCurrency(), newBaseCurrency)
        assertEquals(viewModel.getCurrenciesList().first, newBaseCurrency)
        assertEquals(viewModel.getCurrenciesList()[1], oldBaseCurrency)
    }
}