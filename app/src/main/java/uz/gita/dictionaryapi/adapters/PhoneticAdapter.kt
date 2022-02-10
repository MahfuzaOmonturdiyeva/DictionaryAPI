package uz.gita.dictionaryapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import uz.gita.dictionaryapi.R
import uz.gita.dictionaryapi.holders.PhoneticViewHolder
import uz.gita.dictionaryapi.modelForRetrofit.Phonetics

class PhoneticAdapter(private val context: Context) : ListAdapter<Phonetics,
        PhoneticViewHolder>
    (object : DiffUtil.ItemCallback<Phonetics>() {
    override fun areItemsTheSame(oldItem: Phonetics, newItem: Phonetics): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: Phonetics, newItem: Phonetics): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneticViewHolder {
        return PhoneticViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.phonetics_list_item, parent, false), context
        )
    }

    override fun onBindViewHolder(holder: PhoneticViewHolder, position: Int) {
        if (getItem(position).text != null) {
            holder.phonetics.visibility=View.VISIBLE
            holder.bind(getItem(position))
        }else holder.phonetics.visibility=View.INVISIBLE
    }
}