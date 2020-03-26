package com.avs.rates.network.dto

import java.io.Serializable

data class Conversion(val baseCurrency: String, val rates : Rates) : Serializable