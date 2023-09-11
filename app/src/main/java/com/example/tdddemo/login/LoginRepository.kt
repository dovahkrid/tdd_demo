package com.example.tdddemo.login

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class LoginRepository(private val service: LoginService) {
    suspend fun login(countryCode: String, phoneNumber: String): Flow<Result<LoginResponse>> =
        service.login(countryCode, phoneNumber)


}
