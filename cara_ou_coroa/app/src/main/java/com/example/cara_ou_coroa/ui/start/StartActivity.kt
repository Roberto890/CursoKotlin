package com.example.cara_ou_coroa.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cara_ou_coroa.databinding.ActivityStartBinding
import com.example.cara_ou_coroa.ui.play.PlayActivity
import kotlin.random.Random

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener {
            var random = (0..1).random() // 0 e 1
            var intent = Intent(this, PlayActivity::class.java)
            intent.putExtra("coinValue", random)
            startActivity(intent)
        }
    }
}