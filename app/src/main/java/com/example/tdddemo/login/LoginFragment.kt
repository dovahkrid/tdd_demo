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
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel

    @Inject
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
        return view
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment().apply { }
    }
}