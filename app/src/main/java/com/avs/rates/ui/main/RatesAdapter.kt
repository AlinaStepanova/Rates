package com.avs.rates.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avs.rates.utils.CircleTransform
import com.avs.rates.R
import com.avs.rates.currency.BaseCurrency
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

interface ListItemClickListener {
    fun onListItemClick(clickedItemIndex: Int)
}

class RatesAdapter(rates: ArrayList<BaseCurrency>?) :
    RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    private lateinit var context: Context

    var currencies: ArrayList<BaseCurrency>? = rates
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        context = parent.context
        return RatesViewHolder.from(parent, context)
    }

    override fun getItemCount() = currencies?.size ?: 0

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val item = currencies?.get(position)
        if (item != null) {
            holder.bind(item, context)
        }
    }

    class RatesViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var currencyShortName: TextView = view.findViewById(R.id.tvCurrencyShortName)
        private var currencyFullName: TextView = view.findViewById(R.id.tvCurrencyFullName)
        private var flagImage: ImageView = view.findViewById(R.id.ivFlagImage)
        private var rate: EditText = view.findViewById(R.id.editText)

        init {
            view.setOnClickListener(this)
        }

        fun bind(item: BaseCurrency, context: Context) {
            bindRate(item.rate.toString())
            bindCurrencyFullName(item.getFullName().let { context.getString(it) })
            bindCurrencyShortName(item.getShortName())
            bindFlagImage(item.getImagePath())
        }

        private fun bindCurrencyShortName(name: String?) {
            if (name != null) currencyShortName.text = name
        }

        private fun bindCurrencyFullName(name: String?) {
            if (name != null) currencyFullName.text = name
        }

        private fun bindRate(value: String) {
            rate.setText(value)
        }

        private fun bindFlagImage(imageUrl: String?) {
            Picasso.get()
                .load(imageUrl)
                .fit()
                .transform(CircleTransform())
                .into(flagImage)
        }

        override fun onClick(view: View) {
            val clickedPosition = adapterPosition
            //onClickListener.onListItemClick(clickedPosition)
        }

        companion object {
            fun from(parent: ViewGroup, context: Context): RatesViewHolder {
                val layoutForListItems: Int = R.layout.rate_item_list
                val inflater = LayoutInflater.from(context)
                val view = inflater.inflate(layoutForListItems, parent, false)
                return RatesViewHolder(view)
            }
        }

    }

}