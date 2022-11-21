package com.example.mvvmaxample.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvmaxample.model.DataAdapter
import com.example.mvvmaxample.model.User

class MyViewModel(): ViewModelProvider.Factory{
    private val mutableLiveData = MutableLiveData<User>()
    private val dataAdapter = DataAdapter(context)
    private val url = "https://run.mocky.io/v3/fdcf4780-0bb4-442a-8182-8418b522eae8"
    fun getUser()
    {
        //mutableLiveData.value = dataAdapter.getUserFromAPI(url)
        mutableLiveData.value = User("Than", "123")
    }
    fun getmutbleData() = mutableLiveData
    fun checkUser(user: User):Boolean
    {
        dataAdapter.updateListUser()
        val listUser = dataAdapter.getListUser()
        return listUser.contains(user)
    }


    fun signUpUser(user: User)
    {
        dataAdapter.saveUserToDB(user)
    }
}