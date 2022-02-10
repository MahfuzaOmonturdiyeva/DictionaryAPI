package uz.gita.dictionaryapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import uz.gita.dictionaryapi.R
import uz.gita.dictionaryapi.holders.MeaningViewHolder
import uz.gita.dictionaryapi.modelForRetrofit.Meanings

class MeaningAdapter(private val context:Context) : ListAdapter<Meanings,
        MeaningViewHolder>
    (object : DiffUtil.ItemCallback<Meanings>() {
    override fun areItemsTheSame(oldItem: Meanings, newItem: Meanings): Boolean {
        return oldItem.partOfSpeech == newItem.partOfSpeech
    }

    override fun areContentsTheSame(oldItem: Meanings, newItem: Meanings): Boolean {
        return oldItem == newItem
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        return MeaningViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.meaning_list_items, parent, false)
                ,context)
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}