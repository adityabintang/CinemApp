package com.bintang.cinemapp.Database

import android.provider.BaseColumns

internal class DatabaseContract {

    //Penamaan Columns pada Database
    internal class MovieColumns: BaseColumns {
        companion object{
            const val TABLE_NAME = "movie"
            const val _ID = "id"
            const val TITLE = "title"
            const val OVERVIEW = "overview"
            const val GENRE = "genre"
            const val POSTERPATH = "posterPath"
            const val TRAILER = "trailer"
            const val VOTEAVERAGE = "voteAverage"

        }
    }
}