package com.example.tdddemo.di

import com.example.tdddemo.service.LoginService
import com.example.tdddemo.network.LoginAPI
import com.example.tdddemo.network.interceptor.InterceptorRequestLogin
import com.example.tdddemo.repository.LoginRepository
import com.hbb20.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthNetworkModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepository(service = loginService)
    }

    @Provides
    @Singleton
    fun provideLoginService(loginAPI: LoginAPI): LoginService {
        return LoginService(loginAPI = loginAPI)
    }

    @Provides
    @Singleton
    fun provideLoginAPI(@Named("LoginOkHttpClient") okHttpClient: OkHttpClient): LoginAPI {
        return Retrofit.Builder()
//            .baseUrl(BuildConfig.DOMAIN_API_AUTH)
            .baseUrl("https://messagingtest.mycurrentmessenger.com:8080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginAPI::class.java)
    }

    @Provides
    @Singleton
    @Named("LoginOkHttpClient")
    fun provideOkHttpClient(interceptorRequest: InterceptorRequestLogin): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(interceptorRequest)
            .also {
                if(BuildConfig.DEBUG){
                    it.addInterceptor(logging)
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptorRequest(): InterceptorRequestLogin {
        return InterceptorRequestLogin()
    }

}