package com.avs.rates.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.avs.rates.R
import com.avs.rates.currency.BaseCurrency
import com.avs.rates.databinding.RateItemListBinding
import com.avs.rates.utils.CircleTransform
import com.squareup.picasso.Picasso

interface ListItemClickListener {
    fun onListItemClick(
        newBaseCurrency: BaseCurrency
    )
}

class RatesAdapter(val rates: ArrayList<BaseCurrency>?, private val itemClickListener: ListItemClickListener) :
    RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    private lateinit var context: Context

    var currencies: ArrayList<BaseCurrency>? = rates
        set(value) {
            field = value
            notifyItemRangeChanged(1, (currencies?.size?.minus(1) ?: 0))
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

    inner class RatesViewHolder(private val binding: RateItemListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.editText.setOnClickListener(this)
            binding.editText.clearFocus()
        }

        fun bind(item: BaseCurrency, context: Context) {
            bindRate(item.rate.toString())
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
            binding.editText.isEnabled = adapterPosition == 0
            binding.editText.setText(value)
            //todo make formatting
            if (value == "0" || value == "0.0") {
                binding.editText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            } else {
                binding.editText.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
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
                itemClickListener.onListItemClick(currencies!![clickedPosition])
                notifyItemMoved(clickedPosition, 0)
                bindViewHolder(this, 0)
                bindViewHolder(this, clickedPosition)
            }
            if (clickedPosition == 0 && !currencies.isNullOrEmpty() && view != null) {
                if (view.id == binding.editText.id && clickedPosition != 0) {

                }
            }
        }
    }
}