package com.base.meddueducationproject.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.base.meddueducationproject.R
import com.base.meddueducationproject.presentation.interfaceutils.OnButtonClickListener
import com.base.meddueducationproject.presentation.screens.login.LoginFragment
import com.base.meddueducationproject.presentation.screens.register.SignupFragment

class MainActivity : AppCompatActivity(), OnButtonClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



            showSignupFragment()


    }


    private fun showLoginFragment() {
        val loginFragment = LoginFragment()
        loginFragment.setOnButtonClickListener(this)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, loginFragment)
            .commit()
    }

    private fun showSignupFragment() {
        val signupFragment = SignupFragment()
        signupFragment.setOnButtonClickListener(this)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, signupFragment)
            .commit()
    }


    override fun onSignupButtonClick() {
        Log.d("MainActivity", "signup button clicked")

        showSignupFragment()
    }

    override fun onDontHaveAccount() {
        showSignupFragment()
    }

    override fun onLoginButtonClick() {
        Log.d("MainActivity", "Login button clicked")

        showLoginFragment()

    }

}