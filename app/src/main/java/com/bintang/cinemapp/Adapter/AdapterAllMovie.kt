package com.bintang.cinemapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintang.cinemapp.R
import com.bintang.cinemapp.ResultsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cinema.view.poster
import kotlinx.android.synthetic.main.item_cinema.view.title
import kotlinx.android.synthetic.main.item_cinema_allview.view.*

class AdapterAllMovie(

    val data: List<ResultsItem>,
    val listener: (ResultsItem) -> Unit
) : RecyclerView.Adapter<AdapterAllMovie.AdapterHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflate = layoutInflater.inflate(R.layout.item_cinema_allview, parent, false)
        return AdapterHolder(inflate)
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.bind(data.get(position), listener, contextAdapter, position)
    }

    override fun getItemCount(): Int = data.count()

    class AdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        fun bind(
            data: ResultsItem,
            listener: (ResultsItem) -> Unit,
            context: Context,
            position: Int
        ) {
            itemView.title.text = data.title
            itemView.all_desc.text = data.overview

            itemView.rating_view.rating = data.voteAverage

            Glide.with(itemView.context)
                .load(data.posterPath)
                .into(itemView.poster)



            itemView.setOnClickListener {
                listener(data)
            }

        }
    }

}
