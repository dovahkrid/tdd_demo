package com.example.tdddemo.steps

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.tdddemo.MainActivity
import com.example.tdddemo.R
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import java.lang.Thread.sleep


class LoginRobot {
    fun launchLoginScreen(testRule: ActivityScenarioRule<MainActivity>) {
        testRule.scenario.onActivity { }
    }

    fun selectPhoneNumberField() {
        onView(withId(R.id.editText_carrierNumber)).perform(click())
    }

    fun enterPhoneNumber(phoneNumber: String) {
        onView(withId(R.id.editText_carrierNumber)).perform(typeText(phoneNumber))
    }

    fun closeKeyboard() {
        Espresso.closeSoftKeyboard()
        sleep(100)
    }

    fun clickSubmitButton() {
        onView(withId(R.id.btn_submit)).perform(click())
    }

    fun showToast(testRule: ActivityScenarioRule<MainActivity>) {
        testRule.scenario.onActivity {
            onView(withText("Success")).inRoot(withDecorView(not(`is`(it.window.decorView))))
                .check(matches(isDisplayed()))

        }
    }
}