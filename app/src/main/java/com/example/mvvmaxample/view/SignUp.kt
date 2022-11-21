package com.example.mvvmaxample.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.example.mvvmaxample.R
import com.example.mvvmaxample.model.User
import com.example.mvvmaxample.viewmodel.MyViewModel

class SignUp : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val model:MyViewModel by viewModels<MyViewModel>()
        findViewById<Button>(R.id.signup_bt).setOnClickListener()
        {
            model.signUpUser(User(findViewById<EditText>(R.id.userName_et2).text.toString(), findViewById<EditText>(R.id.password_et2).text.toString()))
            startActivity(Intent(this, Login::class.java))
        }
    }
}