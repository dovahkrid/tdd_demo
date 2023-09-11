package com.example.tdddemo.login

import com.example.tdddemo.repository.LoginRepository
import com.example.tdddemo.utils.BaseUnitTest
import com.example.tdddemo.utils.getValueForTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LoginViewModelShould : BaseUnitTest() {

    private val repository: LoginRepository = mock()
    private val response = mock<LoginResponse>()
    private val expected = Result.success(response)
    private val error = Result.failure<LoginResponse>(Throwable("Something went wrong"))


    @Test
    fun getResponseFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.response.getValueForTest()

        verify(repository, times(1)).login(countryCode, phoneNumber)
    }

    @Test
    fun emitResponseFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.response.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() = runTest {
        val viewModel = mockFailureCase()
        assertEquals(error, viewModel.response.getValueForTest())
    }

    private fun mockFailureCase(): LoginViewModel {
        runBlocking {
            whenever(repository.login(countryCode, phoneNumber)).thenReturn(
                flow {
                    emit(error)
                }
            )
        }
        return LoginViewModel(repository)
    }

    private fun mockSuccessfulCase(): LoginViewModel {
        runBlocking {
            whenever(repository.login(countryCode, phoneNumber)).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return LoginViewModel(repository)
    }
}