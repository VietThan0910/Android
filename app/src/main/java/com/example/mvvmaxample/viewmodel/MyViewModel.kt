package com.example.mvvmaxample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmaxample.model.DataAdapter
import com.example.mvvmaxample.model.User

class MyViewModel:ViewModel(){
    private val mutableLiveData = MutableLiveData<User>()
    private val dataAdapter = DataAdapter()
    private val url = "https://run.mocky.io/v3/fdcf4780-0bb4-442a-8182-8418b522eae8"
    fun getUser()
    {
        mutableLiveData.value = dataAdapter.getUserFromAPI(url)
    }
    fun getmutbleData() = mutableLiveData
    fun checkUser(user: User?):Boolean = (user!= null && user == dataAdapter.getUserFromAPI(url))
}