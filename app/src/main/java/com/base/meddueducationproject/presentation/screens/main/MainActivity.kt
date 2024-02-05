package com.base.meddueducationproject.presentation.screens.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.base.meddueducationproject.R
import com.base.meddueducationproject.presentation.interfaceutils.OnButtonClickListener
import com.base.meddueducationproject.presentation.screens.home.HomeScreen
import com.base.meddueducationproject.presentation.screens.login.LoginFragment
import com.base.meddueducationproject.presentation.screens.signup.SignupFragment

class MainActivity : AppCompatActivity(), OnButtonClickListener {
    private var  isFabBtnClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isFabBtnClicked = intent.getBooleanExtra("isFabClicked", false)

        if (isFabBtnClicked){
            showSignupFragment()

        }else{
            showLoginFragment()

        }

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
        showSignupFragment()
    }

    override fun onLoginButtonClick() {
        isFabBtnClicked = intent.getBooleanExtra("isFabClicked", false)

        if (isFabBtnClicked) {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }else{
            showLoginFragment()
        }
    }

    override fun onAlreadyHaveAccount() {
        super.onAlreadyHaveAccount()
        showLoginFragment()
    }
}