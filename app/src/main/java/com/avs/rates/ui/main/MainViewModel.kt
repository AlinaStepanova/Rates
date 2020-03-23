package com.avs.rates.ui.main

import androidx.lifecycle.ViewModel
import com.avs.rates.network.RatesApi
import javax.inject.Inject

class MainViewModel @Inject constructor(private val apiClient: RatesApi): ViewModel() {

}