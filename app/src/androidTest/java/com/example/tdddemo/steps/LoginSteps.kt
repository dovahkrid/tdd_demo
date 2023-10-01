package com.example.tdddemo.steps

import android.os.SystemClock
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tdddemo.MainActivity
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.And
import io.cucumber.java.en.Then
import junit.framework.Assert.assertNotNull
import io.cucumber.java.After
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginSteps {
    private val robot = LoginRobot()

    @get:Rule
    val rule = activityScenarioRule<MainActivity>()

    lateinit var activity: MainActivity

    @Before("@login-feature")
    fun setup() {
        robot.launchLoginScreen(rule)
        rule.scenario.onActivity {
            activity = it
        }
    }

    @After("@login-feature")
    fun tearDown() {
        SystemClock.sleep(3000)
        activity.finish()
    }

    @Given("I have a Main Activity")
    fun iHaveAMainActivity() {
        assertNotNull(activity)
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


    @And("I press submit button")
    fun iPressSubmitButton() {
        robot.clickSubmitButton()
    }

    @Then("I should see a success toast")
    fun iShouldSeeASuccessToast() {
        robot.showToast(rule)
    }

}