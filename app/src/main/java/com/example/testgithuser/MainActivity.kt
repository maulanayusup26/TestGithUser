package com.example.testgithuser

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testgithuser.Adapter.AdapterListUsers
import com.example.testgithuser.BottomSheet.BottomSheetUsers
import com.example.testgithuser.Data.APIClient
import com.example.testgithuser.Data.APIService
import com.example.testgithuser.Data.Model.ViewModelUsers

class MainActivity : AppCompatActivity() {
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModelUsers: ViewModelUsers
    lateinit var rvList: RecyclerView
    lateinit var etSearch: EditText
    lateinit var imgClear: ImageView
    lateinit var imgAvatar: ImageView
    lateinit var tvUsername: TextView
    lateinit var clUsersearch: CardView
    lateinit var clLoading: ConstraintLayout

    private var lastInput: String = ""
    private var searchHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        rvList = findViewById(R.id.rv_listusers)
        etSearch = findViewById(R.id.et_search)
        imgClear = findViewById(R.id.img_clear)
        imgAvatar = findViewById(R.id.img_avatarsearch)
        tvUsername = findViewById(R.id.tv_namesearch)
        clUsersearch = findViewById(R.id.cl_usersearch)
        clLoading = findViewById(R.id.cl_loading)

        setupViewModelFactory()
        setUpViewModel()
        getData()
        setUpView()
    }

    fun setUpView(){
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()

                searchHandler.removeCallbacksAndMessages(null)

                if (input.isNotEmpty()) {
                    searchHandler.postDelayed({
                        if (lastInput != input) {
                            lastInput = input
                            getDataSearch(lastInput)
                        }
                    }, 2000)
                }else{
                    rvList.visibility = View.VISIBLE
                    clUsersearch.visibility = View.GONE
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                imgClear.visibility = if (count > 0) View.VISIBLE else View.GONE
            }
        })

        imgClear.setOnClickListener {
            etSearch.clearFocus()
            etSearch.text.clear()
            imgClear.visibility = View.GONE
            rvList.visibility = View.VISIBLE
            clUsersearch.visibility = View.GONE
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

    fun getData(){
        viewModelUsers.getListUsers()
        viewModelUsers.liveDataListUsers.observe(this){
            if (it != null){
                Log.d("maulana",it.toString())
                rvList.layoutManager = LinearLayoutManager(this)
                rvList.adapter = AdapterListUsers(it) { user ->
                    val bottomSheet = BottomSheetUsers(null,user.login)
                    bottomSheet.show(supportFragmentManager, "CustomBottomSheetDialog")
                    Log.d("maulana","klik")
                }
                rvList.visibility = View.VISIBLE
                clLoading.visibility = View.GONE
            }else{
                Log.d("maulana",it.toString())
            }
        }
    }

    fun getDataSearch(username: String){
        viewModelUsers.getSearchUsers(username)
        viewModelUsers.liveDataSearchUsers.observe(this){ users ->
            if (users != null){
                rvList.visibility = View.GONE
                clUsersearch.visibility = View.VISIBLE
                tvUsername.text = users.login
                Glide.with(this).load(users.avatar_url).into(imgAvatar)
                clUsersearch.setOnClickListener{
                    val bottomSheet = BottomSheetUsers(users,"")
                    bottomSheet.show(supportFragmentManager, "CustomBottomSheetDialog")
                }
            }
        }
    }
}