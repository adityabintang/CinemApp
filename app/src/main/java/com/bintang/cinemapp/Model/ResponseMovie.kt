package com.bintang.cinemapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResponseMovie(var totalResults: Int, val results: List<ResultsItem>) : Parcelable

@Parcelize
data class ResultsItem(
    var id: Int,
    var title: String,
    var overview: String,
    var genre: String,
    var posterPath: Int,
    var trailer: Int,
    var voteAverage: Float
) : Parcelable
