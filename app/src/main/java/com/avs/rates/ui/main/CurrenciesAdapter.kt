package com.avs.rates.ui.main

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avs.rates.currency.BaseCurrency
import com.avs.rates.databinding.RateItemListBinding
import com.avs.rates.utils.CircleTransform
import com.avs.rates.utils.formatNumber
import com.squareup.picasso.Picasso
import java.util.*

interface RatesListener {
    fun onListItemClick(newBaseCurrency: BaseCurrency)
    fun onEditTextChanged(text: String)
}

class CurrenciesAdapter(
    currencyList: LinkedList<BaseCurrency>?,
    private val itemClickListener: RatesListener
) :
    RecyclerView.Adapter<CurrenciesAdapter.RatesViewHolder>() {

    private lateinit var context: Context
    private var textWatcher = getTextWatcher()
    private var currencies: LinkedList<BaseCurrency>? = currencyList

    fun setCurrencies(currencies: LinkedList<BaseCurrency>?) {
        this.currencies = currencies
        notifyItemRangeChanged(1, (this.currencies?.size?.minus(1) ?: 0))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = RateItemListBinding.inflate(inflater, parent, false)
        return RatesViewHolder(binding)
    }

    override fun getItemCount() = currencies?.size ?: 0

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val item = currencies?.get(position)
        if (item != null) {
            holder.bind(item, context)
        }
    }

    fun updateTopItems() {
        if (itemCount != 0) notifyItemChanged(0)
        if (itemCount > 0) notifyItemChanged(1)
    }

    inner class RatesViewHolder(private val binding: RateItemListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.editText.setOnClickListener(this)
        }

        fun bind(item: BaseCurrency, context: Context) {
            bindRate(formatNumber(item.rate))
            bindCurrencyFullName(item.getFullName().let { context.getString(it) })
            bindCurrencyShortName(item.getShortName())
            bindFlagImage(item.getImagePath())
        }

        private fun bindCurrencyShortName(name: String?) {
            if (name != null) binding.tvCurrencyShortName.text = name
        }

        private fun bindCurrencyFullName(name: String?) {
            if (name != null) binding.tvCurrencyFullName.text = name
        }

        private fun bindRate(value: String) {
            binding.editText.removeTextChangedListener(textWatcher)
            binding.editText.isEnabled = adapterPosition == 0
            binding.editText.setText(value)
            if (adapterPosition == 0) {
                binding.editText.addTextChangedListener(textWatcher)
                itemClickListener.onEditTextChanged(binding.editText.text.toString())
                binding.editText.requestFocus()
            }
        }

        private fun bindFlagImage(imageUrl: String?) {
            Picasso.get()
                .load(imageUrl)
                .fit()
                .transform(CircleTransform())
                .into(binding.ivFlagImage)
        }

        override fun onClick(view: View?) {
            val clickedPosition = adapterPosition
            if (clickedPosition != 0 && !currencies.isNullOrEmpty()) {
                val clickedCurrency = currencies?.get(clickedPosition)
                clickedCurrency?.let { itemClickListener.onListItemClick(it) }
                clickedCurrency?.let {
                    currencies?.removeAt(clickedPosition)
                    currencies?.addFirst(clickedCurrency)
                }
                notifyItemMoved(clickedPosition, 0)
            }

        }
    }

    private fun getTextWatcher(): TextWatcher {
        return object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (charSequence != null) {
                    itemClickListener.onEditTextChanged(charSequence.toString())
                }
            }
        }
    }
}