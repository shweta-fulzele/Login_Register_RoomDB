package com.base.meddueducationproject.presentation.screens.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.meddueducationproject.R
import com.base.meddueducationproject.data.repository.MedduRepository
import com.base.meddueducationproject.data.roomdb.MedduDatabase
import com.base.meddueducationproject.databinding.ActivityHomeScreenBinding
import com.base.meddueducationproject.presentation.interfaceutils.OnButtonClickListener
import com.base.meddueducationproject.presentation.screens.main.MainActivity
import com.base.meddueducationproject.presentation.screens.home.userdetails.UserListAdapter
import com.base.meddueducationproject.presentation.screens.home.userdetails.UserListFactory
import com.base.meddueducationproject.presentation.screens.home.userdetails.UserListViewModel

class HomeScreen : AppCompatActivity(), OnButtonClickListener {
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var userListVM: UserListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val application = requireNotNull(this).application
        val dao = MedduDatabase.getInstance(application).registerDatabaseDao
        val repository = MedduRepository(dao)
        val factory = UserListFactory(repository, application)

        userListVM = ViewModelProvider(this, factory).get(UserListViewModel::class.java)

        displayUserList()

        binding.idFABAdd.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("isFabClicked", true)
            startActivity(intent)
            finish()
        }

    }


    private fun displayUserList() {
        binding.rvUserList.layoutManager = LinearLayoutManager(this)

        userListVM.users.observe(this) {
            binding.rvUserList.adapter = UserListAdapter(it)
        }
    }

}