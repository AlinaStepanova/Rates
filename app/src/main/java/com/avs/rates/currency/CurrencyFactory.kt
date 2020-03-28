package com.avs.rates.currency

fun getBaseCurrency(baseCurrency: String): BaseCurrency {
    when (baseCurrency) {
        Currency.AUD.name -> {
            return AUD()
        }
        Currency.BGN.name -> {
            return BGN()
        }
        Currency.BRL.name -> {
            return BRL()
        }
        Currency.CAD.name -> {
            return CAD()
        }
        Currency.CHF.name -> {
            return CHF()
        }
        Currency.CNY.name -> {
            return CNY()
        }
        Currency.CZK.name -> {
            return CZK()
        }
        Currency.DKK.name -> {
            return DKK()
        }
        Currency.GBP.name -> {
            return GBP()
        }
        Currency.HKD.name -> {
            return HKD()
        }
        Currency.HRK.name -> {
            return HRK()
        }
        Currency.HUF.name -> {
            return HUF()
        }
        Currency.IDR.name -> {
            return IDR()
        }
        Currency.ILS.name -> {
            return ILS()
        }
        Currency.INR.name -> {
            return INR()
        }
        Currency.ISK.name -> {
            return ISK()
        }
        Currency.JPY.name -> {
            return JPY()
        }
        Currency.KRW.name -> {
            return KRW()
        }
        Currency.MXN.name -> {
            return MXN()
        }
        Currency.MYR.name -> {
            return MYR()
        }
        Currency.NOK.name -> {
            return NOK()
        }
        Currency.NZD.name -> {
            return NZD()
        }
        Currency.PHP.name -> {
            return PHP()
        }
        Currency.PLN.name -> {
            return PLN()
        }
        Currency.RON.name -> {
            return RON()
        }
        Currency.RUB.name -> {
            return RUB()
        }
        Currency.SEK.name -> {
            return SEK()
        }
        Currency.SGD.name -> {
            return SGD()
        }
        Currency.THB.name -> {
            return THB()
        }
        Currency.USD.name -> {
            return USD()
        }
        Currency.ZAR.name -> {
            return ZAR()
        }
    }
    return EUR()
}