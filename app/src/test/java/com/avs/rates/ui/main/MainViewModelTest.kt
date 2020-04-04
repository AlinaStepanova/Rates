package com.avs.rates.ui.main

import com.avs.rates.RxSchedulerRule
import com.avs.rates.currency.Currency
import com.avs.rates.network.RatesServerApi
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

    private val delta = 0.0001

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(ratesServerApi, rxBus)
    }

    @Test
    fun addCurrenciesToList() {
        viewModel.addCurrenciesToList()
        assertTrue(viewModel.getCurrenciesList().isNotEmpty())
        assertEquals(viewModel.getCurrenciesList().size, Currency.values().size)
    }

    @Test
    fun updateBaseCurrencyValue() {
        viewModel.updateBaseCurrencyValue("AUD")
        assertEquals(viewModel.getBaseCurrencyValue(), -1.0, delta)
        viewModel.updateBaseCurrencyValue("")
        assertEquals(viewModel.getBaseCurrencyValue(), -1.0, delta)
        viewModel.updateBaseCurrencyValue("12.02")
        assertEquals(viewModel.getBaseCurrencyValue(), 12.02, delta)
    }
}