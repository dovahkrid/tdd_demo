package com.example.tdddemo.steps

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tdddemo.MainActivity
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.And
import io.cucumber.java.en.Then
import io.cucumber.java.After
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginSteps {
    private val robot = LoginRobot()

    @get:Rule
    val rule = activityScenarioRule<MainActivity>()

    val scenario = ActivityScenario.launch(MainActivity::class.java)

    @Before("@login-feature")
    fun setup() {
        robot.launchLoginScreen(scenario)
    }

    @After("@login-feature")
    fun tearDown() {

    }

    @Given("I have a Main Activity")
    fun iHaveAMainActivity() {
//        assertNotNull(activity)
    }


    @When("I click phone number field")
    fun iClickPhoneNumberField() {
        robot.selectPhoneNumberField()
    }


    @And("I enter valid phone number (\\S+)$")
    fun iEnterValidPhoneNumberPhone_number(phoneNumber: String) {
        robot.enterPhoneNumber(phoneNumber)
    }


    @And("I close the keyboard")
    fun iCloseTheKeyboard() {
        robot.closeKeyboard()
    }

    @Then("I press submit button")
    fun iPressSubmitButton() {
        robot.clickSubmitButton()
    }

//    @And("I press submit button")
//    fun iPressSubmitButton() {
//        robot.clickSubmitButton()
//    }
//
//    @Then("I should see a success toast")
//    fun iShouldSeeASuccessToast() {
//        robot.showToast()
//    }

}