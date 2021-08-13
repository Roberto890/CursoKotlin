package com.example.midiaplayer

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import com.example.midiaplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.castlevania)
        mediaPlayer.isLooping = true
        initSeekBarSound()

        binding.buttonPlay.setOnClickListener {
            playSound()
        }

        binding.buttonPause.setOnClickListener {
            pauseSound()
        }

        binding.buttonStop.setOnClickListener {
            stopSound()
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    override fun onResume() {
        super.onResume()
        if(!mediaPlayer.isPlaying){
            mediaPlayer.start()
        }
    }

    override fun onStop() {
        super.onStop()
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }
    }

    private fun playSound(){
        mediaPlayer.start()
    }

    private fun pauseSound(){
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }

    }

    private fun stopSound(){
        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, R.raw.castlevania)
        }
    }

    fun initSeekBarSound(){
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        binding.seekBar.max = maxVolume
        binding.seekBar.progress = currentVolume
        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, p1, 0)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                print("a")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                print("b")
            }

        })

    }

}