package com.example.cara_ou_coroa.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.cara_ou_coroa.R


@BindingAdapter("caraCoroa")
fun setImage(view: ImageView, number: Int) {

    if (number == 0){
        view.setImageResource(R.drawable.moeda_cara)
    }else if (number == 1){
        view.setImageResource(R.drawable.moeda_coroa)
    }


}