package com.avs.rates.utils

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class RxBus {

    private val relay = PublishRelay.create<Any>()
    fun send(o: Any) {
        relay.accept(o)
    }

    val events: Observable<Any>
        get() = relay.observeOn(AndroidSchedulers.mainThread())
}