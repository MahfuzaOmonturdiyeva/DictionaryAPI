package uz.gita.dictionaryapi.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dictionaryapi.databinding.DefinitionsListItemsBinding
import uz.gita.dictionaryapi.modelForRetrofit.Definitions

class DefinitionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: DefinitionsListItemsBinding = DefinitionsListItemsBinding.bind(view)
    private val textViewDefinition = binding.textViewDefinition
    private val textViewExample=binding.textViewExample
    private val textViewSynonym=binding.textViewSynonym
    private val textViewAntonyms=binding.textViewAntonyms
    fun bind(definitions: Definitions){
        if (definitions.definition != null)
            textViewDefinition.text = "Definition: " + definitions.definition
        else textViewDefinition.text = "Definition: "
        if (definitions.example != null)
            textViewExample.text = "Example: " + definitions.example
        else textViewExample.text = "Example: "

        var synonyms: String = ""
        val listSynonyms = definitions.synonyms
        if (listSynonyms != null) {
            for (item in listSynonyms) {
                synonyms += item + "\n"
            }
            textViewSynonym.text = synonyms
        } else
            textViewSynonym.text = ""

        var antonyms: String = ""
        val listAntonyms = definitions.antonyms
        if (listAntonyms != null) {
            for (item in listAntonyms) {
                antonyms += item + "\n"
            }
            textViewAntonyms.text = antonyms
        } else
            textViewSynonym.text = ""

        textViewSynonym.isSelected = true
        textViewAntonyms.isSelected = true
    }
}