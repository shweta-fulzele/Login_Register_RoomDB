package com.base.meddueducationproject.presentation.screens.signup

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.base.meddueducationproject.data.repository.MedduRepository
import com.base.meddueducationproject.data.roomdb.MedduEntity
import kotlinx.coroutines.*


class SignupViewModel(private val repository: MedduRepository, application: Application) :
    AndroidViewModel(application), Observable {

    private var userdata: String? = null

    var userDetailsLiveData = MutableLiveData<Array<MedduEntity>>()


    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val inputPhoneNumber = MutableLiveData<String?>()

    @Bindable
    val inputUsername = MutableLiveData<String?>()

    @Bindable
    val inputPassword = MutableLiveData<String?>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo: LiveData<Boolean>
        get() = _navigateTo

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errorToastUsername: LiveData<Boolean>
        get() = _errorToastUsername


    fun submitButton() {
        if (inputUsername.value == null
            || inputPhoneNumber.value == null
            || inputEmail.value == null ||
            inputPassword.value == null)
        {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val usersNames = repository.getUserName(inputUsername.value!!)
                if (usersNames != null) {
                    _errorToastUsername.value = true
                } else {
                    val username = inputUsername.value!!
                    val phoneNumber = inputPhoneNumber.value!!
                    val email = inputEmail.value!!
                    val password = inputPassword.value!!
                    insert(MedduEntity(0, userName = username,
                        mobile = phoneNumber, email = email, password = password))

                    inputUsername.value = null
                    inputPhoneNumber.value = null
                    inputEmail.value = null
                    inputPassword.value = null
                    _navigateTo.value = true
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigating() {
        _navigateTo.value = false
    }

    fun doneToast() {
        _errorToast.value = false
    }

    fun doneToastUserName() {
        _errorToast.value = false
    }

    private fun insert(user: MedduEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}

