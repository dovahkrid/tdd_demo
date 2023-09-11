package com.example.tdddemo

import androidx.fragment.app.Fragment
import com.example.tdddemo.repository.LoginRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class RootFragment : Fragment() {

    @Inject
    lateinit var repository: LoginRepository

}