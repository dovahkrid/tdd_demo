package com.example.tdddemo

import androidx.appcompat.app.AppCompatActivity
import com.example.tdddemo.repository.LoginRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class RootActivity : AppCompatActivity()  {

    @Inject
    lateinit var loginRepository: LoginRepository

}