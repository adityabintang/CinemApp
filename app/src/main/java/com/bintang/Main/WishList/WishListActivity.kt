package com.bintang.Main.WishList

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintang.Main.Detail.DetailActivity
import com.bintang.Utils.MappingHelper
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WishListActivity : AppCompatActivity() {


    private var listData = ArrayList<ResultsItem>()

    lateinit var movieHelper: MovieHelper

    private lateinit var binding: ActivityAllMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        movieHelper = MovieHelper.getInstance(this)
        movieHelper.open()


    }

    fun getData() {
        GlobalScope.launch(Dispatchers.Main) {
            val defferValue = async(Dispatchers.IO) {
                var cursor = movieHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            listData = defferValue.await()
            initView()
        }
    }

    fun initView() {
        all_movie.layoutManager = LinearLayoutManager(this)
        all_movie.adapter = AdapterAllMovie(listData) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        getData()
    }


}