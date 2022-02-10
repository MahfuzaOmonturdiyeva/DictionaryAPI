package uz.gita.dictionaryapi.retrofit

import uz.gita.dictionaryapi.modelForRetrofit.APIResponse

interface OnFetchDataListener {
    fun onFetchData(apiResponse: APIResponse, message:String)
    fun onError(message: String)
}