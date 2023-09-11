package com.example.tdddemo.repository

import com.example.tdddemo.login.LoginResponse
import com.example.tdddemo.service.LoginService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepository @Inject constructor(private val service: LoginService) {
    suspend fun login(countryCode: String, phoneNumber: String): Flow<Result<LoginResponse>> =
        service.login(countryCode, phoneNumber)

}
