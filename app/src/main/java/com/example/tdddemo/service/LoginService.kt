package com.example.tdddemo.service

import com.example.tdddemo.login.LoginResponse
import com.example.tdddemo.network.LoginAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginService @Inject constructor(private val loginAPI: LoginAPI) {
    suspend fun login(countryCode: String, phoneNumber: String): Flow<Result<LoginResponse>> {
        return flow {
            emit(Result.success(loginAPI.login(countryCode, phoneNumber)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
