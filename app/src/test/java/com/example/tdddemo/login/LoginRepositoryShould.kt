package com.example.tdddemo.login

import com.example.tdddemo.repository.LoginRepository
import com.example.tdddemo.service.LoginService
import com.example.tdddemo.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LoginRepositoryShould : BaseUnitTest() {

    private val service: LoginService = mock()
    private val response = mock<LoginResponse>()
    private val error = Result.failure<LoginResponse>(RuntimeException("Something went wrong"))

    @Test
    fun loginFromService() = runTest {
        val repository = LoginRepository(service)
        repository.login(countryCode, phoneNumber)
        verify(service, times(1)).login(countryCode, phoneNumber)
    }

    @Test
    fun emitLoginResultFromService() = runTest {
        val repository = mockSuccessfulCase()
        assertEquals(response, repository.login(countryCode, phoneNumber).first().getOrNull())
    }

    @Test
    fun emitErrorWhenReceiveError() = runTest {
        val repository = mockFailureCase()
        assertEquals(
            "Something went wrong",
            repository.login(countryCode, phoneNumber).first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockFailureCase(): LoginRepository {
        whenever(service.login(countryCode, phoneNumber)).thenReturn(
            flow {
                emit(error)
            }
        )
        return LoginRepository(service)
    }

    private suspend fun mockSuccessfulCase(): LoginRepository {
        whenever(service.login(countryCode, phoneNumber)).thenReturn(
            flow {
                emit(Result.success(response))
            }
        )
        return LoginRepository(service)
    }

}