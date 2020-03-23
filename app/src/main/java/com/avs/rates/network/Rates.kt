package com.avs.rates.network

import java.io.Serializable

data class Rates(val baseCurrency: String, val rates : HashMap<String, Double>) : Serializable