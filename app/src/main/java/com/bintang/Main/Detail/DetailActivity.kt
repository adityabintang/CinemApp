package com.bintang.Main.Detail

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.bintang.Main.Home.MainActivity
import com.bintang.Main.Login.LoginActivity
import com.bintang.Utils.Const.CODE_LOGIN
import com.bintang.Utils.UserPreference
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
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    lateinit var data: ResultsItem
    lateinit var movieHelper: MovieHelper
    private lateinit var values: ContentValues
    private var statusFavorite = false
    lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        data = intent.getParcelableExtra("data")!!

        userPreference = UserPreference(this)
        movieHelper = MovieHelper.getInstance(this)
        movieHelper.open()

        values = ContentValues()
        initView()
        initListener()


    }

    //Initialisasi View
    fun initView() {
        title_detail.text = data.title
        genre.text = data.genre
        desc.text = data.overview

        statusFavorite()

    }

    //Listener
    fun initListener() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(trailer)
        trailer.setMediaController(mediaController)



        trailer.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + data.trailer))
        trailer.start()

        backButtton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        favorite.setOnClickListener {
            if (userPreference.getStatusUser()) {
                if (statusFavorite) {
                    movieHelper.deleteById(data.id.toString())
                    iconFavorite(false)
                } else {
                    values.put(_ID, data.id)
                    values.put(TITLE, data.title)
                    values.put(OVERVIEW, data.overview)
                    values.put(GENRE, data.genre)
                    values.put(POSTERPATH, data.posterPath)
                    values.put(TRAILER, data.trailer)
                    values.put(VOTEAVERAGE, data.voteAverage)

                    movieHelper.insert(values)

                    iconFavorite(true)
                }
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, CODE_LOGIN)

            }


        }


    }

    fun iconFavorite(boolean: Boolean) {
        if (boolean) {
            statusFavorite = true
            favorite.setImageResource(R.drawable.ic_favorite_enable)
        } else {
            statusFavorite = false
            favorite.setImageResource(R.drawable.ic_favorite_disable)
        }
    }

    fun statusFavorite() {
        var cursor = movieHelper.queryById(data.id.toString())
        if (cursor.moveToNext()) {
            iconFavorite(true)
        } else {
            iconFavorite(false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, bundle: Intent?) {
        super.onActivityResult(requestCode, resultCode, bundle)

        if (requestCode == CODE_LOGIN && resultCode == Activity.RESULT_OK) {
            if (statusFavorite) {
                movieHelper.deleteById(data.id.toString())
                iconFavorite(false)
            } else {
                values.put(_ID, data.id)
                values.put(TITLE, data.title)
                values.put(OVERVIEW, data.overview)
                values.put(GENRE, data.genre)
                values.put(POSTERPATH, data.posterPath)
                values.put(TRAILER, data.trailer)
                values.put(VOTEAVERAGE, data.voteAverage)

                movieHelper.insert(values)

                iconFavorite(true)
            }
        }
    }
}