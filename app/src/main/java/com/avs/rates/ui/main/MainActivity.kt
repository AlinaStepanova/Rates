package com.avs.rates.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.avs.rates.R
import com.avs.rates.RatesApplication
import com.avs.rates.currency.BaseCurrency
import com.avs.rates.databinding.ActivityMainBinding
import com.avs.rates.di.ViewModelFactory
import com.avs.rates.ui.BaseActivity
import java.util.*
import javax.inject.Inject


class MainActivity : BaseActivity(), RatesListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var defaultItemAnimator: DefaultItemAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as RatesApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = viewModelFactory.get()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this
        defaultItemAnimator = DefaultItemAnimator()
        val adapter = CurrenciesAdapter(null, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null

        viewModel.conversion.observe(this, Observer {
            it?.let {
                binding.recyclerView.itemAnimator = null
                adapter.setCurrencies(LinkedList(it))
            }
        })

        viewModel.updateBaseCurrencyEvent.observe(this, Observer {
            it?.let {
                binding.recyclerView.itemAnimator = null
                adapter.updateTopItems()
            }
        })
    }

    override fun onListItemClick(newBaseCurrency: BaseCurrency) {
        binding.recyclerView.itemAnimator = defaultItemAnimator
        viewModel.updateRates(newBaseCurrency)
        binding.recyclerView.layoutManager?.scrollToPosition(0)
    }

    override fun onEditTextChanged(text: String) {
        viewModel.updateBaseCurrencyValue(text)
    }
}
