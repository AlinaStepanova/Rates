package com.avs.rates.ui.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avs.rates.currency.BaseCurrency
import com.avs.rates.databinding.RateItemListBinding
import com.avs.rates.utils.CircleTransform
import com.squareup.picasso.Picasso

interface ListItemClickListener {
    fun onListItemClick(
        newBaseCurrency: BaseCurrency
    )
}

class RatesAdapter(rates: ArrayList<BaseCurrency>?, private val itemClickListener: ListItemClickListener) :
    RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    private lateinit var context: Context

    var currencies: ArrayList<BaseCurrency>? = rates
        set(value) {
            field = value
            notifyDataSetChanged()
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
            binding.editText.setText(value)
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
            }
        }

    }
}