package com.example.motivation.infra.SecurityPreferences

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(val context: Context) {

    private val mSharedPreferences = context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String){
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String):String{
                                                    // ?: = verifica se é diferente de null se sim retorna o que ta na frente
        return mSharedPreferences.getString(key, "") ?: ""
    }


    //coloquei o metodo so pra mostrar q da pra fazer outros store ex: inteiros
    fun storeInt(key: String, value: Int){
        mSharedPreferences.edit().putInt(key, value).apply()
    }
}