package com.example.tdddemo.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    val response = liveData<Result<LoginResponse>> {
        emitSource(repository.login("84", "838613616").asLiveData())
    }

}
