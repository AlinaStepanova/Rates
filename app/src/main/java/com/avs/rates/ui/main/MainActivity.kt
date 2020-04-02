package com.avs.rates.ui.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.avs.rates.R
import com.avs.rates.RatesApplication
import com.avs.rates.currency.BaseCurrency
import com.avs.rates.databinding.ActivityMainBinding
import com.avs.rates.di.ViewModelFactory
import com.avs.rates.network.ErrorType
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

        viewModel.networkErrorEvent.observe(this, Observer {
            handleErrorItemsAppearance(it)
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

    private fun handleErrorItemsAppearance(it: ErrorType?) {
        when (it) {
            null -> {
                binding.ivCloud.visibility = View.INVISIBLE
                binding.ivMessage.visibility = View.INVISIBLE
            }
            ErrorType.NETWORK -> {
                binding.ivCloud.visibility = View.VISIBLE
                binding.ivMessage.visibility = View.VISIBLE
                binding.ivMessage.text = resources.getString(R.string.error_network_connection_text)
            }
            ErrorType.SERVER -> {
                binding.ivCloud.visibility = View.INVISIBLE
                binding.ivMessage.visibility = View.VISIBLE
                binding.ivMessage.text = resources.getString(R.string.error_server_connection_text)
            }
        }
    }
}
