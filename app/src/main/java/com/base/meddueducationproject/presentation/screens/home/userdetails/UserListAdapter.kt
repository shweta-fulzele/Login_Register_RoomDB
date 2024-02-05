package com.base.meddueducationproject.presentation.screens.home.userdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.base.meddueducationproject.R
import com.base.meddueducationproject.data.roomdb.MedduEntity
import com.base.meddueducationproject.databinding.UserListLayoutBinding

class UserListAdapter(private val usersList :List<MedduEntity>):RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: UserListLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.user_list_layout,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position])
    }


}

class MyViewHolder(private val binding :UserListLayoutBinding ):RecyclerView.ViewHolder(binding.root){

    fun bind(user : MedduEntity){
        binding.tvUsername.text = "Name ${user.userName}"
        binding.tvEmail.text = "Email ${user.email}"
        binding.tvPhone.text = "Phone Number ${user.mobile}"
    }

}