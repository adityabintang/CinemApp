package com.bintang.Main.List

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintang.Main.Detail.DetailActivity
import com.bintang.cinemapp.Adapter.AdapterAllMovie
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.GENRE
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.OVERVIEW
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.POSTERPATH
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.TITLE
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.TRAILER
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.VOTEAVERAGE
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion._ID
import com.bintang.cinemapp.Database.DatabaseHelper
import com.bintang.cinemapp.Database.MovieHelper
import com.bintang.cinemapp.R
import com.bintang.cinemapp.ResultsItem

import com.bintang.cinemapp.databinding.ActivityAllMovieBinding
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_all_movie.*
import kotlinx.android.synthetic.main.item_cinema_allview.*

class AllMovieActivity : AppCompatActivity() {


    private var listData = ArrayList<ResultsItem>()


    private lateinit var binding: ActivityAllMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        listData = intent.getParcelableArrayListExtra("data")!!

        initView()
        initListener()

    }

    fun initView() {
        all_movie.layoutManager = LinearLayoutManager(this)

    }

    fun initListener() {
        all_movie.adapter = AdapterAllMovie(listData) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
            finish()
        }

    }






}