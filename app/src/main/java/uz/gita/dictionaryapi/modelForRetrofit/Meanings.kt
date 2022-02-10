package uz.gita.dictionaryapi.modelForRetrofit

data class Meanings (
    val partOfSpeech:String,
    val definitions:List<Definitions>?
)
