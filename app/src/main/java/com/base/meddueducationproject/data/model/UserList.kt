package com.base.meddueducationproject.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserList(
    var name: String,
    var mobileNumber: Int,
    var email: String
) : Parcelable
