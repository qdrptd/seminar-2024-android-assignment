package com.wafflestudio.waffleseminar2024.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.data.modules.LikedMovie

class posterRecyclerViewAdapter(
    private val posterList: List<LikedMovie>,
    private val onPosterClick: (LikedMovie) -> Unit
) : RecyclerView.Adapter<posterRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = posterList[position]
        val imageUrl = "https://image.tmdb.org/t/p/original" + currentItem.posterPath
        holder.imageView.load(imageUrl)
        holder.itemView.setOnClickListener {onPosterClick(currentItem)}
    }

    override fun getItemCount(): Int {
        return posterList.size
    }

}
