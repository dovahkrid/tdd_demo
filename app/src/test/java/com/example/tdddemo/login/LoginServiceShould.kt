package com.example.tdddemo.login

import com.example.tdddemo.network.LoginAPI
import com.example.tdddemo.service.LoginService
import com.example.tdddemo.utils.BaseUnitTest
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LoginServiceShould : BaseUnitTest() {

    private val loginAPI: LoginAPI = mock()
    private val response = mock<LoginResponse>()
    private val error = RuntimeException("Something went wrong")

    @Test
    fun loginFromAPI() = runTest {
        val service = LoginService(loginAPI)
        service.login(countryCode, phoneNumber).first()
        verify(loginAPI, times(1)).login(countryCode, phoneNumber)
    }

    @Test
    fun emitLoginResultFromAPI() = runTest {
        val service = mockSuccessfulCase()
        TestCase.assertEquals(
            response,
            service.login(countryCode, phoneNumber).first().getOrNull()
        )
    }

    @Test
    fun emitErrorWhenReceiveError() = runTest {
        val service = mockFailureCase()
        TestCase.assertEquals(
            "Something went wrong",
            service.login(countryCode, phoneNumber).first().exceptionOrNull()?.message
        )
    }


    private suspend fun mockFailureCase(): LoginService {
        whenever(loginAPI.login(countryCode, phoneNumber)).thenThrow(
            error
        )
        return LoginService(loginAPI)
    }

    private suspend fun mockSuccessfulCase(): LoginService {
        whenever(loginAPI.login(countryCode, phoneNumber)).thenReturn(
            response
        )
        return LoginService(loginAPI)
    }

}