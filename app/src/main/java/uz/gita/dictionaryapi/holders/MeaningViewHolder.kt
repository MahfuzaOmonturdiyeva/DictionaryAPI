package uz.gita.dictionaryapi.holders

import android.content.Context
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dictionaryapi.adapters.DefinitionAdapter
import uz.gita.dictionaryapi.databinding.MeaningListItemsBinding
import uz.gita.dictionaryapi.modelForRetrofit.Meanings

class MeaningViewHolder(@NonNull view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
    private val binding: MeaningListItemsBinding = MeaningListItemsBinding.bind(view)
    private val recyclerDefinitions=binding.recyclerDefinitions
    private val textViewPartOfSpeech=binding.textViewPartOfSpeech
    fun bind(meaning:Meanings){
        textViewPartOfSpeech.text="Parts of Speech: "+meaning.partOfSpeech
        recyclerDefinitions.setHasFixedSize(true)
        recyclerDefinitions.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val definitionAdapter: DefinitionAdapter = DefinitionAdapter()
        recyclerDefinitions.adapter=definitionAdapter
        if (meaning.definitions!=null) {
            definitionAdapter.submitList(meaning.definitions)
        }

    }
}