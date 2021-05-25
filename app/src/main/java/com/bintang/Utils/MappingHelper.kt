package com.bintang.Utils

import android.database.Cursor
import com.bintang.cinemapp.Database.DatabaseContract
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.GENRE
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.OVERVIEW
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.POSTERPATH
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.TITLE
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.TRAILER
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion.VOTEAVERAGE
import com.bintang.cinemapp.Database.DatabaseContract.MovieColumns.Companion._ID
import com.bintang.cinemapp.ResultsItem

object MappingHelper {

    fun mapCursorToArrayList(movieCursor: Cursor?): ArrayList<ResultsItem> {
        val movieList = ArrayList<ResultsItem>()
        movieCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndex(_ID))
                val title = getString(getColumnIndex(TITLE))
                val overView = getString(getColumnIndex(OVERVIEW))
                val genre = getString(getColumnIndex(GENRE))
                val posterPath = getInt(getColumnIndex(POSTERPATH))
                val trailer = getInt(getColumnIndex(TRAILER))
                val voteAverage = getFloat(getColumnIndex(VOTEAVERAGE))
                movieList.add(
                    ResultsItem(
                        id,
                        title,
                        overView,
                        genre,
                        posterPath,
                        trailer,
                        voteAverage
                    )
                )
            }
        }
        return movieList
    }
}