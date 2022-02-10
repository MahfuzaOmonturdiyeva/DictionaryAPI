package uz.gita.dictionaryapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import uz.gita.dictionaryapi.R
import uz.gita.dictionaryapi.holders.DefinitionViewHolder
import uz.gita.dictionaryapi.modelForRetrofit.Definitions

class DefinitionAdapter : ListAdapter<Definitions,
        DefinitionViewHolder>
    (object : DiffUtil.ItemCallback<Definitions>() {
    override fun areItemsTheSame(oldItem: Definitions, newItem: Definitions): Boolean {
        return oldItem.definition == newItem.definition
    }

    override fun areContentsTheSame(oldItem: Definitions, newItem: Definitions): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        return DefinitionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.definitions_list_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}