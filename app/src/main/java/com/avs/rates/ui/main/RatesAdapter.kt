package com.avs.rates.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avs.rates.utils.CircleTransform
import com.avs.rates.R
import com.avs.rates.network.dto.Conversion
import com.squareup.picasso.Picasso

interface ListItemClickListener {
    fun onListItemClick(clickedItemIndex: Int)
}

class RatesAdapter(rates: Conversion?) : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    var rates: Conversion? = rates
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val context = parent.context
        val layoutForListItems: Int = R.layout.rate_item_list
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutForListItems, parent, false)

        return RatesViewHolder(view)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder. bindRate(rates?.rates?.AUD.toString())
    }

    inner class RatesViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var currencyShortName: TextView = view.findViewById(R.id.tvCurrencyShortName)
        private var currencyFullName: TextView = view.findViewById(R.id.tvCurrencyFullName)
        private var flagImage: ImageView = view.findViewById(R.id.ivFlagImage)
        private var rate: EditText = view.findViewById(R.id.editText)

        init {
            view.setOnClickListener(this)
        }

        fun bindCurrencyShortName(name: String) {
            currencyShortName.text = name
        }

        fun bindCurrencyFullName(name: String) {
            currencyFullName.text = name
        }

        fun bindRate(value: String) {
            rate.setText(value)
        }

        fun bindFlagImage(imageUrl: String) {
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

    }

}