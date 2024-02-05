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
import com.base.meddueducationproject.data.repository.MedduRepository
import com.base.meddueducationproject.data.roomdb.MedduDatabase
import com.base.meddueducationproject.databinding.FragmentLoginBinding
import com.base.meddueducationproject.presentation.interfaceutils.OnButtonClickListener
import com.base.meddueducationproject.presentation.screens.home.HomeScreen


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private var buttonClickListener: OnButtonClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dao = MedduDatabase.getInstance(application).registerDatabaseDao
        val repository = MedduRepository(dao)
        val factory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.loginViewModel = loginViewModel

        binding.lifecycleOwner = this
        loginViewModel.navigateToUserDetails.observe(viewLifecycleOwner) {
            Toast.makeText(context, "$it in success", Toast.LENGTH_SHORT).show()
            if (it) {
                onLoginSuccess()
            }
        }

        loginViewModel.errorToast.observe(viewLifecycleOwner) {
            Toast.makeText(context, "$it in error toast", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.erroToastUsername.observe(viewLifecycleOwner) {
            Toast.makeText(context, "$it in errorToastUsername", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.errorToastInvalidPassword.observe(viewLifecycleOwner) {
            Toast.makeText(context, "$it in errorToastInvalidPassword", Toast.LENGTH_SHORT).show()
        }
        binding.loginButton.setOnClickListener {
            loginViewModel.inputUsername.value = binding.etLoginUsername.text.toString()
            loginViewModel.inputPassword.value = binding.etLoginPassword.text.toString()
            login()
        }

        binding.tvDontHaveAccount.setOnClickListener{
            buttonClickListener?.onSignupButtonClick()
        }

        return binding.root
    }

    private fun login() {
        loginViewModel.loginButton()
    }
    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        buttonClickListener = listener
    }

    private fun onLoginSuccess() {
        val intent = Intent(activity, HomeScreen::class.java)
        startActivity(intent)
        activity?.finish()
    }
}