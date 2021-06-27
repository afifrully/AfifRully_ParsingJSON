package com.example.afifrully_parsingjson

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.ic.pelitabangsa.dendiyp.dyprmadi_parsingjson.UserAdapter
import id.ic.pelitabangsa.dendiyp.dyprmadi_parsingjson.model.DataItem
import id.ic.pelitabangsa.dendiyp.dyprmadi_parsingjson.model.ResponUser
import id.ic.pelitabangsa.dendiyp.dyprmadi_parsingjson.network.ApiConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = UserAdapter(mutableListOf())

        rvuser.setHasFixedSize(true)
        rvuser.layoutManager = LinearLayoutManager( this)
        rvuser.adapter = adapter
        getUser()
    }

    private fun mutableListOf() {

    }

    private fun getUser(){
        val client = ApiConfig.getApiService().getListUsers("3"

        client.enqueue(object : retrofit2.Callback<ResponUser> {
            override fun onResponse(call: Call<ResponUser>, response: Response<ResponUser>) {
                if (response.isSuccessful){
                    val dataArray = response.body()?.Data as List<DataItem>
                    for (data in dataArray){
                        adapter.addUser(data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponUser>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT.show())
            }
        })
    }
}