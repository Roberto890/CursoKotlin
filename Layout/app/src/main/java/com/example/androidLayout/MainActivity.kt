package com.example.androidLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidLayout.databinding.ConstraintLayoutBinding
import com.example.androidLayout.databinding.ExercicioConstraintlayout2Binding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ExercicioConstraintlayout2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ExercicioConstraintlayout2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null){
            supportActionBar!!.hide()
        }
    }
}