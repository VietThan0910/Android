package com.example.mvvmaxample.model

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.*
import java.io.*

class DataAdapter {
    private val listUser = ArrayList<User>()

    fun updateListUser()
    {
        listUser
    }

    fun saveUserToDB(user: User)
    {

    }

    fun getUserFromStorage():ArrayList<User>
    {

    }

    fun getUserFromAPI(url: String): User? {
        var user: User? = null
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        runBlocking {
            launch {
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {}
                    override fun onResponse(call: Call, response: Response) {
                        var gson = Gson()

                       val myJson = response.body()?.string()
                        user = gson.fromJson(myJson, User::class.java)
                        //Log.d("ThanVV", user.toString())
                    }
                })
                delay(1800)
            }
        }
        //Log.d("ThanVV", user.toString())
        return user
    }

    private fun writeToFile(data: String, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput("dataUser.txt", Context.MODE_PRIVATE))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: $e")
        }
    }
    private fun readFromFile(context: Context): String? {
        var ret = ""
        try {
            val inputStream: InputStream? = context.openFileInput("dataUser.txt")
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String? = ""
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder.append("\n").append(receiveString)
                }
                inputStream.close()
                ret = stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: " + e.toString())
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e")
        }
        return ret
    }
}