package com.example.mvvmaxample.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.example.mvvmaxample.R
import com.example.mvvmaxample.model.DataAdapter
import com.example.mvvmaxample.model.User
import com.example.mvvmaxample.viewmodel.MyViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class Login : AppCompatActivity() {

    val model:MyViewModel by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login_bt = findViewById<Button>(R.id.login_bt)
        val user_et = findViewById<EditText>(R.id.userName_et)
        val password_et = findViewById<EditText>(R.id.password_et)
        val title_tv = findViewById<TextView>(R.id.title_tv)

        val adapter = DataAdapter(this)

        login_bt.setOnClickListener()
        {
            model.getUser()
//            model.getmutbleData().observe(this){
//                title_tv.text = it.toString()
//                title_tv.textSize = 20F
//            }
//            if (model.checkUser(User(user_et.text.toString(), password_et.text.toString())))
//            {
//                startActivity(Intent(this,HomeScreen::class.java).apply {
//                    putExtra("user", user_et.text.toString())
//                })
//            }


            // Test writing file
            //adapter.clearFile(this)
//            adapter.saveUserToDB(User("than1", "hi1"))
//            adapter.saveUserToDB(User("than2", "hi2"))
//            adapter.saveUserToDB(User("than3", "hi3"))

        }

        findViewById<TextView>(R.id.title_tv).setOnClickListener()
        {
//            val data = DataAdapter(this).readFromFile(this, "dataUser.txt")
//            data?.split('|')?.forEach { Log.d("ThanVV", it) }
//            title_tv.text = data

            //startActivity(Intent(this, SignUp::class.java))
            Log.d("ThanVV", adapter.getUserFromStorage().toString())

        }
//        Log.d("ThanVV",DataAdapter().callAPI("https://run.mocky.io/v3/fdcf4780-0bb4-442a-8182-8418b522eae8").toString())
    }
}