package uz.gita.dictionaryapi.holders

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dictionaryapi.databinding.PhoneticsListItemBinding
import uz.gita.dictionaryapi.modelForRetrofit.Phonetics

class PhoneticViewHolder(view: View,private val context: Context) : RecyclerView.ViewHolder(view) {
    private val binding: PhoneticsListItemBinding = PhoneticsListItemBinding.bind(view)
    private val imageButton=binding.imageButtonAudio
    private val textView=binding.textViewPhonetic
    val phonetics=binding.phonetics
    fun bind(phonetics: Phonetics){
        textView.text = phonetics.text
        if (phonetics.audio != null) {
            imageButton.visibility = View.VISIBLE
            imageButton.setOnClickListener {
                val player = MediaPlayer()
                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                    player.setDataSource("https:" + phonetics.audio)
                    player.prepare()
                    player.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, "Couldn't play audio", Toast.LENGTH_SHORT).show()
                }
            }
        } else imageButton.visibility = View.INVISIBLE
    }
}