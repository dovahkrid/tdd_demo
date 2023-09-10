package com.example.tdddemo.login

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPI {
    @FormUrlEncoded
    @POST("login_api_endpoint")
    suspend fun login(
        @Field("countrycode") countryCode: String,
        @Field("phone") phoneNumber: String
    ): LoginResponse

}
