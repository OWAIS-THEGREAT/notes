package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class record_list : AppCompatActivity() {

    var recyclerView : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)
        val token = intent.getStringExtra("token")
        if (token != null) {
            getlisting(token)
        }
        val write = findViewById<EditText>(R.id.writetodo)
        val texting = write.text
        val adding = findViewById<Button>(R.id.add)

        adding.setOnClickListener {
            if(texting.isNotEmpty()) {
                if (token != null) {
                    creatework(token, texting.toString())
//                    write.setText("")
                }
            }
            else{
                Toast.makeText(this,"Enter the text",Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun handleback(datalist : worklistdata) {
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView?.layoutManager = LinearLayoutManager(this@record_list)
        recyclerView?.adapter = workAdapter(datalist)

    }


    private fun getlisting(token: String) {

        val apiService = retrofitobject.createApiService(token)

        apiService.getworklist().enqueue(object : Callback<worklistdata?> {
            override fun onResponse(call: Call<worklistdata?>, response: Response<worklistdata?>) {
//                Log.d("response",response.body().toString())
                response.body()?.let { handleback(it) }
            }

            override fun onFailure(call: Call<worklistdata?>, t: Throwable) {

            }
        })
    }

    private fun creatework(token: String,task:String) {
//        val token = "80ccef7ea73a589459f8ed323c07f04981682c2a"
        val wdata = workdata(task)
//        Log.d("response",task)

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("works", wdata.works)
            .build()
        val apiService = retrofitobject.createApiService(token)


        apiService.creatework(requestBody).enqueue(object : Callback<workdata?> {
            override fun onResponse(call: Call<workdata?>, response: Response<workdata?>) {
                getlisting(token)
            }

            override fun onFailure(call: Call<workdata?>, t: Throwable) {

            }
        })

    }
}