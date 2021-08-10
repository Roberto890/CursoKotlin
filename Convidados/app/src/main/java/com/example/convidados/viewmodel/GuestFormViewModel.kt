package com.example.convidados.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    //Cria a variavel para passar o context - temos que usar ANDROIDVIEWMODEL pq VIEWMODEL nao
    //possui contexto assim usamos o application
    private val mConstext = application.applicationContext
    //passamos o context no getInstance e esta instanciado o GuestRepository
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mConstext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    //LiveData não pode mudar somente dentro da nossa ViewModel
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest

    fun save(name: String, presence: Boolean){
        val guest = GuestModel(name = name, presence = presence)
        mSaveGuest.value = mGuestRepository.save(guest)

    }

    fun load(id: Int){
        mGuest.value = mGuestRepository.get(id)
    }

}