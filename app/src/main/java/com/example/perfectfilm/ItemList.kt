package com.example.perfectfilm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.perfectfilm.Model.Json_results
import com.example.perfectfilm.Model.ViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

const val  URI_IMAGE : String = "https://image.tmdb.org/t/p/w500/"
class ItemList(
    private val context: Context,
    private val movie: ArrayList<Json_results>?,
    val listener: (Json_results) -> Unit
) : RecyclerView.Adapter<ItemList.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rating = view.findViewById<TextView>(R.id.vote_average)
        val poster = view.findViewById<ImageView>(R.id.poster)
        var title = view.findViewById<TextView>(R.id.title)
        var progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        fun View(date: Json_results, context: Context, listener: (Json_results) -> Unit) {
            rating.text = date.vote_average + "/10"
            title.text = date.title


            Picasso.with(context)
                .load(URI_IMAGE + date.poster_path)
                .into(poster, object : Callback {
                    override fun onSuccess() {
                        if (progressBar != null) {
                            progressBar.visibility = View.GONE
                        }
                    }

                    override fun onError() {
                        TODO("Not yet implemented")
                    }
                })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_of_list, parent, false)
        )

    override fun getItemCount(): Int = movie!!.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.View(movie?.get(position)!!, this.context, listener)
    }
}
