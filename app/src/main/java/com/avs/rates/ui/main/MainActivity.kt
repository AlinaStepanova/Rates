package com.avs.rates.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.avs.rates.R
import com.avs.rates.RatesApplication
import com.avs.rates.databinding.ActivityMainBinding
import com.avs.rates.di.ViewModelFactory
import com.avs.rates.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as RatesApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = viewModelFactory.get()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = RatesAdapter(null)
        binding.recyclerView.adapter = adapter

        viewModel.conversion.observe(this, Observer {
            it?.let {
                adapter.currencies = ArrayList(it)
            }
        })
    }
}
