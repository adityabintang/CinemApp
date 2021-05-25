package com.bintang.cinemapp.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.GENRE
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.OVERVIEW
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.POSTERPATH
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.TABLE_NAME
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.TITLE
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.TRAILER
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.VOTEAVERAGE
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion._ID


internal class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dbcinemapp"
        private const val DATABASE_VERSION = 1

        //Query pembuatan database
        private val SQL_CREATE_TABLE_MOVIE = "CREATE TABLE $TABLE_NAME" +
                "(${_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TITLE} TEXT NOT NULL," +
                "${OVERVIEW} TEXT NOT NULL," +
                "${GENRE} TEXT NOT NULL," +
                "${POSTERPATH} TEXT NOT NULL," +
                "${TRAILER} TEXT NOT NULL," +
                "${VOTEAVERAGE} TEXT NOT NULL)"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_MOVIE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}