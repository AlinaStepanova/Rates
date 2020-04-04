package com.avs.rates.currency

import org.junit.Test

import org.junit.Assert.*

class CurrencyFactoryKtTest {

    @Test
    fun getBaseCurrency() {
        assertEquals(getBaseCurrency(Currency.BRL.name), BRL())
        assertEquals(getBaseCurrency("AUD"), AUD())
        assertEquals(getBaseCurrency(""), EUR())
        assertEquals(getBaseCurrency("0.0"), EUR())
    }
}