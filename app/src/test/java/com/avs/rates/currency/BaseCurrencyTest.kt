package com.avs.rates.currency

import com.avs.rates.DEFAULT_RATE_VALUE
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.properties.Delegates

class BaseCurrencyTest {

    private lateinit var baseCurrency: BaseCurrency
    private lateinit var currency: BaseCurrency
    private var delta by Delegates.notNull<Double>()

    @Before
    fun setUp() {
        baseCurrency = AUD()
        currency = NOK()
        delta = 0.0001
    }

    @Test
    fun getRateTest() {
        assertEquals(baseCurrency.rate, DEFAULT_RATE_VALUE, delta)
        assertEquals(currency.rate, DEFAULT_RATE_VALUE, delta)
    }

    @Test
    fun setRateTest() {
        baseCurrency.rate = 5.098
        assertEquals(baseCurrency.rate, 5.098, delta)
        currency.rate = 12.0
        currency.rate = baseCurrency.rate
        assertEquals(baseCurrency.rate, currency.rate, delta)
    }

    @Test
    fun getShortNameTest() {
        assertTrue(baseCurrency.getShortName().isNotEmpty())
        assertEquals(baseCurrency.getShortName(), Currency.AUD.name)
        assertNotEquals(baseCurrency.getShortName(), currency.getShortName())
    }

    @Test
    fun getImagePathTest() {
        assertTrue(baseCurrency.getImagePath().isNotEmpty())
        assertTrue(currency.getImagePath().isNotEmpty())
    }

    @Test
    fun equalsTest() {
        assertNotSame(currency, baseCurrency)
    }
}