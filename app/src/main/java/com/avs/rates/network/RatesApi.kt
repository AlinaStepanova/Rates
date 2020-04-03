package com.avs.rates.network

import com.avs.rates.network.dto.Conversion
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * An interface used by Retrofit
 */
interface RatesApi {

    @GET("api/android/latest")
    fun getLatestRatesByBase(@Query("base") baseCurrency: String): Single<Response<Conversion>>
}