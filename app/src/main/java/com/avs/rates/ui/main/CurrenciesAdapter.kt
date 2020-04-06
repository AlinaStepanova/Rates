package com.avs.rates.ui.main

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avs.rates.R
import com.avs.rates.currency.BaseCurrency
import com.avs.rates.databinding.RateItemListBinding
import com.avs.rates.utils.CircleTransform
import com.avs.rates.utils.formatNumber
import com.avs.rates.utils.getSelectionIndex
import com.squareup.picasso.Picasso
import java.util.*

interface RatesListener {
    fun onListItemClick(newBaseCurrency: BaseCurrency)
    fun onEditTextChanged(stringValue: String)
}

class CurrenciesAdapter(
    currencyList: LinkedList<BaseCurrency>?,
    private val itemClickListener: RatesListener
) :
    RecyclerView.Adapter<CurrenciesAdapter.RatesViewHolder>() {

    private lateinit var context: Context
    private var textWatcher = getTextWatcher()
    private var currencies: LinkedList<BaseCurrency>? = currencyList

    /**
     * Updates currencies list
     * @param currencies - updated list of currencies
     */
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

    /**
     * Update first two top items after base currency has changed
     */
    fun updateTopItems() {
        if (itemCount != 0) notifyItemChanged(0)
        if (itemCount > 0) notifyItemChanged(1)
    }

    inner class RatesViewHolder(private val binding: RateItemListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private val editTextMaxLength : Int

        init {
            binding.root.setOnClickListener(this)
            binding.editText.setOnClickListener(this)
            editTextMaxLength = context.resources.getInteger(R.integer.edit_text_max_length)
        }

        fun bind(item: BaseCurrency, context: Context) {
            bindRate(formatNumber(item.rate))
            bindCurrencyFullName(item.getFullName().let { context.getString(it) })
            bindCurrencyShortName(item.getShortName())
            bindFlagImage(item.getImagePath())
        }

        /**
         * Sets short currency name to a corresponding text view
         * @param name - short name of currency
         */
        private fun bindCurrencyShortName(name: String?) {
            if (name != null) binding.tvCurrencyShortName.text = name
        }

        /**
         * Sets full currency name to a corresponding text view
         * @param name - full name of currency
         */
        private fun bindCurrencyFullName(name: String?) {
            if (name != null) binding.tvCurrencyFullName.text = name
        }

        /**
         * Disables all the edit texts in the recycler view except for the top one.
         * Sets rate value.
         * @param value - a rate value
         */
        private fun bindRate(value: String) {
            binding.editText.removeTextChangedListener(textWatcher)
            binding.editText.isEnabled = adapterPosition == 0
            binding.editText.setText(value)
            if (adapterPosition == 0) {
                binding.editText.addTextChangedListener(textWatcher)
                itemClickListener.onEditTextChanged(binding.editText.text.toString())
                binding.editText.requestFocus()
                binding.editText.setSelection(getSelectionIndex(value.length, editTextMaxLength))
            }
        }

        /**
         * Loads image url into a image view using custom circle transformation
         * @param imageUrl - country flag url
         */
        private fun bindFlagImage(imageUrl: String?) {
            Picasso.get()
                .load(imageUrl)
                .fit()
                .transform(CircleTransform())
                .into(binding.ivFlagImage)
        }

        /**
         * Changes base currency to appear on the top, if not a first item was clicked
         * @param view - recycler view item
         */
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

    /**
     * Listens to a rate changing of a base currency
     * @return
     */
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