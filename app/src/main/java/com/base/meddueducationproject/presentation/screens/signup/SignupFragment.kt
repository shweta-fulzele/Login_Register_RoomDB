package com.base.meddueducationproject.presentation.screens.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.base.meddueducationproject.R
import com.base.meddueducationproject.data.repository.MedduRepository
import com.base.meddueducationproject.data.roomdb.MedduDatabase
import com.base.meddueducationproject.databinding.FragmentSignupBinding
import com.base.meddueducationproject.presentation.interfaceutils.OnButtonClickListener

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private var buttonClickListener: OnButtonClickListener? = null
    private lateinit var registerViewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSignupBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_signup, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = MedduDatabase.getInstance(application).registerDatabaseDao

        val repository = MedduRepository(dao)

        val factory = SignupViewModelFactory(repository, application)

        registerViewModel = ViewModelProvider(this, factory).get(SignupViewModel::class.java)

        binding.myViewModel = registerViewModel

        binding.lifecycleOwner = this

        registerViewModel.navigateTo.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){
                registerViewModel.doneNavigating()
                buttonClickListener?.onLoginButtonClick()

            }
        })

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner, Observer {
        })


        registerViewModel.errorToast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                registerViewModel.doneToast()
            }
        })

        registerViewModel.errorToastUsername.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT).show()
                registerViewModel.doneToastUserName()
            }
        })

        binding.tvAlreadyHaveAccount.setOnClickListener{
            buttonClickListener?.onAlreadyHaveAccount()
        }


        return binding.root
    }

    // Function to set the button click listener
    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        buttonClickListener = listener
    }

}