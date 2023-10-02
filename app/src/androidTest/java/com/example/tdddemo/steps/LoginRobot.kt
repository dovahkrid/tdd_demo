package com.example.tdddemo.steps

import android.os.SystemClock
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.tdddemo.MainActivity
import com.example.tdddemo.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import java.lang.Thread.sleep


class LoginRobot {

    private lateinit var decorView: View
    fun launchLoginScreen(scenario: ActivityScenario<MainActivity>) {
        scenario.onActivity {
            decorView = it.window.decorView
        }
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

    fun showToast() {
        onView(withText("Success"))
            .inRoot(withDecorView(Matchers.not(decorView)))
            .check(matches(isDisplayed()))
//        onView(withText("Success"))
//            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}