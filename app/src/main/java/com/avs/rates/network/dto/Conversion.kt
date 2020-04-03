package com.avs.rates.network.dto

import java.io.Serializable

/**
 * A data class which contains a server request response
 * @property baseCurrency - a current base currency
 * @property rates - known rates
 */
data class Conversion(val baseCurrency: String, val rates : Rates) : Serializable