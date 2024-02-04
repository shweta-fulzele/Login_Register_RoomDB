package com.base.meddueducationproject.presentation.screens.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.base.meddueducationproject.R

import com.base.meddueducationproject.databinding.ActivityHomeScreenBinding
import com.base.meddueducationproject.presentation.interfaceutils.OnButtonClickListener
import com.base.meddueducationproject.presentation.screens.MainActivity

class HomeScreen : AppCompatActivity(), OnButtonClickListener {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen)
        binding.lifecycleOwner = this

        setContentView(binding.root)


     /*   binding.idFABAdd.setOnClickListener{
            viewModel.isAddUser = true

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }*/


    }

}