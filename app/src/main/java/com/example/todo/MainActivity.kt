package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val but = findViewById<Button>(R.id.signupbut)
        val already = findViewById<TextView>(R.id.already_have_an_account)
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val pass1 = findViewById<EditText>(R.id.password1)
        val pass2 = findViewById<EditText>(R.id.password2)
        val uname = username.text
        val nemail = email.text
        val npass1 = pass1.text
        val npass2 = pass2.text
        but.setOnClickListener {
            register(uname.toString(),nemail.toString(),npass1.toString(),npass2.toString())
        }

        already.setOnClickListener {
            val intent = Intent(this,signin::class.java)
            startActivity(intent)
        }

    }

    private fun sendlog(){
        val intent = Intent(this,signin::class.java)
        startActivity(intent)
    }
    private fun register(username:String,email:String,password1:String,password2:String){

//
        val data = signindata(username,email,password1,password2)

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", data.username)
            .addFormDataPart("email", data.email)
            .addFormDataPart("pass1", data.pass1)
            .addFormDataPart("pass2", data.pass2)
            .build()
        retrofitobjectup.apiinterface.registering(requestBody).enqueue(object : Callback<signinresp?> {
            override fun onResponse(call: Call<signinresp?>, response: Response<signinresp?>) {
//
                sendlog()
            }

            override fun onFailure(call: Call<signinresp?>, t: Throwable) {

            }
        })
    }

}