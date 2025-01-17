package com.example.testgithuser.Data.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testgithuser.Data.APIService
import com.example.testgithuser.Data.Repository.RepositoryUsers
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class ViewModelUsers(private val client: APIService):ViewModel() {
    private val mLiveDataListUsers: MutableLiveData<List<Users>> = MutableLiveData()
    val liveDataListUsers: LiveData<List<Users>> get() = mLiveDataListUsers

    private val mLiveDataSearchUser: MutableLiveData<UsersDetail> = MutableLiveData()
    val liveDataSearchUsers: LiveData<UsersDetail> get() = mLiveDataSearchUser

    fun getListUsers(){
        client.getListUsers().enqueue(object :
            retrofit2.Callback<List<Users>> {
            override fun onResponse(
                call: Call<List<Users>>,
                response: Response<List<Users>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    mLiveDataListUsers.value = data!!
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {

            }
        })
    }

    fun getSearchUsers(username: String){
        client.getSearchUsers(username).enqueue(object :
        retrofit2.Callback<UsersDetail>{
            override fun onResponse(call: Call<UsersDetail>, response: Response<UsersDetail>) {
                if (response.isSuccessful){
                    val data = response.body()
                    mLiveDataSearchUser.value = data!!
                }
            }

            override fun onFailure(call: Call<UsersDetail>, t: Throwable) {

            }
        })
    }
}