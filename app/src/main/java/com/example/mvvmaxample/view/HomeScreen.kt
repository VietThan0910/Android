package com.example.mvvmaxample.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.example.mvvmaxample.R
import com.example.mvvmaxample.model.DataAdapter
import com.example.mvvmaxample.viewmodel.MyViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeScreen : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val tv = findViewById<TextView>(R.id.textView)
        val model:MyViewModel by viewModels<MyViewModel>()
        model.getUser()
        model.getmutbleData().observe(this){
            tv.text = "Chào mừng ${intent.getStringExtra("user")} đến với bình nguyên vô tận"
        }
    }
}