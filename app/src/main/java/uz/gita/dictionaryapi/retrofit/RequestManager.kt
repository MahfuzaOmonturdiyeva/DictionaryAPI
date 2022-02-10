package uz.gita.dictionaryapi.retrofit

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.gita.dictionaryapi.modelForRetrofit.APIResponse

class RequestManager(val context: Context) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWordMeaning(listener: OnFetchDataListener, word: String) {
        val callDictionary: CallDictionary = retrofit.create(CallDictionary::class.java)
        val call = callDictionary.callMeanings(word)

        if (call != null) {
            try {
                call.enqueue(object : Callback<List<APIResponse?>?> {
                    override fun onResponse(
                        call: Call<List<APIResponse?>?>,
                        response: Response<List<APIResponse?>?>
                    ) {
                        if (!response.isSuccessful) {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                            return
                        }
                        response.body()?.get(0)
                            ?.let { listener.onFetchData(it, response.message()) }
                    }

                    override fun onFailure(call: Call<List<APIResponse?>?>, t: Throwable) {
                        listener.onError("No network connection!")
                    }

                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    public interface CallDictionary {
        @GET("entries/en/{word}")
        fun callMeanings(
            @Path("word") word: String?=""
        ): Call<List<APIResponse?>?>?
    }
}

