package com.base.meddueducationproject.presentation.screens.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.base.meddueducationproject.R
import com.base.meddueducationproject.data.repository.RegisterRepository
import com.base.meddueducationproject.data.roomdb.RegisterDatabase
import com.base.meddueducationproject.databinding.FragmentLoginBinding
import com.base.meddueducationproject.presentation.interfaceutils.OnButtonClickListener
import com.base.meddueducationproject.presentation.screens.home.HomeScreen


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    //    Interface for changing fragment from hosting activity
    private var buttonClickListener: OnButtonClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this


        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.loginViewModel = loginViewModel

        binding.lifecycleOwner = this
        loginViewModel.navigatetoUserDetails.observe(viewLifecycleOwner) {
            Toast.makeText(context, "$it in success", Toast.LENGTH_SHORT).show()
            if (it) {
                onLoginSuccess()
            }
        }

        loginViewModel.errotoast.observe(viewLifecycleOwner) {
            Toast.makeText(context, "$it in error toast", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.errotoastUsername.observe(viewLifecycleOwner) {
            Toast.makeText(context, "$it in errorToastUsername", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.errorToastInvalidPassword.observe(viewLifecycleOwner) {
            Toast.makeText(context, "$it in errorToastInvalidPassword", Toast.LENGTH_SHORT).show()
        }
        binding.loginButton.setOnClickListener {
            loginViewModel.inputUsername.value = binding.etLoginEmail.text.toString()
            loginViewModel.inputPassword.value = binding.etLoginPassword.text.toString()
            login()
        }

        return binding.root
    }

    private fun login() {
//        TODO("Not yet implemented")
        loginViewModel.loginButton()
    }


    // Function to set the button click listener
    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        buttonClickListener = listener
    }

    private fun onLoginSuccess() {
        val intent = Intent(activity, HomeScreen::class.java)
        startActivity(intent)
        activity?.finish()
    }
}