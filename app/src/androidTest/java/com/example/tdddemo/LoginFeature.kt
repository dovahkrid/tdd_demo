package com.example.tdddemo

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class LoginFeature {

    @get:Rule
    val rule = activityScenarioRule<MainActivity>()

    @Test
    fun shouldDisplayLoginScreen() {
        assertDisplayed("Login")
    }

    // need more test on UI here

}