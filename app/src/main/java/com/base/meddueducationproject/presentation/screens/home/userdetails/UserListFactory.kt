package com.base.meddueducationproject.presentation.screens.home.userdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.base.meddueducationproject.data.repository.MedduRepository
import java.lang.IllegalArgumentException

class UserListFactory (
    private  val repository: MedduRepository,
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}