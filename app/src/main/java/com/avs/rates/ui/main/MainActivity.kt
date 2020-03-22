package com.avs.rates.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.avs.rates.R
import com.avs.rates.ui.main.MainViewModel
import com.avs.rates.databinding.ActivityMainBinding
import com.avs.rates.di.ViewModelFactory
import com.avs.rates.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelFactory.get()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = viewModel
    }
}
