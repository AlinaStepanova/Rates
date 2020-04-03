package com.avs.rates.utils

import java.math.BigDecimal

/**
 * @param number - a rate value
 * @return - formatted rate value to be shown in the recycler view
 */
fun doubleToString(number: Double) : String {
    if (number < 0) {
        return ""
    }
    var result = round(number, 3).toString()
    if (result.contains('.') && result.endsWith('0')) {
        var i = result.length - 1;
        while (result[i] == '0') {
            i -= 1;
        }
        result = result.substring(0, i + 1);
    }
    if (result.endsWith('.')) {
        result = result.substring(0, result.length - 1);
    }
    return result
}

/**
 *
 * @param value - a rate value
 * @param decimalPlace - number of decimal places
 * @return a rounded rate value
 */
fun round(value: Double, decimalPlace: Int): Double {
    var number = BigDecimal(value.toString())
    number = number.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP)
    return number.toDouble()
}