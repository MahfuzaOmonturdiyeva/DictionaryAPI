package uz.gita.dictionaryapi

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.dictionaryapi.adapters.MeaningAdapter
import uz.gita.dictionaryapi.adapters.PhoneticAdapter
import uz.gita.dictionaryapi.databinding.ActivityMainBinding
import uz.gita.dictionaryapi.modelForRetrofit.APIResponse
import uz.gita.dictionaryapi.retrofit.OnFetchDataListener
import uz.gita.dictionaryapi.retrofit.RequestManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var phoneticAdapter: PhoneticAdapter
    private lateinit var meaningAdapter: MeaningAdapter
    private lateinit var manager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)

        progressDialog.setTitle("Loading...")
        progressDialog.show()
        manager = RequestManager(this@MainActivity)
        manager.getWordMeaning(listener = listener, word="hello")

        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    progressDialog.setTitle("Fatching response for $p0" ?: "")
                    progressDialog.show()
                    manager.getWordMeaning(listener = listener, word = p0 ?: "")
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }

            })
    }

    private val listener: OnFetchDataListener = object : OnFetchDataListener {
        override fun onFetchData(apiResponse: APIResponse, message: String) {
            progressDialog.dismiss()
            if (apiResponse == null) {
                Toast.makeText(this@MainActivity, "No data found!!!", Toast.LENGTH_SHORT).show()
                return
            }
            showData(apiResponse)
        }


        override fun onError(message: String) {
            progressDialog.dismiss()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }

    }

    fun showData(apiResponse: APIResponse) {
        binding.textViewWord.text = "Word: " + apiResponse.word
        binding.recyclerPhonetics.setHasFixedSize(true)
        binding.recyclerPhonetics.layoutManager =LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        phoneticAdapter = PhoneticAdapter(this)
        binding.recyclerPhonetics.adapter = phoneticAdapter
        phoneticAdapter.submitList(apiResponse.phonetics)
        binding.recyclerMeaning.setHasFixedSize(true)
        binding.recyclerMeaning.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        meaningAdapter = MeaningAdapter(this)
        binding.recyclerMeaning.adapter = meaningAdapter
        meaningAdapter.submitList(apiResponse.meanings)

        var string=""
        for (item in apiResponse.meanings!!){
            string+=item.partOfSpeech+"\t"
        }

        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}