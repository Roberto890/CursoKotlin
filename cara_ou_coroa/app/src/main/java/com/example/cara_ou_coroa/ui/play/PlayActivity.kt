package com.example.cara_ou_coroa.ui.play

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cara_ou_coroa.R
import com.example.cara_ou_coroa.databinding.ActivityPlayBinding
import com.example.cara_ou_coroa.ui.start.StartActivity
import kotlin.properties.Delegates

class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeCoin()

        binding.backButton.setOnClickListener {
            finish()
        }

    }

    private fun changeCoin(){
        val data = intent.extras
        val number = data?.getInt("coinValue")
        if (number == 0){
            binding.coin.setImageResource(R.drawable.moeda_cara)
        }else if (number == 1){
            binding.coin.setImageResource(R.drawable.moeda_coroa)
        }
    }

}