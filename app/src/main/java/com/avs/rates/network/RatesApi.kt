package com.avs.rates.network

import com.avs.rates.network.dto.Rates
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {

    @GET("latest")
    fun getLatestRatesByBase(@Query("base") baseCurrency: String): Single<Response<Rates>>
}