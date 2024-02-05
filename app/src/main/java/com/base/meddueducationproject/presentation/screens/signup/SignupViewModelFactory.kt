package com.base.meddueducationproject.presentation.screens.signup

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.base.meddueducationproject.data.repository.MedduRepository
import java.lang.IllegalArgumentException

class SignupViewModelFactory(
    private val repository: MedduRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}