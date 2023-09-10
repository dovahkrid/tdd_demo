package com.example.tdddemo.login

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoginService(private val loginAPI: LoginAPI) {
    suspend fun login(countryCode: String, phoneNumber: String): Flow<Result<LoginResponse>> {
        return flow {
            emit(Result.success(loginAPI.login(countryCode, phoneNumber)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
