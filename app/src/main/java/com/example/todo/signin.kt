package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val username = findViewById<EditText>(R.id.name)
        val password = findViewById<EditText>(R.id.pass)

        val uname = username.text
        val pass = password.text
        val but = findViewById<Button>(R.id.signinbut)

        but.setOnClickListener {
            gettoken(uname.toString(),pass.toString())
        }
    }

    private fun sendtoken(token:String){
        val intent = Intent(this,record_list::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }


    private fun gettoken(username:String,password:String)  {

//        Log.d("response",username)
        val tok = getTokener(username,password)
        retrofitobject.apiinterface.send(tok).enqueue(object : Callback<tokenresponse?> {
            override fun onResponse(
                call: Call<tokenresponse?>,
                response: Response<tokenresponse?>
            ) {
                val token = response.body()?.token ?: ""
//                Log.d("response",token)
                sendtoken(token)
            }

            override fun onFailure(call: Call<tokenresponse?>, t: Throwable) {

            }
        })
    }
}