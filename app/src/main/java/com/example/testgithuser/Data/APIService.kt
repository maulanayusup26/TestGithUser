package com.example.testgithuser.Data

import com.example.testgithuser.Data.Model.Users
import com.example.testgithuser.Data.Model.UsersDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("users")
    fun getListUsers():retrofit2.Call<List<Users>>

    @GET("users/{username}")
    fun getSearchUsers(@Path("username")username:String):retrofit2.Call<UsersDetail>
}