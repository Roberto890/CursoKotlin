package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.infra.SecurityPreferences.MotivationConstants
import com.example.motivation.infra.SecurityPreferences.SecurityPreferences
import com.example.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mSecurityPreferences : SecurityPreferences
    private var mPhraseFilter : Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSecurityPreferences = SecurityPreferences(this)
        binding.textName.text = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)

        //Lógica inicial de seleção
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
        handleNewPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageMorning.setOnClickListener(this)

        if (supportActionBar != null){
            supportActionBar!!.hide()
        }
    }

    override fun onClick(view: View) {
        val id = view.id
        val listFilter = listOf(binding.imageAll.id, binding.imageHappy.id, binding.imageMorning.id)

        if (id == binding.buttonNewPhrase.id){
            handleNewPhrase()
        } else if (id in listFilter){
            handleFilter(id)
        }

    }

    private fun handleFilter(id: Int){
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
        binding.imageMorning.setColorFilter(ContextCompat.getColor(this, R.color.white))
        when(id){
            binding.imageAll.id -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
                println(mPhraseFilter)
            }
            binding.imageHappy.id -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
                println(mPhraseFilter)
            }
            binding.imageMorning.id -> {
                binding.imageMorning.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
                println(mPhraseFilter)
            }
        }

    }

    private fun handleNewPhrase(){
        println(mPhraseFilter)
        binding.textPhrase.text =  Mock().getPhrase(mPhraseFilter)

    }


}