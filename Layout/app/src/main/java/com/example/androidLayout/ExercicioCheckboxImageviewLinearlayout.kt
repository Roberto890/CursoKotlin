package com.example.androidLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.androidLayout.databinding.ExercicioCheckboxImageviewLinearlayoutBinding


class ExercicioCheckboxImageviewLinearlayout : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ExercicioCheckboxImageviewLinearlayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ExercicioCheckboxImageviewLinearlayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.boxChecked.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        var clickVerify = view.id
        if (binding.boxChecked.isChecked){
            binding.androidImage.setColorFilter(ContextCompat.getColor(this,R.color.android_green))
        }else{
            binding.androidImage.setColorFilter(ContextCompat.getColor(this,R.color.black))
        }
    }

//    private fun changeColorAndroid(){
//        binding.androidImage.setColorFilter(ContextCompat.getColor(this,R.color.android_green))
//    }
//
//    private fun changeColorBlack(){
//        binding.androidImage.setColorFilter(ContextCompat.getColor(this,R.color.black))
//    }
}

