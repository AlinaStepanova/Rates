package com.avs.rates.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.avs.rates.EMISSION_PERIOD
import com.avs.rates.RxSchedulerRule
import com.avs.rates.currency.Currency
import com.avs.rates.currency.HUF
import com.avs.rates.currency.JPY
import com.avs.rates.currency.KRW
import com.avs.rates.getOrAwaitValue
import com.avs.rates.network.ErrorType
import com.avs.rates.network.RatesServerApi
import com.avs.rates.network.dto.Conversion
import com.avs.rates.network.dto.Rates
import com.avs.rates.utils.RxBus
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*

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

    private lateinit var conversion: Conversion

    private val delta = 0.0001

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(ratesServerApi, rxBus)
        conversion = Conversion(
            Currency.EUR.name, Rates(
                1.0, 12.0, 23.0, 0.1, 12.3, 24.02, 23.04,
                0.99, 3.23, 32.2, 12.234, 23.09, 12.34, 9.32,
                8.0988, 19.0, 33.8, 0.99, 1.99, 12.9, 9.09,
                8.009, 100.4, 1.1, 2.2, 333.9, 60.0, 0.999,
                5.21, 1.1, 12.2, 1.5
            )
        )
    }

    @Test
    fun addCurrenciesToListTest() {
        viewModel.addCurrenciesToList()
        assertTrue(viewModel.getCurrenciesList().isNotEmpty())
        assertEquals(viewModel.getCurrenciesList().size, Currency.values().size)
    }

    @Test
    fun updateBaseCurrencyValueTest() {
        viewModel.updateBaseCurrencyValue(Currency.EUR.name)
        assertEquals(viewModel.getBaseCurrencyValue(), -1.0, delta)
        viewModel.updateBaseCurrencyValue("")
        assertEquals(viewModel.getBaseCurrencyValue(), -1.0, delta)
        viewModel.updateBaseCurrencyValue("12.02")
        assertEquals(viewModel.getBaseCurrencyValue(), 12.02, delta)
    }

    @Test
    fun handleServerResponseTest() {
        viewModel.handleServerResponse(ErrorType.SERVER)
        var serverEvent = viewModel.networkErrorEvent.getOrAwaitValue()
        assertTrue(serverEvent == ErrorType.SERVER)
        viewModel.handleServerResponse(conversion)
        serverEvent = viewModel.networkErrorEvent.getOrAwaitValue()
        assertTrue(serverEvent == null)
    }

    @Test
    fun handleUserInteractionTest() {
        val newBaseCurrency = KRW()
        viewModel.handleUserInteraction(newBaseCurrency)
        verify(ratesServerApi, times(1))
            .getRatesPeriodically(EMISSION_PERIOD, newBaseCurrency.getShortName())
    }

    @Test
    fun updateBaseCurrencyTest() {
        viewModel.addCurrenciesToList()
        viewModel.handleConversionResponse(conversion)
        val oldBaseCurrency = viewModel.getBaseCurrency()
        val newBaseCurrency = HUF()
        viewModel.updateBaseCurrency(newBaseCurrency)
        assertEquals(viewModel.getBaseCurrency(), newBaseCurrency)
        assertEquals(viewModel.getCurrenciesList().first, newBaseCurrency)
        assertFalse(viewModel.getCurrenciesList().indexOfLast { it == newBaseCurrency } != 0)
        assertEquals(viewModel.getCurrenciesList()[1], oldBaseCurrency)
        assertEquals(viewModel.getCurrenciesList().size, Currency.values().size)
        val conversionList = viewModel.conversionList.getOrAwaitValue()
        assertFalse(conversionList.isNullOrEmpty())
    }

    @Test
    fun updateRecyclerViewTest() {
        viewModel.updateRecyclerView()
        val updateEvent = viewModel.updateBaseCurrencyEvent.getOrAwaitValue()
        assertTrue(updateEvent)
    }

    @Test
    fun updateRatesValueTest() {
        val newValue = 500.0
        val oldAUDValue = 1.5
        val oldZARValue = 13.25
        val newBaseCurrency = JPY()
        conversion = Conversion(
            newBaseCurrency.getShortName(), Rates(
                oldAUDValue, 2.06, 23.0, 0.1, 12.3, 24.02, 23.04,
                0.99, 3.23, 32.2, 12.234, 23.09, 12.34, 9.32,
                8.0988, 19.0, 33.8, newValue, 1.99, 12.9, 9.09,
                8.009, 100.4, 1.1, 2.2, 333.9, 60.0, 0.999,
                5.21, 1.1, 12.2, oldZARValue
            )
        )
        newBaseCurrency.rate = newValue
        viewModel.handleUserInteraction(newBaseCurrency)
        viewModel.updateRatesValue(conversion, newBaseCurrency)
        assertEquals(viewModel.getCurrenciesList().first.rate, newValue, delta)
        assertEquals(viewModel.getCurrenciesList()[1].rate, newValue * oldAUDValue, delta)
        assertEquals(viewModel.getCurrenciesList().last.rate, newValue * oldZARValue, delta)
    }
}