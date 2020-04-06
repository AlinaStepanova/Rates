package com.avs.rates.currency

import com.avs.rates.DEFAULT_RATE_VALUE
import com.avs.rates.R

/**
 * Base class for every currency. Holds common data and methods.
 */
abstract class BaseCurrency {

    var rate: Double = DEFAULT_RATE_VALUE

    abstract fun getShortName(): String

    abstract fun getFullName(): Int

    abstract fun getImagePath(): String

    override fun equals(other: Any?): Boolean {
        return (other is BaseCurrency && other.getShortName() == getShortName())
    }

    override fun hashCode(): Int {
        return rate.hashCode() * 17 * getShortName().hashCode()
    }

    override fun toString(): String {
        return "${getShortName()} $rate"
    }
}

class EUR : BaseCurrency() {
    override fun getShortName() = Currency.EUR.name
    override fun getFullName() = R.string.currency_euro_title
    override fun getImagePath() =
        "https://cdn4.iconfinder.com/data/icons/world-flags-circular/1000/Flag_of_Europe_-_Circle-512.png"
}

class AUD : BaseCurrency() {
    override fun getShortName() = Currency.AUD.name
    override fun getFullName() = R.string.currency_australian_dollar_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/australia/flag-square-500.png"
}

class BGN : BaseCurrency() {
    override fun getShortName() = Currency.BGN.name
    override fun getFullName() = R.string.currency_bulgarian_lev_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/bulgaria/flag-square-500.png"
}

class BRL : BaseCurrency() {
    override fun getShortName() = Currency.BRL.name
    override fun getFullName() = R.string.currency_brazilian_real_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/brazil/flag-square-500.png"
}

class CAD : BaseCurrency() {
    override fun getShortName() = Currency.CAD.name
    override fun getFullName() = R.string.currency_canadian_dollar_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/canada/flag-square-500.png"
}

class CHF : BaseCurrency() {
    override fun getShortName() = Currency.CHF.name
    override fun getFullName() = R.string.currency_swiss_franc_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/switzerland/flag-square-500.png"
}

class CNY : BaseCurrency() {
    override fun getShortName() = Currency.CNY.name
    override fun getFullName() = R.string.currency_yuan_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/china/flag-square-500.png"
}

class CZK : BaseCurrency() {
    override fun getShortName() = Currency.CZK.name
    override fun getFullName() = R.string.currency_czech_koruna_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/czech-republic/flag-square-500.png"
}

class DKK : BaseCurrency() {
    override fun getShortName() = Currency.DKK.name
    override fun getFullName() = R.string.currency_danish_krone_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/denmark/flag-square-500.png"
}

class GBP : BaseCurrency() {
    override fun getShortName() = Currency.GBP.name
    override fun getFullName() = R.string.currency_pound_sterling_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/united-kingdom/flag-square-500.png"
}

class HKD : BaseCurrency() {
    override fun getShortName() = Currency.HKD.name
    override fun getFullName() = R.string.currency_hong_kong_dollar_title
    override fun getImagePath() =
        "https://cdn1.iconfinder.com/data/icons/rounded-flat-country-flag-collection-1/2000/hk-01.png"
}

class HRK : BaseCurrency() {
    override fun getShortName() = Currency.HRK.name
    override fun getFullName() = R.string.currency_croatian_kuna_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/croatia/flag-square-500.png"
}

class HUF : BaseCurrency() {
    override fun getShortName() = Currency.HUF.name
    override fun getFullName() = R.string.currency_forint_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/hungary/flag-square-500.png"
}

class IDR : BaseCurrency() {
    override fun getShortName() = Currency.IDR.name
    override fun getFullName() = R.string.currency_rupiah_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/indonesia/flag-square-500.png"
}

class ILS : BaseCurrency() {
    override fun getShortName() = Currency.ILS.name
    override fun getFullName() = R.string.currency_new_israeli_shekel_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/israel/flag-square-500.png"
}

class INR : BaseCurrency() {
    override fun getShortName() = Currency.INR.name
    override fun getFullName() = R.string.currency_indian_rupee_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/india/flag-square-500.png"
}

class ISK : BaseCurrency() {
    override fun getShortName() = Currency.ISK.name
    override fun getFullName() = R.string.currency_iceland_krona_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/iceland/flag-square-500.png"
}

class JPY : BaseCurrency() {
    override fun getShortName() = Currency.JPY.name
    override fun getFullName() = R.string.currency_yen_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/japan/flag-square-500.png"
}

class KRW : BaseCurrency() {
    override fun getShortName() = Currency.KRW.name
    override fun getFullName() = R.string.currency_south_korean_won_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/south-korea/flag-square-500.png"
}

class MXN : BaseCurrency() {
    override fun getShortName() = Currency.MXN.name
    override fun getFullName() = R.string.currency_mexican_peso_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/mexico/flag-square-500.png"
}

class MYR : BaseCurrency() {
    override fun getShortName() = Currency.MYR.name
    override fun getFullName() = R.string.currency_malaysian_ringgit_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/malaysia/flag-square-500.png"
}

class NOK : BaseCurrency() {
    override fun getShortName() = Currency.NOK.name
    override fun getFullName() = R.string.currency_norwegian_krone_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/norway/flag-square-500.png"
}

class NZD : BaseCurrency() {
    override fun getShortName() = Currency.NZD.name
    override fun getFullName() = R.string.currency_new_zealand_dollar_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/new-zealand/flag-square-500.png"
}

class PHP : BaseCurrency() {
    override fun getShortName() = Currency.PHP.name
    override fun getFullName() = R.string.currency_philippine_peso_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/philippines/flag-square-500.png"
}

class PLN : BaseCurrency() {
    override fun getShortName() = Currency.PLN.name
    override fun getFullName() = R.string.currency_zloty_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/poland/flag-square-500.png"
}

class RON : BaseCurrency() {
    override fun getShortName() = Currency.RON.name
    override fun getFullName() = R.string.currency_leu_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/romania/flag-square-500.png"
}

class RUB : BaseCurrency() {
    override fun getShortName() = Currency.RUB.name
    override fun getFullName() = R.string.currency_russian_ruble_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/russia/flag-square-500.png"
}

class SEK : BaseCurrency() {
    override fun getShortName() = Currency.SEK.name
    override fun getFullName() = R.string.currency_swedish_krona_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/sweden/flag-square-500.png"
}

class SGD : BaseCurrency() {
    override fun getShortName() = Currency.SGD.name
    override fun getFullName() = R.string.currency_singapore_dollar_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/singapore/flag-square-500.png"
}

class THB : BaseCurrency() {
    override fun getShortName() = Currency.THB.name
    override fun getFullName() = R.string.currency_baht_title
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/thailand/flag-square-500.png"
}

class USD : BaseCurrency() {
    override fun getShortName() = Currency.USD.name
    override fun getFullName() = R.string.currency_us_dollar_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/united-states-of-america/flag-square-500.png"
}

class ZAR : BaseCurrency() {
    override fun getShortName() = Currency.ZAR.name
    override fun getFullName() = R.string.currency_rand_title
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/south-africa/flag-square-500.png"
}