package com.avs.rates.utils

import org.junit.Test

import org.junit.Assert.*

class FormatKtTest {

    @Test
    fun formatNumberTest() {
        var number = 12.0
        assertEquals(formatNumber(number), "12")
        number = 12.100
        assertEquals(formatNumber(number), "12.1")
        number = -1.0
        assertEquals(formatNumber(number), "")
        number = 0.0
        assertEquals(formatNumber(number), "0")
        number = 12345.333
        assertEquals(formatNumber(number), "12345.333")
    }

    @Test
    fun roundTest() {
        val delta = 0.001
        var value = 1.2899999
        assertEquals(round(value, 1), 1.3, delta)
        assertEquals(round(value, 2), 1.29, delta)
        assertEquals(round(value, 3), 1.29, delta)
        value = 0.12378
        assertEquals(round(value, 1), 0.1, delta)
        assertEquals(round(value, 2), 0.12, delta)
        assertEquals(round(value, 3), 0.124, delta)
        value = 1234.01
        assertEquals(round(value, 1), 1234.0, delta)
        assertEquals(round(value, 2), 1234.01, delta)
        assertEquals(round(value, 3), 1234.01, delta)
    }

    @Test
    fun getSelectionIndexTest() {
        var valueLength = 10
        val maxLength = 18
        assertEquals(getSelectionIndex(valueLength, maxLength), valueLength)
        valueLength = 22
        assertEquals(getSelectionIndex(valueLength, maxLength), maxLength)
        valueLength = 18
        assertEquals(getSelectionIndex(valueLength, maxLength), valueLength)
    }
}