package com.example.tdddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tdddemo.login.LoginFragment
import com.example.tdddemo.repository.LoginRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : RootActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LoginFragment.newInstance())
                .commit()
        }
    }
}