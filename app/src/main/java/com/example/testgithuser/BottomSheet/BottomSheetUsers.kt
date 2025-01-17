package com.example.testgithuser.BottomSheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testgithuser.Data.APIClient
import com.example.testgithuser.Data.APIService
import com.example.testgithuser.Data.Model.Users
import com.example.testgithuser.Data.Model.UsersDetail
import com.example.testgithuser.Data.Model.ViewModelUsers
import com.example.testgithuser.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetUsers (private val users: UsersDetail?,private val username: String): BottomSheetDialogFragment() {

    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModelUsers: ViewModelUsers

    lateinit var tvName: TextView
    lateinit var tvCompany: TextView
    lateinit var tvAddres: TextView
    lateinit var tvBio: TextView
    lateinit var tvTotalfollowers: TextView
    lateinit var tvTotalfollowing: TextView
    lateinit var tvTotalgists: TextView
    lateinit var tvTotalrepos: TextView
    lateinit var imgAvatar: ImageView
    lateinit var imgClose: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottomsheetusers, container, false)

        tvName = view.findViewById(R.id.tv_name)
        tvCompany = view.findViewById(R.id.tv_company)
        tvAddres = view.findViewById(R.id.tv_addres)
        tvBio = view.findViewById(R.id.tv_bio)
        tvTotalfollowers = view.findViewById(R.id.tv_totalfollowers)
        tvTotalfollowing = view.findViewById(R.id.tv_totalfollowing)
        tvTotalgists = view.findViewById(R.id.tv_totalgists)
        tvTotalrepos = view.findViewById(R.id.tv_totalrepos)
        imgAvatar = view.findViewById(R.id.img_avatar)
        imgClose = view.findViewById(R.id.img_close)

        imgClose.setOnClickListener { dismiss() }

        if (users != null) {
            tvName.text = users?.login
            tvCompany.text = users?.company
            tvAddres.text = users?.location
            tvBio.text = users?.bio
            tvTotalfollowers.text = users?.followers.toString()
            tvTotalfollowing.text = users?.following.toString()
            tvTotalgists.text = users?.public_gists.toString()
            tvTotalrepos.text = users?.public_repos.toString()
            Glide.with(this).load(users?.avatar_url).into(imgAvatar)
        }else{
            setupViewModelFactory()
            setUpViewModel()
            getDataSearch(username)
        }
        return view
    }

    fun getDataSearch(username: String){
        viewModelUsers.getSearchUsers(username)
        viewModelUsers.liveDataSearchUsers.observe(this){ users ->
            if (users != null){
                tvName.text = users.login
                tvCompany.text = users.company
                tvAddres.text = users.location
                tvBio.text = users.bio
                tvTotalfollowers.text = users.followers.toString()
                tvTotalfollowing.text = users.following.toString()
                tvTotalgists.text = users.public_gists.toString()
                tvTotalrepos.text = users.public_repos.toString()
                Glide.with(this).load(users.avatar_url).into(imgAvatar)
            }
        }
    }

    private fun setupViewModelFactory() {
        val apiInterface = APIClient.getClient().create(APIService::class.java)

        viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                when {
                    modelClass.isAssignableFrom(ViewModelUsers::class.java) -> {
                        @Suppress("UNCHECKED_CAST")
                        return ViewModelUsers(apiInterface) as T
                    }

                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
        }
    }

    fun setUpViewModel(){
        viewModelUsers = ViewModelProvider(this,viewModelFactory)[ViewModelUsers::class.java]
    }
}