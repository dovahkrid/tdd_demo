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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel
    lateinit var viewModelFactory: LoginViewModelFactory
    private val retrofit = Retrofit.Builder()
        .baseUrl("base_url")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val loginAPI = retrofit.create(LoginAPI::class.java)
    private val service: LoginService = LoginService(loginAPI)
    private val repository: LoginRepository = LoginRepository(service)

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
        return view
    }

    private fun setupViewModel() {
        viewModelFactory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment().apply { }
    }
}