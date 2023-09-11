package com.example.tdddemo.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.tdddemo.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    val response = liveData<Result<LoginResponse>> {
        emitSource(repository.login("84", "838613616").asLiveData())
    }

    fun login(){
        viewModelScope.launch {
            val loginRes =  repository.login("","")
        }
    }
}
