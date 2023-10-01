package com.example.tdddemo.test

import android.os.Bundle
import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["features"],
    glue = ["com.example.tdddemo.steps"])
class LoginRunner: CucumberAndroidJUnitRunner(){


    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
    }
}