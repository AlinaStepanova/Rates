package com.avs.rates.ui.main

import androidx.lifecycle.ViewModel
import com.avs.rates.network.RatesServerApi
import javax.inject.Inject

class MainViewModel @Inject constructor(private val ratesServerApi: RatesServerApi): ViewModel() {

}