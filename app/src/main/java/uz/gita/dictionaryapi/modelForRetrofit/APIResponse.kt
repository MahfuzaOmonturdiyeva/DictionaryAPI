package uz.gita.dictionaryapi.modelForRetrofit

data class APIResponse (
    val word:String,
    val phonetics: List<Phonetics>?,
    val meanings: List<Meanings>?
)