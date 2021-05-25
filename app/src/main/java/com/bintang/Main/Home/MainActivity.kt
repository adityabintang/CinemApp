package com.bintang.Main.Home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintang.Main.Detail.DetailActivity
import com.bintang.cinemapp.Adapter.AdapterMovie
import com.bintang.Main.List.AllMovieActivity
import com.bintang.Main.Login.LoginActivity
import com.bintang.Main.WishList.WishListActivity
import com.bintang.Utils.Const.CODE_LOGIN
import com.bintang.Utils.UserPreference
import com.bintang.cinemapp.Api.DataDummy
import com.bintang.cinemapp.R
import com.bintang.cinemapp.ResultsItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var listData = ArrayList<ResultsItem>()
    lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userPreference = UserPreference(this)

        shimmer_movie.startShimmer()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            shimmer_movie.stopShimmer()
            shimmer_movie.visibility = View.GONE
            movie.visibility = View.VISIBLE
            initListener()
            getData()
        }, 5000)


    }

    private fun initListener() {


        tv_user.setOnClickListener {
            if (userPreference.getStatusUser()) {
                val intent = Intent(this, WishListActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, CODE_LOGIN)
            }
        }

    }

    private fun initView() {
        movie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        movie.adapter = AdapterMovie(listData) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
            finish()
        }
        allView.setOnClickListener {
            val intent = Intent(this, AllMovieActivity::class.java)
            intent.putExtra("data", listData)
            startActivity(intent)
            finish()
        }

        if (userPreference.getStatusUser()) {
            tv_user.text = userPreference.getNamaUser()
            tv_desc.text = "Thanks for join, do you want exit?"
            iv_logout.visibility = View.VISIBLE
        } else {
            tv_user.text = "Login"
            tv_desc.text = "Save your favorite movie"
            iv_logout.visibility = View.INVISIBLE
        }
    }

    fun getData() {
        for (i in DataDummy.titleCinema.indices) {
            listData.add(
                ResultsItem(
                    i + 1,
                    DataDummy.titleCinema[i],
                    DataDummy.descCinema[i],
                    DataDummy.genreCinema[i],
                    DataDummy.posterCinema[i],
                    DataDummy.trailerCinema[i],
                    DataDummy.ratingCinema[i]
                )
            )
        }

        initView()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_LOGIN && resultCode == Activity.RESULT_OK) {
            val intent = Intent(this, WishListActivity::class.java)
            startActivity(intent)
            initView()
        }
    }

    override fun onResume() {
        super.onResume()
        initView()
    }


}