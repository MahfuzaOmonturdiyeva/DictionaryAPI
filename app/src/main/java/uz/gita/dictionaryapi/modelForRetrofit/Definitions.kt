package uz.gita.dictionaryapi.modelForRetrofit

data class Definitions(
    val definition:String?,
    val example:String?,
    val synonyms:List<String>?,
    val antonyms:List<String>?
)
