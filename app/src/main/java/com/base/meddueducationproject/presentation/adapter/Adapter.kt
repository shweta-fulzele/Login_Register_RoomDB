package com.base.meddueducationproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.base.meddueducationproject.R
import com.base.meddueducationproject.data.roomdb.RegisterEntity
import com.base.meddueducationproject.databinding.UserListLayoutBinding


class Adapter(private val usersList :List<RegisterEntity>):RecyclerView.Adapter<MyviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: UserListLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.user_list_layout,parent,false)
        return MyviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.bind(usersList[position])

    }


}

class MyviewHolder(private val binding :UserListLayoutBinding ):RecyclerView.ViewHolder(binding.root){

    fun bind(user : RegisterEntity){
        binding.tvUsername.text = user.userName
    }

}