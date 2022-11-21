package com.example.mvvmaxample.model

import android.content.ComponentName
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.*
import java.io.*

class DataAdapter(val context: Context) {
    private val listUser = ArrayList<User?>()
    private val url = "https://run.mocky.io/v3/fdcf4780-0bb4-442a-8182-8418b522eae8"
    private val path = "dataUser.txt"
    fun updateListUser() {
        listUser.addAll(getUserFromStorage())
        listUser.add(getUserFromAPI(url))
    }

    fun getListUser() = listUser

    fun saveUserToDB(user: User) {
        val user_str = user.username + "," + user.password + "|"
        writeToFile(user_str, context, path)
    }

    fun getUserFromStorage(): ArrayList<User> {
        val listUser = ArrayList<User>()
        val data = readFromFile(context, path)?.trim()
        if (data != null) {
            val user_arr = data.split('|')
            var username = ""
            var password = ""
            for (i in 0 until user_arr.size - 1) {
                val componentUser = user_arr[i].split(',')
                username = componentUser[0]
                password = componentUser[1]
                listUser.add(User(username, password))
            }
        }
        return listUser

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
                delay(2000)
            }
        }
        //Log.d("ThanVV", user.toString())
        return user
    }

    fun clearFile() {
        val outputStreamWriter =
            OutputStreamWriter(context.openFileOutput("dataUser.txt", Context.MODE_PRIVATE))
        outputStreamWriter.write("")
        outputStreamWriter.close()
    }

    private fun writeToFile(data: String, context: Context, path: String) {
        try {
            val outputStreamWriter =
                OutputStreamWriter(context.openFileOutput(path, Context.MODE_APPEND))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: $e")
        }
    }

    private fun readFromFile(context: Context, path: String): String? {
        var ret = ""
        try {
            val inputStream: InputStream? = context.openFileInput(path)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String? = ""
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder
                        .append("\n")
                        .append(receiveString)
                }
                inputStream.close()
                ret = stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: $e")
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e")
        }
        return ret
    }
}