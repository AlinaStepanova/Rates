package com.avs.rates.currency

fun getBaseCurrency(baseCurrency: String): BaseCurrency {
    when (baseCurrency) {
        //todo create enum which holds strings
        "AUD" -> {
            return AUD()
        }
        "BGN" -> {
            return BGN()
        }
        "BRL" -> {
            return BRL()
        }
        "CAD" -> {
            return CAD()
        }
        "CHF" -> {
            return CHF()
        }
        "CNY" -> {
            return CNY()
        }
        "CZK" -> {
            return CZK()
        }
        "DKK" -> {
            return DKK()
        }
        "GBP" -> {
            return GBP()
        }
        "HKD" -> {
            return HKD()
        }
        "HRK" -> {
            return HRK()
        }
        "HUF" -> {
            return HUF()
        }
        "IDR" -> {
            return IDR()
        }
        "ILS" -> {
            return ILS()
        }
        "INR" -> {
            return INR()
        }
        "ISK" -> {
            return ISK()
        }
        "JPY" -> {
            return JPY()
        }
        "KRW" -> {
            return KRW()
        }
        "MXN" -> {
            return MXN()
        }
        "MYR" -> {
            return MYR()
        }
        "NZD" -> {
            return NZD()
        }
        "PHP" -> {
            return PHP()
        }
        "PLN" -> {
            return PLN()
        }
        "RON" -> {
            return RON()
        }
        "RUB" -> {
            return NZD()
        }
        "SEK" -> {
            return SEK()
        }
        "SGD" -> {
            return SGD()
        }
        "THB" -> {
            return THB()
        }
        "USD" -> {
            return USD()
        }
        "ZAR" -> {
            return ZAR()
        }
    }
    return EUR()
}