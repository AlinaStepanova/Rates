package com.avs.rates.currency

abstract class BaseCurrency {

    var rate: Double = 1.0

    abstract fun getShortName(): String

    abstract fun getFullName(): String

    abstract fun getImagePath(): String
}

class EUR : BaseCurrency() {
    override fun getShortName() = "EUR"
    override fun getFullName() = "Euro"
    override fun getImagePath() = "https://cdn4.iconfinder.com/data/icons/world-flags-circular/1000/Flag_of_Europe_-_Circle-512.png"
}

class AUD : BaseCurrency() {
    override fun getShortName() = "AUD"
    override fun getFullName() = "Australian Dollar"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/australia/flag-square-500.png"
}

class BGN : BaseCurrency() {
    override fun getShortName() = "BGN"
    override fun getFullName() = "Bulgarian Lev"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/bulgaria/flag-square-500.png"
}

class BRL : BaseCurrency() {
    override fun getShortName() = "BRL"
    override fun getFullName() = "Brazilian Real"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/brazil/flag-square-500.png"
}

class CAD : BaseCurrency() {
    override fun getShortName() = "CAD"
    override fun getFullName() = "Canadian Dollar"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/canada/flag-square-500.png"
}

class CHF : BaseCurrency() {
    override fun getShortName() = "CHF"
    override fun getFullName() = "Swiss Franc"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/switzerland/flag-square-500.png"
}

class CNY : BaseCurrency() {
    override fun getShortName() = "CNY"
    override fun getFullName() = "Yuan"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/china/flag-square-500.png"
}

class CZK : BaseCurrency() {
    override fun getShortName() = "CZK"
    override fun getFullName() = "Czech Koruna"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/czech-republic/flag-square-500.png"
}

class DKK : BaseCurrency() {
    override fun getShortName() = "DKK"
    override fun getFullName() = "Danish Krone"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/denmark/flag-square-500.png"
}

class GBP : BaseCurrency() {
    override fun getShortName() = "GBP"
    override fun getFullName() = "Pound Sterling"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/united-kingdom/flag-square-500.png"
}

class HKD : BaseCurrency() {
    override fun getShortName() = "HKD"
    override fun getFullName() = "Hong Kong Dollar"
    override fun getImagePath() =
        "https://cdn1.iconfinder.com/data/icons/rounded-flat-country-flag-collection-1/2000/hk-01.png"
}

class HRK : BaseCurrency() {
    override fun getShortName() = "HRK"
    override fun getFullName() = "Croatian Kuna"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/croatia/flag-square-500.png"
}

class HUF : BaseCurrency() {
    override fun getShortName() = "HUF"
    override fun getFullName() = "Forint"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/hungary/flag-square-500.png"
}

class IDR : BaseCurrency() {
    override fun getShortName() = "IDR"
    override fun getFullName() = "Rupiah"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/indonesia/flag-square-500.png"
}

class ILS : BaseCurrency() {
    override fun getShortName() = "ILS"
    override fun getFullName() = "New Israeli Shekel"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/israel/flag-square-500.png"
}

class INR : BaseCurrency() {
    override fun getShortName() = "INR"
    override fun getFullName() = "Indian Rupee"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/india/flag-square-500.png"
}

class ISK : BaseCurrency() {
    override fun getShortName() = "ISK"
    override fun getFullName() = "Iceland Krona"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/iceland/flag-square-500.png"
}

class JPY : BaseCurrency() {
    override fun getShortName() = "JPY"
    override fun getFullName() = "Yen"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/japan/flag-square-500.png"
}

class KRW : BaseCurrency() {
    override fun getShortName() = "KRW"
    override fun getFullName() = "South Korean Won"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/south-korea/flag-square-500.png"
}

class MXN : BaseCurrency() {
    override fun getShortName() = "MXN"
    override fun getFullName() = "Mexican Peso"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/mexico/flag-square-500.png"
}

class MYR : BaseCurrency() {
    override fun getShortName() = "MYR"
    override fun getFullName() = "Malaysian Ringgit"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/malaysia/flag-square-500.png"
}

class NZD : BaseCurrency() {
    override fun getShortName() = "NZD"
    override fun getFullName() = "New Zealand Dollar"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/new-zealand/flag-square-500.png"
}

class PHP : BaseCurrency() {
    override fun getShortName() = "PHP"
    override fun getFullName() = "Philippine Peso"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/philippines/flag-square-500.png"
}

class PLN : BaseCurrency() {
    override fun getShortName() = "PLN"
    override fun getFullName() = "Zloty"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/poland/flag-square-500.png"
}

class RON : BaseCurrency() {
    override fun getShortName() = "RON"
    override fun getFullName() = "Leu"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/romania/flag-square-500.png"
}

class RUB : BaseCurrency() {
    override fun getShortName() = "RUB"
    override fun getFullName() = "Russian Ruble"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/russia/flag-square-500.png"
}

class SEK : BaseCurrency() {
    override fun getShortName() = "SEK"
    override fun getFullName() = "Swedish Krona"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/sweden/flag-square-500.png"
}

class SGD : BaseCurrency() {
    override fun getShortName() = "SGD"
    override fun getFullName() = "Singapore Dollar"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/singapore/flag-square-500.png"
}

class THB : BaseCurrency() {
    override fun getShortName() = "THB"
    override fun getFullName() = "Baht"
    override fun getImagePath() = "https://cdn.countryflags.com/thumbs/thailand/flag-square-500.png"
}

class USD : BaseCurrency() {
    override fun getShortName() = "USD"
    override fun getFullName() = "US Dollar"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/united-states-of-america/flag-square-500.png"
}

class ZAR : BaseCurrency() {
    override fun getShortName() = "ZAR"
    override fun getFullName() = "Rand"
    override fun getImagePath() =
        "https://cdn.countryflags.com/thumbs/south-africa/flag-square-500.png"
}