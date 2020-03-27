package com.avs.rates.currency

abstract class BaseCurrency() {

    var rate: Double = 0.0

    fun setValue(newValue : Double) {
        this.rate = newValue
    }

    abstract fun getShortName(): String

    abstract fun getFullName(): String
    fun getImagePath() = "https://upload.wikimedia.org/wikipedia/en/a/a4/Flag_of_the_United_States.svg"
}

class EUR : BaseCurrency() {

    override fun getShortName() = "EUR"
    override fun getFullName() = "EURO"
}

class AUD : BaseCurrency() {
    override fun getShortName() = "AUD"
    override fun getFullName() = "Australian Dollar"
}

class BGN : BaseCurrency() {
    override fun getShortName() = "BGN"
    override fun getFullName() = "Bulgarian Lev"
}

class BRL : BaseCurrency() {
    override fun getShortName() = "BRL"
    override fun getFullName() = "Brazilian Real"
}

class CAD : BaseCurrency() {
    override fun getShortName() = "CAD"
    override fun getFullName() = "Canadian Dollar"
}

class CHF : BaseCurrency() {
    override fun getShortName() = "CHF"
    override fun getFullName() = "Swiss Franc"
}

class CNY : BaseCurrency() {
    override fun getShortName() = "CNY"
    override fun getFullName() = "Yuan"
}

class CZK : BaseCurrency() {
    override fun getShortName() = "CZK"
    override fun getFullName() = "Czech Koruna"
}

class DKK : BaseCurrency() {
    override fun getShortName() = "DKK"
    override fun getFullName() = "Danish Krone"
}

class GBP : BaseCurrency() {
    override fun getShortName() = "GBP"
    override fun getFullName() = "Pound Sterling"
}

class HKD : BaseCurrency() {
    override fun getShortName() = "HKD"
    override fun getFullName() = "Hong Kong Dollar"
}

class HRK : BaseCurrency() {
    override fun getShortName() = "HRK"
    override fun getFullName() = "Croatian Kuna"
}

class HUF : BaseCurrency() {
    override fun getShortName() = "HUF"
    override fun getFullName() = "Forint"
}

class IDR : BaseCurrency() {
    override fun getShortName() = "IDR"
    override fun getFullName() = "Rupiah"
}

class ILS : BaseCurrency() {
    override fun getShortName() = "ILS"
    override fun getFullName() = "New Israeli Shekel"
}

class INR : BaseCurrency() {
    override fun getShortName() = "INR"
    override fun getFullName() = "Indian Rupee"
}

class ISK : BaseCurrency() {
    override fun getShortName() = "ISK"
    override fun getFullName() = "Iceland Krona"
}

class JPY : BaseCurrency() {
    override fun getShortName() = "JPY"
    override fun getFullName() = "Yen"
}

class KRW : BaseCurrency() {
    override fun getShortName() = "KRW"
    override fun getFullName() = "South Korean Won"
}

class MXN : BaseCurrency() {
    override fun getShortName() = "MXN"
    override fun getFullName() = "Mexican Peso"
}

class MYR : BaseCurrency() {
    override fun getShortName() = "MYR"
    override fun getFullName() = "Malaysian Ringgit"
}

class NZD : BaseCurrency() {
    override fun getShortName() = "NZD"
    override fun getFullName() = "New Zealand Dollar"
}

class PHP : BaseCurrency() {
    override fun getShortName() = "PHP"
    override fun getFullName() = "Philippine Peso"
}

class PLN : BaseCurrency() {
    override fun getShortName() = "PLN"
    override fun getFullName() = "Zloty"
}

class RON : BaseCurrency() {
    override fun getShortName() = "RON"
    override fun getFullName() = "Leu"
}

class RUB : BaseCurrency() {
    override fun getShortName() = "RUB"
    override fun getFullName() = "Russian Ruble"
}

class SEK : BaseCurrency() {
    override fun getShortName() = "SEK"
    override fun getFullName() = "Swedish Krona"
}

class SGD : BaseCurrency() {
    override fun getShortName() = "SGD"
    override fun getFullName() = "Singapore Dollar"
}

class THB : BaseCurrency() {
    override fun getShortName() = "THB"
    override fun getFullName() = "Baht"
}

class USD : BaseCurrency() {
    override fun getShortName() = "USD"
    override fun getFullName() = "US Dollar"
}

class ZAR : BaseCurrency() {
    override fun getShortName() = "ZAR"
    override fun getFullName() = "Rand"
}