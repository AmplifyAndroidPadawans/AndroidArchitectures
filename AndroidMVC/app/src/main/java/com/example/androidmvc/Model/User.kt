package com.example.androidmvc.Model

import android.text.TextUtils
import android.util.Patterns

class User(
    private val email: String?,
    private val password: String?
) : IUser {

    override fun getEmail(): String? {
        return email
    }

    override fun getPassword(): String? {
        return password
    }

    override fun isValid(): Int {
        return if(TextUtils.isEmpty(getEmail()))
            0
        else if(!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            1
        else if(TextUtils.isEmpty(getPassword()))
            2
        else if(getPassword()?.length!! <=6)
            3
        else
            -1
    }
}