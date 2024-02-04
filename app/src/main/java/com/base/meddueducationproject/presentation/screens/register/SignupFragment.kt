package com.base.meddueducationproject.presentation.screens.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.base.meddueducationproject.R
import com.base.meddueducationproject.data.repository.RegisterRepository
import com.base.meddueducationproject.data.roomdb.RegisterDatabase

import com.base.meddueducationproject.data.roomdb.RegisterEntity
import com.base.meddueducationproject.databinding.FragmentSignupBinding
import com.base.meddueducationproject.presentation.interfaceutils.OnButtonClickListener

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private var buttonClickListener: OnButtonClickListener? = null
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        binding.lifecycleOwner = this


        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)

        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)

        binding.myViewModel = registerViewModel

        binding.lifecycleOwner = this

//        registerViewModel.navigateto.observe(this, Observer { hasFinished->
//            if (hasFinished == true){
//                Log.i("MYTAG","insidi observe")
//                displayUsersList()
//                registerViewModel.doneNavigating()
//            }
//        })

//        registerViewModel.userDetailsLiveData.observe(this, Observer {
//            Log.i("MYTAG",it.toString()+"000000000000000000000000")
//        })


//        registerViewModel.errotoast.observe(this, Observer { hasError->
//            if(hasError==true){
//                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
//                registerViewModel.donetoast()
//            }
//        })
//
//        registerViewModel.errotoastUsername.observe(this,  { hasError->
//            if(hasError==true){
//                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT).show()
//                registerViewModel.donetoastUserName()
//            }
//        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupBtn.setOnClickListener {

            val username = binding.etSignupName.text.toString()
            val email = binding.etSignupEmail.text.toString()
            val phone = binding.etSignupNumber.text.toString()
            val password = binding.etSignupPassword.text.toString()

            // Create a new UserEntity with the input data
            val newUser = RegisterEntity(userName = username, email = email, mobile = phone, password = password)

            // Insert the new user using the ViewModel
            registerViewModel.insert(newUser)

            // Notify the hosting activity about the signup completion or navigate to the next screen
            buttonClickListener?.onLoginButtonClick()

        }

        binding.tvAlreadyHaveAccount.setOnClickListener {
            buttonClickListener?.onLoginButtonClick()

        }
    }

    // Function to set the button click listener
    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        buttonClickListener = listener
    }

}