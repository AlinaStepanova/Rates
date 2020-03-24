package com.avs.rates.network.dto

import java.io.Serializable

data class Rates(val baseCurrency: String, val rates : Rate) : Serializable