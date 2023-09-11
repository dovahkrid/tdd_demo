package com.example.tdddemo.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.tdddemo.R
import com.example.tdddemo.RootFragment
import com.example.tdddemo.network.LoginAPI
import com.example.tdddemo.repository.LoginRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class LoginFragment : RootFragment() {

    lateinit var viewModel: LoginViewModel
    lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        setupViewModel()
        viewModel.response.observe(this as LifecycleOwner) { response ->
            if (response.getOrNull() != null)
                Log.d("LoginFragment", "response: $response")
            else
                Log.d("LoginFragment", "response: $response")
        }

        view.findViewById<View>(R.id.btn_submit).setOnClickListener { login() }
        return view
    }

    private fun setupViewModel() {
        viewModelFactory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    private fun login() {
        viewModel.login()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment().apply { }
    }
}