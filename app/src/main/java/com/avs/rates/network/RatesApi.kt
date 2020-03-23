package com.avs.rates.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {

    @GET("latest?base=")
    fun getLatestRatesByBase(@Query("base") baseCurrency: String): Single<Response<Rates>>
}