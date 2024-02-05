package com.base.meddueducationproject.presentation.screens.home.userdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.base.meddueducationproject.data.repository.MedduRepository

class UserListViewModel (private val repository: MedduRepository, application: Application):AndroidViewModel(application){

    val users = repository.users

    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo: LiveData<Boolean>
        get() = _navigateTo

    fun doneNavigating(){
        _navigateTo.value=false
    }

    fun backButtonClicked(){
        _navigateTo.value = true
    }

}