package com.avs.rates.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.avs.rates.di.ViewModelFactory

/**
 * A class which serve as a parent to all activities classes in the app.
 * Contains common data and methods
 */
abstract class BaseActivity : AppCompatActivity() {

    inline fun <reified T : ViewModel> ViewModelFactory<T>.get(): T =
        ViewModelProviders.of(this@BaseActivity, this)[T::class.java]
}