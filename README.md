# TDD Demo (UPDATED)

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
  These are the unit tests of respectively ```LoginService```, ```LoginRepository```, ```LoginViewModel```

### The flow of Login Feature

![The Data and Test Flow](https://github.com/dovahkrid/tdd_demo/blob/master/Drawing.png)

Each of the component will have their own unit test. The different is the latter component depends on the former (for example, ```LoginViewModel``` needs the ```LoginRepository```). But in the unit test, they can't depend on each other. We use ```mock``` to simulate the dependencies, ensuring the isolation of component.

### In the Unit Test


Each component has 3 unit tests: The integration test, the "successful" test and the "error" test. For example, the repository test in ```LoginRepositoryShould```:

- The integration test

```kotlin
    private val service: LoginService = mock()
    @Test
    fun loginFromService() = runTest {
        val repository = LoginRepository(service)
        repository.login(countryCode, phoneNumber)
        verify(service, times(1)).login(countryCode, phoneNumber)
    }
```
This test ensures that the ```service``` is integrated and the ```login``` method called only once when the repository call ```login```. The service is a mock, not the real one to ensure the isolation of the component.



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



## Cucumber

### Introduction

Cucumber is a Behavior-Driven Development (BDD) tool that allows our team to describe software behavior without detailing how that functionality is implemented. By using plain language constructs, Cucumber fosters collaboration between technical and non-technical members of our team, ensuring that everyone is on the same page.

### Setup

1. We need to write a "script" file to describe the feature. The file is in ```app/src/androidTest/assets/features/login.feature```. The file is written in Gherkin language, which is a plain language that can be understood by both technical and non-technical members of the team.

```gherkin
Feature: Login
  Perform login on phone number is inputted

  @login-feature
  Scenario Outline: Input phone number
    Given I have a Main Activity
    When I click phone number field
    And I enter valid phone number <phone_number>
    And I close the keyboard
    Then I press submit button

    Examples:
      | phone_number |
      | 868218516 |
```

2. We need to write the step definition file to describe the steps in the script file. The file is in ```app/src/androidTest/java/com/example/tdddemo/login/LoginSteps.kt```. The file is written in Kotlin language, which is a programming language that can be understood by technical members of the team. For example:

```kotlin
class LoginSteps {

    init {
        Given("I have a Main Activity") {
            launchActivity<MainActivity>()
        }

        When("I click phone number field") {
            onView(withId(R.id.et_phone_number)).perform(click())
        }

        And("I enter valid phone number {string}") { phoneNumber: String ->
            onView(withId(R.id.et_phone_number)).perform(typeText(phoneNumber))
        }

        And("I close the keyboard") {
            onView(withId(R.id.et_phone_number)).perform(closeSoftKeyboard())
        }

        Then("I press submit button") {
            onView(withId(R.id.btn_submit)).perform(click())
        }
    }
}
```


3. We need to write the test runner file to run the test. The file is in ```app/src/androidTest/java/com/example/tdddemo/test/LoginRunner.kt```. The file is written in Kotlin language, which is a programming language that can be understood by technical members of the team. For example:

```kotlin
@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["features"],
    glue = ["com.example.tdddemo.steps"])
class LoginRunner: CucumberAndroidJUnitRunner(){


    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
    }
}
```

4. Now we can run the test with the IDE.

The example above is only a very simple step of an user behavior (tap the text field, input the phone number, and tap the submit button)
