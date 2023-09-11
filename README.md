# TDD Demo

This is the demo for Test-Driven Development. This demo will be updated continuously until the Clean Architecture is completely applied.


## Why use TDD

- **Documentation**: For me, this is actually the most important advantage of TDD. It provides clear documentation. The tests effectively describe the functionality and expectations of the system. Anyone reading the tests can understand the intended behavior and constraints of the feature, even without diving deep into the implementation details.
- **Quality Control**: TDD ensures that before any code is written, there's a clear and comprehensive suite of tests to check against.
- **For the Long Run**: While TDD might seem time-consuming initially, it pays dividends in the long run. As your application grows, ensuring quality and preventing regressions becomes increasingly challenging. With TDD, you have a safety net. Before adding new features or making changes, you can be confident that existing functionality remains intact. Moreover, if a bug or issue is discovered, it's easier to pinpoint and resolve with tests in place.

## Development Steps

1.  **(Currently Done) Add the Unit Test**: As of now, we have implemented unit tests for the first API of the Login feature. We'll add more when doing the add business logic.
2.  **Apply Dependency Injection**: Next, we plan to incorporate dependency injection for efficient service handling and enhancing modularity and testability.
3.  **Add More Business Logic and Test**: As the application evolves, we intend to integrate more intricate business logic. Each new addition will strictly adhere to the TDD principles, ensuring that tests are written and passed before the feature's implementation.
4.  **Restructure Files Based on Clean Architecture**: To further maintain scalability and readability, we'll be restructuring the project following the Clean Architecture guidelines. This will ensure that our application remains modular, adaptable, and maintainable as it grows.

## Current State

### Test
- Feature Test: ```LoginFeature.kt``` (in folder app/src/androidTest/java/com/example/tdddemo/)
This is the file contain feature tests, and also the future UI tests. For now, we will run test in here to know which unit test step we're currently in.
- Unit Test: ```LoginServiceShould.kt```, ```LoginRepositoryShould.kt```, ```LoginViewModelShould.kt``` (in folder app/src/test/java/com/example/tdddemo/login/)
These are the unit tests of respectively ```LoginServiceShould```, ```LoginRepositoryShould```, ```LoginViewModelShould```

### The flow of Login Feature

Add the image later

Each component has 3 unit tests: The intergration test, the "successfull" test and the "error" test. For example, the repository test in ```LoginRepositoryShould```:

- The intergration test

```kotlin
    private val service: LoginService = mock()
    @Test
    fun loginFromService() = runTest {
        val repository = LoginRepository(service)
        repository.login(countryCode, phoneNumber)
        verify(service, times(1)).login(countryCode, phoneNumber)
    }
```
This test ensures that the ```service``` is intergrated and the ```login``` method called only once when the repository call ```login```. The service is a mock, not the real one to ensure the isolation of the component.



- The "successful" test
```kotlin
    @Test
    fun emitLoginResultFromService() = runTest {
        val repository = mockSuccessfulCase()
        assertEquals(response, repository.login(countryCode, phoneNumber).first().getOrNull())
    }

    private suspend fun mockSuccessfulCase(): LoginRepository {
        whenever(service.login(countryCode, phoneNumber)).thenReturn(
            flow {
                emit(Result.success(response))
            }
        )
        return LoginRepository(service)
    }
```

- The "failure" test
```kotlin
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
```


Each of the component will use all 3 basic unit tests above. There will be more specific tests in specific logic and situations.
