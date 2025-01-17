package com.example.testgithuser.Data.Repository

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testgithuser.Data.APIService
import com.example.testgithuser.Data.Model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryUsers(private val apiService : APIService) {
    fun getListUsers(): LiveData<List<Users>>{
        val result = MutableLiveData<List<Users>>()
        val client = apiService.getListUsers()
        client.enqueue(object : Callback<List<Users>>{
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful){
                    result.value = response.body()
                }else{

                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {

            }
        })
        return result
    }
}